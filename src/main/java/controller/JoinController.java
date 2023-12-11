package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CouponStorageDao;
import model.dao.PointDao;
import model.dao.UserDao;
import model.vo.CouponStorage;
import model.vo.Point;
import model.vo.User;

@WebServlet("/view/join")
public class JoinController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");
		
		PointDao pointdao = new PointDao();
		Date now = new Date(System.currentTimeMillis());
		// 쿠폰
		LocalDate local = LocalDate.now();
		LocalDate aa = local.plusMonths(1);
		Date exp = Date.valueOf(aa); // 쿠폰사용기한 (한달후까지)
		CouponStorageDao coupon = new CouponStorageDao();
		
		boolean result = false;

		if (id == "" || id == null || password == "" || password == null || nickName == "" || nickName == null) {
			result = false;
			response.setContentType("text/html; charset=utf-8");
	        PrintWriter w = response.getWriter();
	        w.write("<script>alert('필수입력 요소들을 작성해주세요 :)');history.go(-1);</script>");
	        w.flush();
	        w.close();
		}

		try {
			User one = new User(id, password, 1000000, nickName, 1);
			UserDao userdao = new UserDao();
			User found = userdao.findById(id);

			if (found != null) {
				request.getSession().setAttribute("error", true);
				request.getSession().setAttribute("found", found);
				response.sendRedirect(request.getServletContext().getContextPath() + "/view/join");
			} else {
				result = userdao.save(one);
				Point x = new Point(0,one.getId(),"웰컴포인트!",3000,now);
				pointdao.save(x);
				CouponStorage y = new CouponStorage(0,id, exp, 1);
				coupon.save(y);
				//request.setAttribute("one", one); //계정을 새로 만들어서 set
				//request.getSession().setAttribute("logonUser", one);
				request.setAttribute("result", result);
				response.sendRedirect(request.getServletContext().getContextPath() + "/view/login");
				//request.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
