package filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter({ "/private/*" })
public class PrivateAccessFilter extends HttpFilter{
			
			@Override
			protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
					throws IOException, ServletException {
				
				String uri = request.getRequestURI();
				//System.out.println(" PrivateAccessFilter.doFilter at " + uri);
			
				if (request.getSession().getAttribute("logonUser") != null) {
					chain.doFilter(request, response); 
				} else {
					String path = request.getServletContext().getContextPath();
					response.sendRedirect(path+"/view/login");
				}
			}
		}


