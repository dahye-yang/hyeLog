package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BuyLogDao;
import model.dao.CartDao;
import model.dao.CouponStorageDao;
import model.dao.PointDao;
import model.dao.UserDao;
import model.vo.BuyLog;
import model.vo.CouponStorage;
import model.vo.Point;
import model.vo.User;

@WebServlet("/private/order/buy")
public class BuyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 구매로그 save 성공 時 User balance update 및 point 적립

		String itemcode = request.getParameter("itemcode");
		String piece = request.getParameter("piece");
		String price = request.getParameter("price");
		int price2 = Integer.parseInt(price);

		String point = request.getParameter("point");
		double point2 = Double.valueOf(point);
		int point3 = (int) point2;

		// 구매완료 후 장바구니에서 삭제
		String cartId = request.getParameter("cartId");
		System.out.println("cartId---->" + cartId);

		User found = (User) request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());

		BuyLogDao buylogdao = new BuyLogDao();
		UserDao userdao = new UserDao();
		PointDao pointdao = new PointDao();
		CartDao cartDao = new CartDao();

		try {
			// user의 잔액이 0이거나 구매금액 마이너스 금액이 0이하이면...
			if (found.getBalance() < 0 || found.getBalance() < price2) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('잔액이 부족합니다.😥\\n잔액충전을 해주세요.🥰');history.go(-1);</script>");
				w.flush();
				w.close();
			} else {
				BuyLog one = new BuyLog(0, found.getId(), price2, now, Integer.parseInt(piece),
						Integer.parseInt(itemcode));
				// 구매 Log 저장
				boolean result = buylogdao.save(one);
				System.out.println("구매로그 등록결과--->" + result);
				found.setBalance(found.getBalance() - price2);
				// -----------
				found.setUseMoney(found.getUseMoney() + price2);
				// 레벨업 조건 체크하기
				boolean result2 = userdao.update(found);
				System.out.println("유저 잔액변경 결과-->" + result2);

				// 레벨업 과정
				int target = found.getUseMoney();

				int modifyLv = 1;
				if (target >= 100000 && target < 300000) {
					modifyLv = 2;
				} else if (target >= 300000 && target < 700000) {
					modifyLv = 3;
				} else if (target >= 700000 && target < 900000) {
					modifyLv = 4;
				} else {
					modifyLv = 5;
				}

				int oldLevel = found.getLevelId();

				if (oldLevel != modifyLv) {
					found.setLevelId(modifyLv);
					userdao.update(found);
					
					//날짜기한 2주
					LocalDate local = LocalDate.now();
					LocalDate aa = local.plusWeeks(2);
					Date exp = Date.valueOf(aa);
					 
					//레벨업 쿠폰 주기
					CouponStorage couponStorage = new CouponStorage(0,found.getId(),exp,5);
					CouponStorageDao couponStorageDao = new CouponStorageDao();
					 couponStorageDao.save(couponStorage);
					
					couponStorageDao.findCouponByUser(found.getId());
			
					response.setContentType("text/html; charset=utf-8");
					PrintWriter w = response.getWriter();
					w.write("<script>alert('" + oldLevel + "Lv ➝" + found.getLevelId() + "Lv로 레벨업했습니다. 레벨업쿠폰 지급해드렸으니 확인해 보세요:)'); location.href='"
							+ request.getServletContext().getContextPath() + "/private/myshop';</script>");
					w.flush();
					w.close();
				}

				// 포인트적립내역
				Point two = new Point(0, found.getId(), "구매적립포인트!", point3, now);
				pointdao.save(two);
				// 장바구니에서 삭제
				boolean result3 = cartDao.deletById(Integer.parseInt(cartId));
				System.out.println("장바구니 삭제결과-->" + result3);
				// 주문조회 만든 후 그곳으로 이동하도록 바꾸기
				response.sendRedirect(request.getServletContext().getContextPath() + "/view/main");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
