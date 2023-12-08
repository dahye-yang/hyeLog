package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.NoticeDao;
import model.vo.Notice;

@WebServlet("/view/noticedetail")
public class NoticeDetailController extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticeId = request.getParameter("id");
		int x = Integer.parseInt(noticeId);
		NoticeDao noticedao = new NoticeDao();
		
		try {
			Notice one = noticedao.findById(x);
			
			request.setAttribute("one", one);
			request.getRequestDispatcher("/WEB-INF/view/noticedetail.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
