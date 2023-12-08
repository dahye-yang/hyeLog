package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.NoticeDao;
import model.vo.Notice;

@WebServlet("/board/notice")
public class NoticeSaveController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지사항 입력 폼 접근
		Date now = new Date(System.currentTimeMillis());
		
		request.setAttribute("now", now);
		request.getRequestDispatcher("/WEB-INF/board/notice.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지사항 저장
		String a = request.getParameter("regidate");
		//System.out.println("파라미터로 넘어온 날짜 string --> "+a);
		Date now = Date.valueOf(a);
		
		String title = request.getParameter("title");
		String message = request.getParameter("message");
		
		NoticeDao noticedao = new NoticeDao();
		
		try {
			Notice one = new Notice();
			one.setId(0);
			one.setMessage(message);
			one.setTitle(title);
			one.setNoticeDate(now);
			
			int createdId = noticedao.save(one);
			
			
			//등록 완료되면 해당 공지사항 상세페이지로 가게끔
			// notice 이 id를 경로를 물려줘서 상세페이지로 들어가게끔.... (?id='여기다가 넣을걸 ... setting 할 수있을깡?'
			response.sendRedirect(request.getServletContext().getContextPath()+"/view/noticedetail?id="+createdId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
