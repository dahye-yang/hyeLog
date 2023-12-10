package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CouponStorageDao;
import model.vo.CouponStorage;
import model.vo.User;
@WebServlet("/private/myshop/coupon")
public class CouponController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User one = (User)request.getSession().getAttribute("logonUser");
		request.setAttribute("user", one);
		
		CouponStorageDao couponStorageDao = new CouponStorageDao();
		try {
			
			List<CouponStorage> list = couponStorageDao.findCouponByUser(one.getId());
			request.setAttribute("list", list);
			
			int c = list.size();
			request.setAttribute("count", c);
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/private/myshop/coupon.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/private/myshop/coupon.jsp").forward(request, response);
	}
}
