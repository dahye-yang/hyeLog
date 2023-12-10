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

@WebServlet("/private/order/cart")
public class CartSaveController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String piece = request.getParameter("piece");
		String itemCode = request.getParameter("itemcode");
		User found = (User)request.getSession().getAttribute("logonUser");
		
		System.out.println("piece --> "+ piece);
		System.out.println("itemcode --> "+ itemCode);
		
		CartDao cartDao = new CartDao();
		
		try {
			//카트를 찾아서 만약 같은 아이템코드의 카트가 있다면 개수만 + / 없다면 카트에 저장하기
			Cart two = cartDao.findByUserIdAndItemCode(found.getId(), Integer.parseInt(itemCode));
			
			if(two == null) {
				Cart one = new Cart();
				one.setId(0);
				one.setUserId(found.getId());
				one.setCartPiece(Integer.parseInt(piece));
				one.setItemCode(Integer.parseInt(itemCode));
				
				cartDao.save(one);
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('장바구니에 담았어요 :)');history.go(-1);</script>");
				w.flush();
				w.close();
				
			}else {
				two.setCartPiece(two.getCartPiece()+1);
				cartDao.update(two);
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('장바구니에 담았어요 :)');history.go(-1);</script>");
				w.flush();
				w.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
}
