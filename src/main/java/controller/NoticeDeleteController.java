package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.NoticeDao;

@WebServlet("/board/noticedelete")
public class NoticeDeleteController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제하기 누르면 notice의 id 받아와서 그걸로 삭제
		String id = request.getParameter("id");
		int x = Integer.parseInt(id);
		NoticeDao noticedao = new NoticeDao();
		
		try {
			boolean result = noticedao.deletById(x);
			System.out.println("공지사항 삭제결과-->"+result);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getServletContext().getContextPath()+"/view/noticemain");
	}
}
