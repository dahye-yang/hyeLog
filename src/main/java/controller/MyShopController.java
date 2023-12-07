package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.vo.User;
@WebServlet("/private/myshop")
public class MyShopController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User one = (User) request.getSession().getAttribute("logonUser");
		UserDao userDao = new UserDao();
		
		try {
			User found = userDao.findUserWithlevelImgByUserId(one.getId());
			request.getSession().setAttribute("logonUser", found);	

			//request.setAttribute("user", one);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/private/myshop.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/private/myshop.jsp").forward(request, response);
	}
}
