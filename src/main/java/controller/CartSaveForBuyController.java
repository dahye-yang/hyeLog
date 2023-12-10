package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;
import model.vo.Cart;
import model.vo.User;

@WebServlet("/private/order/cartsaveforbuy")
public class CartSaveForBuyController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String piece = request.getParameter("itempiece");
		int x = Integer.parseInt(piece);
		String itemCode = request.getParameter("itemcode");
		int y = Integer.parseInt(itemCode);
		User found = (User)request.getSession().getAttribute("logonUser");
		
		//System.out.println("piece --> "+ piece);
		//System.out.println("itemcode --> "+ itemCode);
		
		CartDao cartDao = new CartDao();
		
		
		try {
			// 넘어온 구매수량이 0일경우는 
			if(x == 0) {
				response.setContentType("text/html; charset=utf-8");
		        PrintWriter w = response.getWriter();
		        w.write("<script>alert('필수 옵션을 선택해주세요!');history.go(-1);</script>");
		        w.flush();
		        w.close();
			}else {
				Cart one = new Cart();
				one.setId(0);
				one.setUserId(found.getId());
				one.setCartPiece(x);
				one.setItemCode(y);
				
				cartDao.save(one);
				
				Cart two = cartDao.findByUserIdAndItemCode(found.getId(), y);
				
				//	System.out.println("구매하려고 넘어온 아이템이름--->"+two.getItem().getName());
				//	System.out.println("개수-->"+two.getCartPiece());
				request.setAttribute("found", found);
				request.setAttribute("two", two);
				request.setAttribute("piece", x);
				request.setAttribute("code", y);
				request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);
				//response.sendRedirect(request.getServletContext().getContextPath()+"/private/order/buy");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
