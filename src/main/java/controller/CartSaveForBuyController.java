package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;
import model.dao.CouponDao;
import model.dao.CouponStorageDao;
import model.dao.PointDao;
import model.vo.Cart;
import model.vo.CouponStorage;
import model.vo.Point;
import model.vo.User;

@WebServlet("/private/order/cartsaveforbuy")
public class CartSaveForBuyController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String piece = request.getParameter("itempiece");
		//String piece = request.getParameter("itempiece");
		int x = Integer.parseInt(piece);
		String itemCode = request.getParameter("itemcode");
		int y = Integer.parseInt(itemCode);
		User found = (User)request.getSession().getAttribute("logonUser");
		
		//System.out.println("piece --> "+ piece);
		//System.out.println("itemcode --> "+ itemCode);
		
		CartDao cartDao = new CartDao();
		PointDao pointDao = new PointDao();
		CouponStorageDao couponStorageDao = new CouponStorageDao();
		
		
		try {
			// 넘어온 구매수량이 0일경우는 
			if(x == 0) {
				response.setContentType("text/html; charset=utf-8");
		        PrintWriter w = response.getWriter();
		        w.write("<script>alert('필수 옵션을 선택해주세요!');history.go(-1);</script>");
		        w.flush();
		        w.close();
			}else {
				
				//카트를 찾아서 만약 같은 아이템코드의 카트가 있다면 개수만 + / 없다면 카트에 저장하기
				Cart three = cartDao.findByUserIdAndItemCode(found.getId(), y);
				
				if(three == null) {
					Cart one = new Cart();
					one.setId(0);
					one.setUserId(found.getId());
					one.setCartPiece(x);
					one.setItemCode(y);
					
					int cartId = cartDao.save(one);
					//카트 save 후 저장된 cartid
					request.setAttribute("cartId", cartId);
					String[] a = new String[] {String.valueOf(cartId)};
					List<Cart> list = cartDao.findByUserIdAndCartId(found.getId(), a);
					request.setAttribute("list", list);
					int sum=0;
					for(Cart b : list) {
						sum = x * b.getItem().getPrice();
						}
					//쿠폰
					List<CouponStorage> couponList =  couponStorageDao.findCouponByUser(found.getId());
					request.setAttribute("couponList", couponList);
					request.setAttribute("sum", sum);
				}else {
					three.setCartPiece(three.getCartPiece()+1);
					cartDao.update(three);
					String[] a = new String[] {String.valueOf(three.getId())};
					List<Cart> list = cartDao.findByUserIdAndCartId(found.getId(), a);
					request.setAttribute("list", list);
					request.setAttribute("cartId", three.getId());
					int sum=0;
					for(Cart b : list) {
						sum = x * b.getItem().getPrice();
						}
					request.setAttribute("sum", sum);
					//쿠폰
					List<CouponStorage> couponList =  couponStorageDao.findCouponByUser(found.getId());
					
					for(CouponStorage bb : couponList) {
						bb.getCoupon().getAlt();
						bb.getCoupon().getDiscount();
						bb.getExpDate();
					}
					
					request.setAttribute("couponList", couponList);
				}
				
				
				
				//Cart two = cartDao.findByUserIdAndItemCode(found.getId(), y);
				Point four = pointDao.findPointSumByUserId(found.getId());
				System.out.println("포인트합계-->"+four.getPointsum());
				/*
				 * System.out.println("구매하려고 넘어온 아이템이름--->"+two.getItem().getName());
				 * System.out.println("개수-->"+two.getCartPiece());
				 */
				
				request.setAttribute("point", four);
				request.setAttribute("found", found);
				//request.setAttribute("two", two);
				request.setAttribute("piece", x);
				request.setAttribute("code", y); // 아이템코드
				request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
