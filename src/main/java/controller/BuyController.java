package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import model.vo.Cart;
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
		
		String coupon = request.getParameter("coupon");

		String[] itemcodes = request.getParameterValues("itemcode");
	//	System.out.println("아이템코드 배열사이트 -->" + itemcodes.length);
		String[] pieces = request.getParameterValues("piece");
	//	System.out.println("piece 배열사이트 -->" + pieces.length);
		String[] cartIds = request.getParameterValues("cartId");
		System.out.println("cartId 배열사이트 -->" + cartIds.length);
		
		// 구매총액
		String sum = request.getParameter("sum"); 
		int sum2 = Integer.parseInt(sum);

		// 각 장바구니id별 가격을 ,,,
//		String price = request.getParameter("price");
//		int price2 = Integer.parseInt(price);
//		System.out.println("넘어온 price-->" + price);
		String point = request.getParameter("point");
		int point3 = 0;
		if(Integer.parseInt(point) != 0) {
			double point2 = Double.valueOf(point);
			point3 = (int) point2;	
			System.out.println("포인트는----??"+point3);
		}
//		//구매완료 후 장바구니에서 삭제
//		String cartId = request.getParameter("cartId");
//		System.out.println("cartId---->"+cartId);

		User found = (User) request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());

		BuyLogDao buylogdao = new BuyLogDao();
		UserDao userdao = new UserDao();
		PointDao pointdao = new PointDao();
		CartDao cartDao = new CartDao();
		CouponStorageDao couponStorageDao = new CouponStorageDao();
		
		try {
			// user의 잔액이 0이거나 구매금액 마이너스 금액이 0이하이면...
			if (found.getBalance() < 0 || found.getBalance() < sum2) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('잔액이 부족합니다.😥\\n잔액충전을 해주세요.🥰');history.go(-1);</script>");
				w.flush();
				w.close();
			} else {
				List<Cart> list = cartDao.findByUserIdAndCartId(found.getId(), cartIds);
				List<Integer> prices = new ArrayList<Integer>();
				System.out.println("Integer는 몇갯!!!-->"+prices.size());
				
				for(Cart g : list) {
					prices.add(g.getCartPiece()*g.getItem().getPrice());
				}
				
				for (int i = 0; i < itemcodes.length; i++) {

					BuyLog one = new BuyLog(0, found.getId(), prices.get(i), now, Integer.parseInt(pieces[i]),
							Integer.parseInt(itemcodes[i]));
					// 구매 Log 저장
					boolean result = buylogdao.save(one);
					System.out.println("구매로그 등록결과--->" + result + i);
				}

				// 레벨업 조건 체크하기
				found.setBalance(found.getBalance() - sum2);
				boolean result2 = userdao.update(found);
				System.out.println("유저 잔액변경 결과-->" + result2);
				
				if(point3 > 0) {
					Point two = new Point(0, found.getId(), "구매적립포인트!", point3, now);
					pointdao.save(two);
				}else {
					Point two = new Point(0, found.getId(), "포인트사용!", point3, now);
					pointdao.save(two);
				}
				
				// 장바구니에서 삭제
				for (int i = 0; i < itemcodes.length; i++) {

					boolean result3 = cartDao.deletById(Integer.parseInt(cartIds[i]));
					System.out.println("장바구니 삭제결과-->" + result3+i);
					// 주문조회 만든 후 그곳으로 이동하도록 바꾸기
					
				}
				boolean result4 = couponStorageDao.deletByNo(Integer.parseInt(coupon));
				System.out.println("쿠폰사용후 삭제여부---?"+result4);
				response.sendRedirect(request.getServletContext().getContextPath() + "/view/main");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
