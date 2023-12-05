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

	// 아이템 list로 전체 뽑기
//	public List<Item> findAll() throws ClassNotFoundException {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//
//		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
//				"1111");) {
//
//			String sql = "select i.*,g.no,g.item_img from items i join item_imgs g on i.code=g.code";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//
//			ResultSet rs = pstmt.executeQuery();
//			List<Item> list = new ArrayList<Item>();
//			while (rs.next()) {
//				int id = rs.getInt("id"); // rs.getInt("code");
//				String name = rs.getString("name"); // rs.getString("name");
//		
//
//				Item one = new Item(id, name);
//
//				list.add(one);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

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
					img.setItemImg(rs.getString("item_img"));
					list.add(img);
					
					item.setItemImg(list);
				}else {
					ItemImg imgs = new ItemImg();
					imgs.setItemImg(rs.getString("item_img"));
					item.getItemImg().add(imgs);
				}

			}
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
