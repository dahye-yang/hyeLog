package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.NoticeDao;
import model.vo.Notice;

@WebServlet("/board/noticefix")
public class NoticeFixController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공지사항 수정폼으로 보내주는 데이터
		String id = request.getParameter("id");
		int x = Integer.parseInt(id);
		NoticeDao noticedao = new NoticeDao();

		try {
			Notice one = noticedao.findById(x);

			request.setAttribute("noticeone", one);
			request.getRequestDispatcher("/WEB-INF/board/noticefix.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공지사항 수정(update)
		String id = request.getParameter("id");
		int idx = Integer.parseInt(id);
		String title = request.getParameter("title");
		String message = request.getParameter("message");

		Date now = new Date(System.currentTimeMillis());
		NoticeDao noticedao = new NoticeDao();

		try {
			Notice one = new Notice();
			one.setId(idx);
			one.setMessage(message);
			one.setTitle(title);
			one.setNoticeDate(now);

			boolean result = noticedao.update(one);
			System.out.println("공지사항 수정-->" + result);
			response.sendRedirect(request.getServletContext().getContextPath() + "/view/noticedetail?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
