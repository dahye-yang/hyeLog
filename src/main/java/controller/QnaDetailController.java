package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.QnaDao;
import model.vo.Qna;
@WebServlet("/board/qnaDetail")
public class QnaDetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int num = Integer.parseInt(id);
		
		QnaDao qnaDao = new QnaDao();
		try {
			Qna target = qnaDao.findCategoryById(num);
			request.setAttribute("target", target);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/board/qnaDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/board/qnaDetail.jsp").forward(request, response);
	}
}
