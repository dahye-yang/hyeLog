package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.QnaCategoryDao;
import model.dao.QnaDao;
import model.vo.Qna;
import model.vo.QnaCategory;
import model.vo.User;

@WebServlet("/board/qnaForm")
public class QnaFormController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User one = (User) request.getSession().getAttribute("logonUser");
		boolean exist = false;
		if (one == null) {
			exist = true;
			request.setAttribute("exist", exist);
		}
		request.setAttribute("one", one);
		System.out.println(one.getNickName());

		QnaCategoryDao qnaCategoryDao = new QnaCategoryDao();
		try {
			List<QnaCategory> list = qnaCategoryDao.findAll();
			request.setAttribute("list", list);

//			for (QnaCategory a : list) {
//				System.out.println(a.getName());
//			}
			request.getRequestDispatcher("/WEB-INF/board/qnaForm.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User one = (User) request.getSession().getAttribute("logonUser");
		
		String qnaType = request.getParameter("qnaType");
		String[] T = qnaType.split("\\.");
		System.out.println("받은 t값" + T.toString());
		String t = T[0];
		int qnaCate = Integer.parseInt(t);

		String writer = one.getNickName();

		String title = request.getParameter("title");

		String type = request.getParameter("check");
		int openType = Integer.parseInt(type);

		String question = request.getParameter("content");

		int viewCnt = 0;

		String answer = null;

		Date now = new Date(System.currentTimeMillis());

		System.out.println(now);

		Qna qna = new Qna(0, writer, title, question, now, viewCnt, answer, openType, qnaCate);
		QnaDao qnaDao = new QnaDao();
		boolean SaveResult = false;
	
			try {
				SaveResult = qnaDao.save(qna);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		if(SaveResult) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/board/qna");
		}else {
		request.getRequestDispatcher("/WEB-INF/board/qnaForm.jsp").forward(request, response);
	
	}
	}}
