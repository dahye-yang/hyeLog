package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.vo.User;

@WebServlet("/private/delete")
public class DeleteUserController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User found = (User)request.getSession().getAttribute("logonUser");
		request.setAttribute("found", found);
		
		request.getRequestDispatcher("WEB-INF/private/delete.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter("deletepassword");
		
		User one = (User)request.getSession().getAttribute("logonUser");
		UserDao userdao = new UserDao();
		
		boolean result = false;
		
		try {
			if(password == "" || password == null) {
				result = false;
			}else {
				if(one.getPassword().equals(password)) {
					result = userdao.deletById(one.getId());
					request.getSession().invalidate();
					request.getRequestDispatcher("/WEB-INF/private/delete.jsp").forward(request, response);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
