package controller;


import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.LoginLogDao;
import model.dao.PointDao;
import model.dao.UserDao;
import model.vo.LoginLog;
import model.vo.Point;
import model.vo.User;

@WebServlet("/view/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPassword = request.getParameter("loginPassword");

		UserDao userDao = new UserDao();
		try {

			User found = userDao.findById(loginId);
			if (found != null && found.getPassword().equals(loginPassword)) {
				request.getSession().setAttribute("logonUser", found);

				response.sendRedirect(request.getServletContext().getContextPath() + "/view/main");

				// 첫 접속시 포인트 주기
				LoginLogDao loginLogDao = new LoginLogDao();
				Date now = new Date(System.currentTimeMillis());
				List<LoginLog> logs = loginLogDao.findByLogAt(now);

				boolean pointResult = false; // 포인트 지급 성공 여부
				// id 있는지 찾는 과정
				LoginLog pick = null;
				for (LoginLog p : logs) {
					if (p.getUserId().equals(found.getId())) {
						pick = p;
						break;
					}
				}
				if (pick == null) {
					PointDao pointDao = new PointDao();
					Point x = new Point(0,found.getId(),"출석포인트!",100,now);
					pointDao.save(x);
					
					pointResult = true; // 포인트 지급 성공 여부
				}
				request.setAttribute("pointResult", pointResult);

				//출석체크 포인트 한번만 지급해줘야하기 때문에
				LoginLog log = new LoginLog(loginId, now); // 시퀀스때문에 생성자 2개짜리 만들어놈.
				loginLogDao.save(log);
			} else {
				request.setAttribute("error", true);
				request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
