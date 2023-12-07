package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ItemDao;
import model.dao.ReviewDao;
import model.vo.Item;
import model.vo.Review;

@WebServlet("/view/reviewmain")
public class ReviewMainController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewDao reviewdao = new ReviewDao();
		ItemDao itemdao = new ItemDao();
		
		try {
			List<Review> list = reviewdao.findAll();
			List<Integer> code = new ArrayList<Integer>();
			
			for(Review a : list) {
				code.add(a.getItemCode());
			}
			List<Item> items = new ArrayList<>(); // 리뷰데이터의 아이템들을 담아놓은 리스트
			for(Integer x : code) {
				items.add(itemdao.findByCode(x));
			}
			for(int i =0; i < list.size(); i++){ // review객체에 item객체 넣는작업
				list.get(i).setItem(items.get(i));
			}
			
			//System.out.println(list.toString());
			
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/view/reviewmain.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
