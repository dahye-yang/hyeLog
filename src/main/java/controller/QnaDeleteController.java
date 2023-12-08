package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.QnaDao;
import model.dao.UserDao;
import model.vo.User;
@WebServlet("/board/qnaDelete")
public class QnaDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/board/qna.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		int i = Integer.parseInt(id);

		System.out.println(i);
		
		QnaDao qnaDao = new QnaDao();
		boolean deleteResult= false;
	
				try {
					qnaDao.deletById(i);
					deleteResult= true;
					System.out.println(deleteResult);
					
					if(deleteResult) {
						response.sendRedirect(request.getServletContext().getContextPath() + "/board/qna");
					}
					
					request.setAttribute("deleteResult", deleteResult);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		request.getRequestDispatcher("/WEB-INF/board/qna.jsp");
	}

}
