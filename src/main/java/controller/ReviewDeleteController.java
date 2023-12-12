package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ReviewDao;

@WebServlet("/private/reviewdelete")
public class ReviewDeleteController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리뷰 삭제기능
		String id = request.getParameter("reviewId");
		int x = Integer.parseInt(id);
		String code = request.getParameter("code");
		
		ReviewDao reviewdao = new ReviewDao();
		
		try {

			reviewdao.deletById(x);
			
			response.sendRedirect(request.getServletContext().getContextPath()+"/view/detail?code="+code);
			//request.getRequestDispatcher("/WEB-INF/private/reviewdelete.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
