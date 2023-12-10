package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PointDao;
import model.vo.Point;
import model.vo.User;
@WebServlet("/private/myshop/point")
public class PointController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User one = (User)request.getSession().getAttribute("logonUser");
		request.setAttribute("user", one);
		PointDao p = new PointDao();
		try {
			List<Point> list = p.findPointListByUserId(one.getId());
			request.setAttribute("list", list);
			
			int sum = 0;
			for(Point l : list) {
				sum += l.getPoint();
			}
			request.setAttribute("sum", sum);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/private/myshop/point.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/private/myshop/point.jsp").forward(request, response);
	}
}
