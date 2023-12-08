package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BuyLogDao;
import model.vo.User;

@WebServlet("/private/order/buy")
public class BuyController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User found = (User)request.getSession().getAttribute("logonUser");
		
		request.setAttribute("found", found);
		request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 구매로그 save
		
		BuyLogDao buyLogDao = new BuyLogDao();
		
		try {
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		response.sendRedirect(request.getServletContext().getContextPath()+"/view/main");
	}
}
