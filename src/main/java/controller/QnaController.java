package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.QnaCategoryDao;
import model.vo.QnaCategory;
import model.vo.User;

@WebServlet("/board/qna")
public class QnaController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User one = (User)request.getSession().getAttribute("logonUser");
		boolean exist = false;
		if (one ==null) {
			exist = true;
			request.setAttribute("exist", exist);
		}
		
		QnaCategoryDao qnaCategoryDao = new QnaCategoryDao();
		 try {
			List<QnaCategory> list = qnaCategoryDao.findAll();
			request.setAttribute("list", list);
			
			for(QnaCategory a : list) {
				System.out.println(a.getName());
			}
			request.getRequestDispatcher("/WEB-INF/board/qnaForm.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("/WEB-INF/board/qnaForm.jsp").forward(request, response);
	}
}
