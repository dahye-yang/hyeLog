package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
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

		boolean result = false;

		if (id == "" || id == null || password == "" || password == null || nickName == "" || nickName == null) {
			result = false;
		}

		try {
			User one = new User(id, password, 500000, nickName, 1);
			UserDao userdao = new UserDao();
			User found = userdao.findById(id);

			if (found != null) {
				request.getSession().setAttribute("error", true);
				request.getSession().setAttribute("found", found);
				response.sendRedirect(request.getServletContext().getContextPath() + "/view/join");
			} else {
				result = userdao.save(one);
				request.setAttribute("one", one);
				request.getSession().setAttribute("logonUser", one);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/WEB-INF/view/join.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
