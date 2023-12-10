package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CartDao;
import model.vo.Cart;
import model.vo.User;

@WebServlet("/private/order/buycartall")
public class BuyAllAtCartController extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 장바구니에서 전체구매
		String[] itemCode2 = request.getParameterValues("itemcode");
//		for(String a : itemCode2) {
//			System.out.println("넘어온 아이템코드--?"+ a);
//		}

		User found = (User)request.getSession().getAttribute("logonUser");
		CartDao cartDao = new CartDao();
		
		List<Cart> list = new ArrayList<Cart>();
		int sum = 0;
		
		try {
			if(itemCode2 != null) {
				// 전체구매
				for(int i =0; i< itemCode2.length; i++) {
					Cart one = cartDao.findByUserIdAndItemCode(found.getId(), Integer.parseInt(itemCode2[i]));
					list.add(one);
				}
				System.out.println("담긴리스트 사이즈--->"+list.size());
	
				for(Cart a : list) {
					sum += a.getCartPiece() * a.getItem().getPrice();
				}
				System.out.println("구매할 예상총액은--?"+sum);
				// 개별구매
				
				request.setAttribute("sum", sum);
				request.setAttribute("list", list);
				request.setAttribute("found", found);
				
				request.getRequestDispatcher("/WEB-INF/private/order/buyall.jsp").forward(request, response);
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('구매할 상품이 없습니다 :)');history.go(-1);</script>");
				w.flush();
				w.close();
			}
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
