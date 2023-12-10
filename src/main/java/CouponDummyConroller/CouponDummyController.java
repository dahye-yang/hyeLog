//package CouponDummyConroller;
//
//import java.io.IOException;
//import java.sql.Date;
//import java.util.Random;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import model.dao.CouponDao;
//import model.dao.CouponStorageDao;
//import model.vo.Coupon;
//import model.vo.CouponStorage;
//
//@WebServlet("/dummy/make")
//public class CouponDummyController extends HttpServlet {
//
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String[] desc = new String[] {};
//		CouponStorageDao dao = new CouponStorageDao();
//		CouponDao coupon = new CouponDao();
//		Random r = new Random();
//		try {
//			for (int cnt = 1; cnt <= 3; cnt++) {
//
//				CouponStorage c = new CouponStorage();
//				c.setNo(cnt);
//				c.setUserId("totoro");
//				c.setCouponId(r.nextInt(1, 4));
//			
//				int month = r.nextInt(1, 12);
//				int day = r.nextInt(1, 30);
//				String dateStr = "2024-" + 
//						String.format("%02d", month) + "-" + String.format("%02d", day);
//				c.setExpDate(Date.valueOf(dateStr));
//				
//				Coupon cp= new Coupon();
//				cp.setDiscount(coupon.findDiscountById(c.getCouponId()));
//				
//			dao.save(c);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}