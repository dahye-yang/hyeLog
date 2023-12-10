package controller;

import java.io.IOException;

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
		
		String piece = request.getParameter("piece");
		int x = Integer.parseInt(piece);
		String itemCode = request.getParameter("itemcode");
		int y = Integer.parseInt(itemCode);
		User found = (User)request.getSession().getAttribute("logonUser");
		
		CartDao cartDao = new CartDao();
		
		try {
			
			Cart two = cartDao.findByUserIdAndItemCode(found.getId(), y);
			
			request.setAttribute("found", found);
			request.setAttribute("two", two);
			request.setAttribute("piece", x);
			request.setAttribute("code", y);
			request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
