package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/private/logout")
public class LogoutController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		
		Cookie cookie = new Cookie("ticketCode", null); //값이 크게 의미가 없어서
		cookie.setPath(request.getServletContext().getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect(request.getServletContext().getContextPath() + "/view/main");
	}

}
