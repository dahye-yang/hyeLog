package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Item;
import model.vo.ItemImg;


public class ItemDao {



	public Item findByCode(int itemCode) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select i.*,g.no,g.item_img from items i join item_imgs g on i.code=g.code "
					+ "where i.code = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemCode);
			
			Item item = null;
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if(item == null) {
					item = new Item();
					item.setCode(rs.getInt("code"));
					item.setName(rs.getString("name"));
					item.setPrice(rs.getInt("price"));
					item.setDetail(rs.getString("detail"));
					item.setCategoryId(rs.getInt("category_id"));
					
					List<ItemImg> list = new ArrayList<>();
					ItemImg img = new ItemImg();
					img.setitemimgUrl(rs.getString("item_img"));
					list.add(img);
					
					item.setItemImg(list);
				}else {
					ItemImg imgs = new ItemImg();
					imgs.setitemimgUrl(rs.getString("item_img"));
					item.getItemImg().add(imgs);
				}
			}
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	 // 카테고리 click 하면 category_id parameter로 넘겨주어서 해당 카테고리 item 가져오기 
	public List<Item> findByCategoryId(String one) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select * from items where category_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one);

			ResultSet rs = pstmt.executeQuery();
			List<Item> list = new ArrayList<Item>();
			while (rs.next()) {
				Item item = new Item();
				item.setCode(rs.getInt("code"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setDetail(rs.getString("detail"));

				list.add(item);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	// 해당 카테고리 item의 code로 itme_img 가지고와서 List<item> 에 셋팅
	public List<ItemImg> findByImg(int one) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select * from item_imgs where code=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, one);

			ResultSet rs = pstmt.executeQuery();
			List<ItemImg> list = new ArrayList<ItemImg>();
			while (rs.next()) {
				ItemImg item = new ItemImg();
				item.setitemimgUrl(rs.getString("item_img"));

				list.add(item);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<Item> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM items";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Item> list = new ArrayList<Item>();
			
			while (rs.next()) {
				Item item = new Item();
				item.setCode(rs.getInt("code"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setDetail(rs.getString("detail"));

				list.add(item);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
