package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDao;
import model.dao.ItemDao;
import model.dao.ReviewDao;
import model.vo.Category;
import model.vo.Item;
import model.vo.Review;
import model.vo.User;

@WebServlet("/view/detail")
public class ItemDetailController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User found = (User) request.getSession().getAttribute("logonUser");
		String code = request.getParameter("code"); // item 클릭시 넘겨주는 item.code
		int x = Integer.parseInt(code);
		
		System.out.println("code--->"+ code);
		
		ItemDao itemdao = new ItemDao();
		ReviewDao reviewdao = new ReviewDao();

		boolean result = false;
		// 로그인 유무 체크-> login/logout 화면 분리
		if (found == null) {
			result = false;
		} else {
			result = true;
		}

		try {
		
			// 넘겨받은 item.code로 이미지/상세내용/가격 등
			Item item = itemdao.findByCode(x);

			// 리뷰가지고 오기
			List<Review> reviewlist = reviewdao.findByItemCode(code);

			request.setAttribute("reviewlist", reviewlist);
			request.setAttribute("result", result);
			request.setAttribute("found", found); // 리뷰작성 버트 차이를 두기위해서 로그인체크
			request.setAttribute("item", item);
			request.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
