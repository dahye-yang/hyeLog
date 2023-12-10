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
import model.dao.PointDao;
import model.dao.UserDao;
import model.vo.BuyLog;
import model.vo.Cart;
import model.vo.Point;
import model.vo.User;

@WebServlet("/private/order/buyall")
public class BuyAllController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 구매로그 save 성공 時 User balance update 및 point 적립
	
		String[] cartId = request.getParameterValues("cartId");
		String[] itemCode = request.getParameterValues("itemCode");
		System.out.println("cartId 넘어온 갯수---->"+cartId.length);	
		System.out.println("itemCode 넘어온 갯수---->"+itemCode.length);	
		
		String price = request.getParameter("price");
		int price2 = Integer.parseInt(price);
				
		String point = request.getParameter("point");
		double point2 = Double.valueOf(point);
		int point3 = (int)point2;
				
		User found = (User)request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());
		
		BuyLogDao buylogdao = new BuyLogDao();
		UserDao userdao = new UserDao();
		PointDao pointdao = new PointDao();
		CartDao cartDao = new CartDao();
		
		List<Cart> list = new ArrayList<Cart>();
		try {
			// user의 잔액이 0이거나 구매금액 마이너스 금액이 0이하이면...
			if(found.getBalance() < 0 || found.getBalance() < price2) {
				response.setContentType("text/html; charset=utf-8");
		        PrintWriter w = response.getWriter();
		        w.write("<script>alert('잔액이 부족합니다.😥\\n잔액충전을 해주세요.🥰');history.go(-1);</script>");
		        w.flush();
		        w.close();
			}else {		
				for(int i=0; i < cartId.length; i++) {
					
					Cart two = cartDao.findByUserIdAndItemCode(found.getId(), Integer.parseInt(itemCode[i]));
					list.add(two);
					int sum = two.getCartPiece() * two.getItem().getPrice(); // 장바구니 각각 금액(갯수*상품금액)
					BuyLog one = new BuyLog(0, found.getId(), sum,
							now, two.getCartPiece(), two.getItemCode());
					// 구매 Log 저장
					boolean result = buylogdao.save(one);
					System.out.println("구매로그 등록결과--->"+result);
				}
				
				// 레벨업 조건 체크하기
				found.setBalance(found.getBalance()-price2);
				boolean result2 = userdao.update(found);
				System.out.println("유저 잔액변경 결과-->"+result2);
				// 적립포인트 적용
				Point two = new Point(0,found.getId(),"구매적립포인트!", point3, now);
				pointdao.save(two);
				// 장바구니에서 삭제
				for(int i =0; i < cartId.length; i++) {
					boolean result3 = cartDao.deletById(Integer.parseInt(cartId[i]));
					System.out.println("장바구니 삭제결과-->"+result3);
				}
				response.sendRedirect(request.getServletContext().getContextPath()+"/view/main");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
