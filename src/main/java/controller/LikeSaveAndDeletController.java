package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LikeDao;
import model.vo.Like;
import model.vo.User;

@WebServlet("/private/like")
public class LikeSaveAndDeletController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 찜하기 하트 채워지고~~? save 시키기
		User found = (User)request.getSession().getAttribute("logonUser");
		String itemcode = request.getParameter("itemcode");
		
		LikeDao likedao = new LikeDao();
		
		try {
			Like one = likedao.findByUserIdAndItemCode(found.getId(), Integer.parseInt(itemcode));
			if(one == null) {
				Like two = new Like(0,found.getId(),Integer.parseInt(itemcode));
				boolean result = likedao.save(two);
				System.out.println("찜콩하기 저장결과-->"+result);
			}else {
				boolean result2 = likedao.deletByItemCode(Integer.parseInt(itemcode));
				System.out.println("찡콩하기 삭제결과-->"+result2);
			}
			response.sendRedirect(request.getServletContext().getContextPath()+"/view/main");
			//request.getRequestDispatcher("/WEB-ING/view/detail.jsp").forward(request, response); ///?
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
