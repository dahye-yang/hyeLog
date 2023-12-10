package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Cart;
import model.vo.Item;
import model.vo.Like;

public class LikeDao {
	
	public boolean save(Like one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into likes values(LIKES_SEQ.NEXTVAL,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getUserId());
			pstmt.setInt(2, one.getItemCode());

			int n = pstmt.executeUpdate();

			if (n == 1) {
				result = true;
				System.out.println("executeUpdate ==> " + n);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public boolean deletByItemCode(int code) throws ClassNotFoundException {
		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "DELETE FROM likes WHERE Item_code = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);

			int n = pstmt.executeUpdate();

			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public Like findByUserIdAndItemCode(String user , int itemcode) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM likes WHERE user_id = ? and item_code =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setInt(2, itemcode);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				int itemCode = rs.getInt("item_code");


				return new Like(id, userId, itemCode);
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<Like> findAllByUserId(String str) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select a.id"
						    +",a.user_id"
						    +",a.item_code"
						    +",a.name"
						    +",a.price"
						    +",a.category_id"
						    +",b.item_img"
					    +" from (select c.id"
						    	      +",c.user_id"
						    	      +",c.item_code"
						    	      +",i.name"
						    	      +",i.price"
						    	      +",i.category_id"
						    	+"  from likes c join items i "
						    	  +"on c.item_code= i.code) a join (select ROW_NUMBER() OVER(PARTITION BY c.code ORDER BY c.code ) as num, c.* "
						    	  								   +"from item_imgs c) b "
						   +"on a.item_code = b.code "
						   +"and b.num = 1 "
						   +"where a.user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);

			ResultSet rs = pstmt.executeQuery();
			List<Like> list = new ArrayList<Like>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				int itemCode = rs.getInt("item_code");

				Item x = new Item();
				x.setCode(itemCode);
				x.setName(rs.getString("name"));
				x.setPrice(rs.getInt("price"));
				x.setCategoryId(rs.getInt("category_id"));
				x.setImage(rs.getString("item_img"));

				Like one = new Like(id, userId, itemCode, x);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
