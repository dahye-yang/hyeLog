package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ItemDao;
import model.vo.Item;
import model.vo.User;

@WebServlet("/view/main")
public class MainController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User found = (User)request.getSession().getAttribute("logonUser");
		ItemDao itemdao = new ItemDao();
		boolean result = false;
		
		if(found == null) {
			result = false;
		}else {
			result = true;
		}
		request.setAttribute("result", result);
		
		try {
			Item item = (Item)itemdao.findByCode(1);
			request.setAttribute("item", item);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
	}
}
