package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;

@WebServlet("/private/order/cartdelete")
public class CartDeleteController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("cartid");
		
		CartDao cartdao = new CartDao();
		
		try {
			boolean result = cartdao.deletById(Integer.parseInt(id));
			System.out.println("삭제결과 -->"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		response.sendRedirect(request.getServletContext().getContextPath()+"/private/order/cartmain");
		//request.getRequestDispatcher("/WEB-INF/private/order/cartdelete.jsp").forward(request, response);
	}
}
