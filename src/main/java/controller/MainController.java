package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CategoryDao;
import model.dao.ItemDao;
import model.vo.Category;
import model.vo.Item;
import model.vo.ItemImg;
import model.vo.User;

@WebServlet("/view/main")
public class MainController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User found = (User)request.getSession().getAttribute("logonUser");
		ItemDao itemdao = new ItemDao();
		CategoryDao categorydao = new CategoryDao();
		boolean result = false;
		// 로그인 유무 체크-> login/logout 화면 분리
		if(found == null) {
			result = false;
		}else {
			result = true;
		}
		request.setAttribute("result", result);
		List<String> img = new ArrayList<String>();
		try {
			//메인화면에 아이템 나열
			Item item = (Item)itemdao.findByCode(1);
			List<ItemImg> list = item.getItemImg();
			for(ItemImg a : list) {		
				img.add(a.getitemimgUrl());
			}
			request.setAttribute("img", img);
			request.setAttribute("item", item);
			
			//카테고리 배너 생성
			List<Category> categorys = categorydao.findAll();
			request.setAttribute("categorys", categorys);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
	}
}
