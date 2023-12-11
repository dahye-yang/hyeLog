package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;
import model.dao.CouponStorageDao;
import model.dao.PointDao;
import model.vo.Cart;
import model.vo.CouponStorage;
import model.vo.Point;
import model.vo.User;

@WebServlet("/private/order/cartmain")
public class CartMainController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니 메인 페이지
		User found = (User)request.getSession().getAttribute("logonUser");
		CartDao cartdao = new CartDao();
		PointDao pointDao = new PointDao();
		CouponStorageDao couponStorageDao = new CouponStorageDao();
		
		try {
			List<Cart> list = cartdao.findAllByUserId(found.getId());
			
			Point one = pointDao.findPointSumByUserId(found.getId());
			System.out.println(found.getId()+"님의 포인트 합계는--->"+one.getPointsum()); 
			
			List<CouponStorage> couponList = couponStorageDao.findCouponByUser(found.getId());
			int size = couponList.size();
			
			request.setAttribute("size", size); //쿠폰 갯수 체크
			request.setAttribute("one", one); // 가용포인트 체크
			request.setAttribute("list", list);	
			request.getRequestDispatcher("/WEB-INF/private/order/cartmain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
