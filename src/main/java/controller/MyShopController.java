package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CouponStorageDao;
import model.dao.PointDao;
import model.dao.UserDao;
import model.vo.CouponStorage;
import model.vo.Point;
import model.vo.User;
@WebServlet("/private/myshop")
public class MyShopController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User one = (User) request.getSession().getAttribute("logonUser");
		UserDao userDao = new UserDao();
		
		try {
			
			//레벨 사진 뽑기
			User found = userDao.findUserWithlevelImgByUserId(one.getId());
			request.getSession().setAttribute("logonUser", found);	
			
			//point적립금 합계 출력
			PointDao p = new PointDao();
			List<Point> list = p.findPointListByUserId(one.getId());
		
			int sum = 0;
			for(Point l : list) {
				sum = sum + l.getPoint();
			}
			request.setAttribute("sum", sum);
			
			//쿠폰개수 뽑기
			CouponStorageDao couponStorageDao = new CouponStorageDao();

			List<CouponStorage> l = couponStorageDao.findCouponByUser(one.getId());
			int c = l.size();
			request.setAttribute("count", c);
			
			
			

			//request.setAttribute("user", one);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/private/myshop.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/private/myshop.jsp").forward(request, response);
	}
}
