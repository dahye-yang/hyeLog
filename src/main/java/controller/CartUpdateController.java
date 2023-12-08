package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;
import model.vo.Cart;

@WebServlet("/private/order/cartupdate")
public class CartUpdateController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] cartId = request.getParameterValues("cartId");
		String[] cartPiece = request.getParameterValues("cartPiece");	
		//String[] itemCode = request.getParameterValues("itemCode");
		
		for(int i =0; i < cartPiece.length; i++) {
			System.out.println(cartPiece[i]);
		}
		
		CartDao cartDao = new CartDao();
		
		try {
			List<Cart> list = new ArrayList<Cart>();
			Cart one = new Cart();
			for(int i =0; i < cartId.length; i++) {
				one.setId(Integer.parseInt(cartId[i]));
				one.setCartPiece(Integer.parseInt(cartPiece[i]));
				//one.setItemCode(Integer.parseInt(itemCode[i]));
				list.add(one);
			}
			for(Cart g : list) {
				boolean result = cartDao.update(g);
				System.out.println("결과--->"+result);
			}
			
			response.sendRedirect(request.getServletContext().getContextPath()+"/private/order/cartmain");
			//request.getRequestDispatcher("/WEB-INF/private/order/cartupdate.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
