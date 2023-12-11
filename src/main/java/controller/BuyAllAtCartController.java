package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;
import model.dao.CouponStorageDao;
import model.dao.PointDao;
import model.vo.Cart;
import model.vo.CouponStorage;
import model.vo.Point;
import model.vo.User;

@WebServlet("/private/order/buycartall")
public class BuyAllAtCartController extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 장바구니에서 전체구매
		String[] cartId = request.getParameterValues("cartId");
		String orderId = request.getParameter("deleteNo");
		
		User found = (User)request.getSession().getAttribute("logonUser");
		CartDao cartDao = new CartDao();
		PointDao pointDao = new PointDao();
		CouponStorageDao couponStorageDao = new CouponStorageDao();
		
		if(orderId != null) {
			cartId = new String[cartId.length];
			cartId[0] = orderId;
			
		} 
		
//		System.out.println("카트아이디 갯수---->"+cartId.length); // 넘어오고..?
		
		int sum = 0;
		
		try {
			if(cartId != null) {
				
				List<Cart> list = cartDao.findByUserIdAndCartId(found.getId(), cartId);
				
				System.out.println("담긴리스트 사이즈--->"+list.size());
	
				for(Cart a : list) {
					sum += a.getCartPiece() * a.getItem().getPrice();
				}
				System.out.println("구매할 예상총액은--?"+sum);
				
				Point four = pointDao.findPointSumByUserId(found.getId());
				//쿠폰
				List<CouponStorage> couponList =  couponStorageDao.findCouponByUser(found.getId());
				request.setAttribute("couponList", couponList);
				
				request.setAttribute("point", four);
				request.setAttribute("sum", sum);
				request.setAttribute("list", list);
				request.setAttribute("found", found);
				
				request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('구매할 상품이 없습니다 :)');history.go(-1);</script>");
				w.flush();
				w.close();
			}
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
