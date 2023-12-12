package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LikeDao;

@WebServlet("/private/likedelete")
public class LikeDeleteController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 찜하기 삭제하기
		//User found = (User) request.getSession().getAttribute("logonUser");
		String itemcode = request.getParameter("deleteNo"); // 삭제할 아이템코드 넘겨받기

		LikeDao likedao = new LikeDao();
		String url = request.getServletContext().getContextPath()+"/private/likemain";
		try {
			boolean result2 = likedao.deletByItemCode(Integer.parseInt(itemcode));
			System.out.println("찡콩하기 삭제결과-->" + result2);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>alert('삭제됐습니다 :)');location.href='"+url+"';</script>");
			w.flush();
			w.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
