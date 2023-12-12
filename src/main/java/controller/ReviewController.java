package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ItemDao;
import model.dao.ReviewDao;
import model.vo.Item;
import model.vo.Review;
import model.vo.User;

@WebServlet("/private/review")
public class ReviewController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User found = (User)request.getSession().getAttribute("logonUser");
		String code = request.getParameter("code");
		System.out.println("리뷰로 넘어오는 코드-->"+code);
		int x = Integer.parseInt(code);
		String categoryId = request.getParameter("categoryId");
		ItemDao itemdao = new ItemDao();
		
		try {
			Item item = itemdao.findByCode(x);
			
			request.setAttribute("item", item);
			request.setAttribute("found", found);
			//response.sendRedirect(request.getServletContext().getContextPath()+"/view/detail?code="+categoryId);
			request.getRequestDispatcher("/WEB-INF/private/review.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String writer = request.getParameter("writer");
		String score = request.getParameter("score");
		int y = Integer.parseInt(score);
		String message = request.getParameter("message");
		String code = request.getParameter("code");
		int x = Integer.parseInt(code);
		ReviewDao reviewdao = new ReviewDao();
		
		
//		System.out.println(writer);
//		System.out.println(score);
//		System.out.println(message);
		
		try {
			Review one = new Review();
			one.setId(0);
			one.setWriter(writer);
			one.setScore(y);
			one.setMessage(message);
			one.setItemCode(x);
			
			reviewdao.save(one);
			response.sendRedirect(request.getServletContext().getContextPath()+"/view/detail?code="+code);
			//request.getRequestDispatcher("/WEB-INF/private/review.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
