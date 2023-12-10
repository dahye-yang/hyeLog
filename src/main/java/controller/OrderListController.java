package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BuyLogDao;
import model.vo.BuyLog;
import model.vo.User;
@WebServlet("/private/myshop/orderlist")
public class OrderListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("logonUser");
		String id = user.getId();
		BuyLogDao buyLogDao = new BuyLogDao();
		try {
			//구매내력 아이템과 연결시켜서 갖고 오는 리스트
			List<BuyLog> list = buyLogDao.findAllWithItemItemImgByUserId(id);
			System.out.println("가지고온 리스트 사이즈-->"+list.size());
			request.setAttribute("list", list);
			
			//날짜 중복 제거
//			for(BuyLog buylog : list) {
//				Stream<BuyLog> b = list.stream().distinct();
//				request.setAttribute("b", b);
//			}

			
			//합계 갖고오기
			int sum = 0;
			for(BuyLog b : list) {
				sum = sum + b.getPrice();
			}
			request.setAttribute("sum", sum);
			
			
			
//			ItemDao itemDao = new ItemDao();
//			//이미지 연결된 아이템 코드 갖고오기
//			for(BuyLog b : list) {
//				Item item = new Item();
//				item =itemDao.findByCode(b.getItemCode());
//				request.setAttribute("item", item);
//				
//			}
			
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/private/myshop/orderlist.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/private/myshop/orderlist.jsp").forward(request, response);
	}
}
