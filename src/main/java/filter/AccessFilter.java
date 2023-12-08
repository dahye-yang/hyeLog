package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/private/*")
public class AccessFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String uri = request.getRequestURI();

		if (request.getSession().getAttribute("logonUser") == null) { // 로그인한 사용자만 접근 허용하는
			String path = request.getServletContext().getContextPath();// 만약 필터가 없다면 통과됨.
			request.getSession().setAttribute("url", uri);
			response.sendRedirect(path + "/view/login"); // 더이상 작동시킬 필터가 없으면 해당 페이지 접근허용.
			return;
		}
		chain.doFilter(request, response);
	}

}
