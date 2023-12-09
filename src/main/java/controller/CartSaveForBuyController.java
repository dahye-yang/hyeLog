package controller;

import java.io.IOException;

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
			Cart one = new Cart();
			one.setId(0);
			one.setUserId(found.getId());
			one.setCartPiece(Integer.parseInt(piece));
			one.setItemCode(Integer.parseInt(itemCode));
			
			cartDao.save(one);
			
			Cart two = cartDao.findByUserIdAndItemCode(found.getId(), Integer.parseInt(itemCode));
			
		//	System.out.println("구매하려고 넘어온 아이템이름--->"+two.getItem().getName());
		//	System.out.println("개수-->"+two.getCartPiece());
			request.setAttribute("found", found);
			request.setAttribute("two", two);
			request.setAttribute("piece", x);
			request.setAttribute("code", y);
			request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);
			//response.sendRedirect(request.getServletContext().getContextPath()+"/private/order/buy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
