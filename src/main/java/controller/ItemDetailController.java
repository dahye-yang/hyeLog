package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ItemDao;
import model.vo.Item;

@WebServlet("/view/detail")
public class ItemDetailController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = (String)request.getParameter("code"); //item 클릭시 넘겨주는 item.code
		int x = Integer.parseInt(code);
		ItemDao itemdao = new ItemDao();
		try {
			// 넘겨받은 item.code로 이미지/상세내용/가격 등 
			Item item = itemdao.findByCode(x);
			
			
			request.setAttribute("item", item);
			request.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
