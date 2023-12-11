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

@WebServlet("/private/reviewfix")
public class ReviewFixController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 리뷰 수정페이지
		String writer = request.getParameter("writer");
		String reviewId = request.getParameter("reviewId");
		int id = Integer.parseInt(reviewId);
		String code = request.getParameter("code");

		//categoryId .. 수정완료 후 그 아이템 상세페이지로 가게끔하기위함.
		String categoryId = request.getParameter("categoryId");
		ReviewDao reviewdao = new ReviewDao();
		ItemDao itemdao = new ItemDao();
		
		try {
			Review one =  reviewdao.findById(id);
			Item item = itemdao.findByCode(Integer.parseInt(code));
			
			request.setAttribute("item", item);
			request.setAttribute("categoryId",categoryId );
			request.setAttribute("writer", writer);
			request.setAttribute("one", one);
			request.getRequestDispatcher("/WEB-INF/private/reviewfix.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리뷰 수정하기
		String id = request.getParameter("id");
		String writer = request.getParameter("writer");
		String score = request.getParameter("score");
		String message = request.getParameter("message");
		String itemCdoe = request.getParameter("itemCode");
		
		String categoryId = request.getParameter("categoryId");
		
		ReviewDao reviewdao = new ReviewDao();
		try {
			Review one = new Review();
			one.setId(Integer.parseInt(id));
			one.setWriter(writer);
			one.setScore(Integer.parseInt(score));
			one.setMessage(message);
			one.setItemCode(Integer.parseInt(itemCdoe));
			
			reviewdao.update(one);
			
			response.sendRedirect(request.getServletContext().getContextPath()+"/view/detail?code="+itemCdoe);
			//request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
