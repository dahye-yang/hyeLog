package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.NoticeDao;
import model.vo.Notice;

@WebServlet("/view/noticemain")
public class NoticeMainController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeDao noticedao = new NoticeDao();
		
		try {
			List<Notice> noticelist = noticedao.findAll();
			
			request.setAttribute("noticelist", noticelist);
			request.getRequestDispatcher("/WEB-INF/view/noticemain.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
