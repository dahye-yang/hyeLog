package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDao;
import model.vo.Category;
import model.vo.User;

@WebServlet("/nav")
public class NavController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User found = (User)request.getSession().getAttribute("logonUser");
		CategoryDao categorydao = new CategoryDao();
		
		
		try {
			//카테고리 배너 생성
			List<Category> listx = categorydao.findAll();
			
			//System.out.println("size -->"+listx.size());
			//System.out.println(listx.toString());
			request.setAttribute("listx", listx);
			request.setAttribute("found", found);
			request.getRequestDispatcher("/WEB-INF/nav.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
