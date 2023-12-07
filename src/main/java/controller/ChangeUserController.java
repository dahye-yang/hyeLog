package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.vo.User;

@WebServlet("/private/changeUser")
public class ChangeUserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User one = (User)request.getSession().getAttribute("logonUser");
		int n = one.getPassword().length();
		String result = "*".repeat(n);
		request.setAttribute("result", result);
	
		request.getRequestDispatcher("/WEB-INF/private/changeUser.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User one = (User)request.getSession().getAttribute("logonUser");
		UserDao userdao = new UserDao();
		boolean result = false;
	
		//비밀번호 변경 
		String password = request.getParameter("PW");
		String newPassWord = request.getParameter("newPW");
		if(one.getPassword().equals(password)) {
			User userP = new User(one.getId(),newPassWord,one.getBalance(),one.getNickName(),one.getLevelId());
			try {
				result = userdao.update(userP);
				System.out.println(result);
				if(result) {
					response.sendRedirect(request.getServletContext().getContextPath() + "/private/myshop");
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//닉네임 변경
		String nickName = request.getParameter("nickName");
		User userN = new User(one.getId(),one.getPassword(),one.getBalance(),nickName,one.getLevelId());
		try {
			result = userdao.update(userN);
			if(result) {
				response.sendRedirect(request.getServletContext().getContextPath() + "/private/myshop");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
