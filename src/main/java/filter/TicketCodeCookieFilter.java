package filter;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.KeepTicketsDao;
import model.dao.UserDao;
import model.vo.KeepTickets;
import model.vo.User;

@WebFilter("/*")
public class TicketCodeCookieFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			Cookie[] cookies = request.getCookies();
			
			Cookie founds = null;
			
			//만약 쿠키를 갖고 있다면, 쿠키를 찾아. 
			if (cookies != null && cookies.length > 0) {
				for (Cookie c : cookies) {
					String str = c.getName();
					if (str.equals("ticketCode")) {
						founds = c;
						break;
					}
				}
			}
			if (founds != null) {
				KeepTicketsDao ticketdao = new KeepTicketsDao();
				UserDao userdao = new UserDao();

				KeepTickets ticket = ticketdao.findByCode(founds.getValue());
				Date now = new Date(System.currentTimeMillis());

				if (ticket == null || ticket.getExqiredAt().after(now)) {
					User found = userdao.findById(ticket.getUserId());
					request.getSession().setAttribute("logonUser", found);
				}
			}

			chain.doFilter(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
