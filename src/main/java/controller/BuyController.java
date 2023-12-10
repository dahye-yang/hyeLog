package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.BuyLogDao;
import model.dao.PointDao;
import model.dao.UserDao;
import model.vo.BuyLog;
import model.vo.Point;
import model.vo.User;

@WebServlet("/private/order/buy")
public class BuyController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/WEB-INF/private/order/buy.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 구매로그 save 성공 時 User balance update 및 point 적립
		String itemcode = request.getParameter("itemcode");
		String piece = request.getParameter("piece");
		String price = request.getParameter("price");
		String point = request.getParameter("point");
		double point2 = Double.valueOf(point);
		int point3 = (int)point2;
		
		int price2 = Integer.parseInt(price);
		
		User found = (User)request.getSession().getAttribute("logonUser");
		Date now = new Date(System.currentTimeMillis());
		
		BuyLogDao buylogdao = new BuyLogDao();
		UserDao userdao = new UserDao();
		PointDao pointdao = new PointDao();
		
		try {
			BuyLog one = new BuyLog(0, found.getId(), Integer.parseInt(price),
					now, Integer.parseInt(piece),Integer.parseInt(itemcode));
			
			
			// user의 잔액이 0이거나 구매금액 마이너스 금액이 0이하이면...
			if(found.getBalance() < 0 || found.getBalance()-price2 < 0) {
				response.setContentType("text/html; charset=utf-8");
		        PrintWriter w = response.getWriter();
		        w.write("<script>alert('잔액이 부족합니다😥\n잔엑충전 해 주세요 🥰');history.go(-1);</script>");
		        w.flush();
		        w.close();
			}else {
				// 구매 Log 저장
				boolean result = buylogdao.save(one);
				System.out.println("구매로그 등록결과--->"+result);
				found.setBalance(found.getBalance()-price2);
				// 레벨업 조건 체크하기
				boolean result2 = userdao.update(found);
				System.out.println("유저 잔액변경 결과-->"+result2);
				Point two = new Point(0,found.getId(),"구매적립포인트!", point3, now);
				pointdao.save(two);
				//주문조회 만든 후 그곳으로 이동하도록 바꾸기
				response.sendRedirect(request.getServletContext().getContextPath()+"/view/main");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
