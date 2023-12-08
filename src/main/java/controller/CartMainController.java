package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;
import model.vo.Cart;
import model.vo.User;

@WebServlet("/private/order/cartmain")
public class CartMainController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니 메인 페이지
		User found = (User)request.getSession().getAttribute("logonUser");
		CartDao cartdao = new CartDao();
		
		try {
			List<Cart> list = cartdao.findAllByUserId(found.getId());
//			List<Integer> points = new ArrayList<>();
			
			// 장바구니에 담을 (개수 * 상품가격)
//			for(Cart a : list) {
//				int x = a.getItem().getPrice() * a.getCartPiece();
//				//System.out.println("x값은---->"+x);
//				a.getItem().setPrice(x);
//				//구매금액의 1% 포인트
//				double bb = x / 100;
//				int i;
//				i = (int)Math.round(bb);
//				points.add(i);	
//			}
//			request.setAttribute("points", points);
			request.setAttribute("list", list);	
			request.getRequestDispatcher("/WEB-INF/private/order/cartmain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
