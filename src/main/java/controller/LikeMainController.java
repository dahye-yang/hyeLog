package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LikeDao;
import model.vo.Like;
import model.vo.User;

@WebServlet("/private/likemain")
public class LikeMainController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User found = (User)request.getSession().getAttribute("logonUser");
		LikeDao likedao = new LikeDao();
		
		try {
			List<Like> list = likedao.findAllByUserId(found.getId());
						
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/private/likemain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
