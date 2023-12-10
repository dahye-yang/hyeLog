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

@WebServlet("/private/order/buycart")
public class BuyAtCartController extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// 장바구니에서 개별구매
		String itemCode = request.getParameter("deleteNo");
		//System.out.println("deleteNo--->"+itemCode);
		int y = Integer.parseInt(itemCode);
		
		User found = (User)request.getSession().getAttribute("logonUser");
		CartDao cartDao = new CartDao();
		
		try {
			
			Cart two = cartDao.findByUserIdAndItemCode(found.getId(), y);
			System.out.println("카트피스 몇개넘어가냑!!!"+two.getCartPiece());
			request.setAttribute("found", found);
			request.setAttribute("two", two);
			request.setAttribute("piece", two.getCartPiece());
			request.setAttribute("code", y);
				
			request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
