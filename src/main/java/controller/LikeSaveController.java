package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LikeDao;
import model.vo.Like;
import model.vo.User;

@WebServlet("/private/like")
public class LikeSaveController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 찜하기 하트 채워지고~~? save 시키기
		User found = (User) request.getSession().getAttribute("logonUser");
		String itemcode2 = request.getParameter("itemcode"); // 추가할 아이템코드 넘겨받기
		System.out.println("넘겨져온 아이템 코드--->" + itemcode2);

		LikeDao likedao = new LikeDao();

		try {
			Like one = likedao.findByUserIdAndItemCode(found.getId(), Integer.parseInt(itemcode2));
			if (one != null) {		
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('동일한 상품이 찜콩되어있어요 :)');history.go(-1);</script>");
				w.flush();
				w.close();
			}else {
		        Like two = new Like(0, found.getId(), Integer.parseInt(itemcode2));
				boolean result = likedao.save(two);
				System.out.println("찜콩하기 저장결과-->" + result);
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('찜콩하기 저장완료 :)');history.go(-1);</script>");
				w.flush();
				w.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
