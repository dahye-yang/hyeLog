package controller;

import java.io.IOException;
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

@WebServlet("/product/categorylist")
public class CategoryController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 카테고리 클릭하고 넘어가는 화면
		String x = request.getParameter("categoryId");
		ItemDao itemdao = new ItemDao();
		CategoryDao categorydao = new CategoryDao();
		
		
		
		try {
			// 카테고리 배너 띄우기 위한 코드
			List<Category> categorys = categorydao.findAll();
			request.setAttribute("categorys", categorys);
			//클릭한 카테고리 별 아이템 찾기위한 코드
			Category category = categorydao.findOne(x);
			List<Item> itemlist = itemdao.findByCategoryId(x);
			
			for(int i = 0; i < itemlist.size(); i++) {
				List<ItemImg> itemImgList = itemdao.findByImg(itemlist.get(i).getCode());
				itemlist.get(i).setItemImg(itemImgList);
				
			}
			
			// item list toString으로 확인
			//System.out.println(itemlist.toString());
			request.setAttribute("categorys", categorys);
			request.setAttribute("category", category);
			request.setAttribute("itemlist", itemlist);
			request.getRequestDispatcher("/WEB-INF/product/categorylist.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();	
		}
		
	}
}
