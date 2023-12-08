package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.QnaDao;
import model.vo.Qna;

@WebServlet("/board/qna")
public class QnaController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QnaDao qanDao = new QnaDao();
		try {
			List<Qna> list = qanDao.findAllWithCategory();
			
						
			request.setAttribute("list", list);

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			request.getRequestDispatcher("/WEB-INF/board/qna.jsp").forward(request, response);
		
		}

	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/board/qna.jsp").forward(request, response);
	}
}
