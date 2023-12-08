package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Cart;
import model.vo.Item;


public class CartDao {

	public boolean save(Cart one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into carts values(CARTS_SEQ.NEXTVAL,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getUserId());
			pstmt.setInt(2, one.getCartPiece());
			pstmt.setInt(3, one.getItemCode());
			
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

	public List<Cart> findAllByUserId(String str) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT a.id "
							 + ",a.user_id"
							 + ",a.cart_piece"
							 + ",a.item_code"
							 + ",a.name"
							 + ",a.price"
							 + ",a.category_id"
							 + ",b.item_img"
						+ " from (select c.id"
							          +",c.user_id"
							          +",c.cart_piece"
							          +",c.item_code"
							          +",i.name"
							          +",i.price"
							          +",i.category_id"
							     +" from carts c join items i"
							      +" on c.item_code=i.code) a join"
							      						    +" (select ROW_NUMBER() OVER(PARTITION BY c.code ORDER BY c.code) as num, c.*"
							      						      +" from item_imgs c) b"
						+" on a.item_code = b.code"
					    +" AND b.num = 1"
						+" where a.user_id =?"
					    +" order by a.id desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);

			ResultSet rs = pstmt.executeQuery();
			List<Cart> list = new ArrayList<Cart>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				int cartPiece= rs.getInt("cart_piece");
				int itemCode = rs.getInt("item_code");
				
				Item x = new Item();
				x.setCode(itemCode);
				x.setName(rs.getString("name"));
				x.setPrice(rs.getInt("price"));
				x.setCategoryId(rs.getInt("category_id"));
				x.setImage(rs.getString("item_img"));

				Cart one = new Cart(id, userId, cartPiece, itemCode, x);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(Cart one) throws ClassNotFoundException {
		boolean result = false;
		// 0. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "UPDATE carts SET CART_PIECE=? WHERE ID=? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, one.getCartPiece());
			pstmt.setInt(2, one.getId());

			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean deletById(int id) throws ClassNotFoundException {
		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "DELETE FROM carts WHERE ID = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

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
}
