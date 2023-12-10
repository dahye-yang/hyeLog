package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.BuyLog;
import model.vo.Item;
import model.vo.ItemImg;

public class BuyLogDao {
	// 구매기록
	public boolean save(BuyLog one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into buy_logs values(BUY_LOGS_SEQ.NEXTVAL,?,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getUserId());
			pstmt.setInt(2, one.getPrice());
			pstmt.setDate(3, one.getBuyDate());
			pstmt.setInt(4, one.getPiece());
			pstmt.setInt(5, one.getItemCode());

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

	// 주문조회
	public List<BuyLog> findAllByUserId(String userid) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * from buy_logs where user_id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			ResultSet rs = pstmt.executeQuery();
			List<BuyLog> list = new ArrayList<BuyLog>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				int price = rs.getInt("price");
				Date buyDate = rs.getDate("buy_date");
				int piece = rs.getInt("piece");
				int itemCode = rs.getInt("item_code");

				BuyLog one = new BuyLog(id, userId, price, buyDate, piece, itemCode);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deletById(int id) throws ClassNotFoundException {
		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "DELETE FROM buy_logs WHERE ID = ? ";
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

	//주문조회페이지 만들기 위해(구매내력과 유저 연결)
	public List<BuyLog> findAllWithItemItemImgByUserId(String userid) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select a.*, b.item_img "
					+ "from (select c.id"
					+ ",c.user_id"
					+ ",c.price"
					+ ",c.buy_date"
					+ ",c.piece"
					+ ",c.item_code"
					+ ",i.name "
					+ "from buy_logs c join items i "
					+ "on c.item_code= i.code where user_id = ?) a join (select ROW_NUMBER() OVER(PARTITION BY c.code ORDER BY c.code) as num, c.* "
					+ "from item_imgs c) b "
					+ "on a.item_code = b.code "
					+ "and b.num = 1";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			ResultSet rs = pstmt.executeQuery();
			List<BuyLog> list = new ArrayList<BuyLog>();

			while (rs.next()) {
				BuyLog buyLog = new BuyLog();
				buyLog.setId(rs.getInt("id"));
				buyLog.setUserId(rs.getString("user_id"));
				buyLog.setPrice(rs.getInt("price"));
				buyLog.setBuyDate(rs.getDate("buy_date"));
				buyLog.setPiece(rs.getInt("piece"));
				buyLog.setItemCode(rs.getInt("item_code"));

				Item item = new Item();
				item.setName(rs.getString("name"));
				item.setImage(rs.getString("item_img"));
				buyLog.setItem(item);
			
				list.add(buyLog);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
	
//	//주문조회페이지 만들기 위해(구매내력과 유저 연결)
//	public List<BuyLog> findAllWithItemByUserId(String userid) throws ClassNotFoundException {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//
//		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
//				"1111");) {
//
//			String sql = "select b.*,i.name, i.category_id from buy_logs b left join items i on b.item_code = i.code where b.user_id=?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, userid);
//
//			ResultSet rs = pstmt.executeQuery();
//			List<BuyLog> list = new ArrayList<BuyLog>();
//
//			while (rs.next()) {
//				BuyLog buyLog = new BuyLog();
//				buyLog.setId(rs.getInt("id"));
//				buyLog.setUserId(rs.getString("user_id"));
//				buyLog.setPrice(rs.getInt("price"));
//				buyLog.setBuyDate(rs.getDate("buy_date"));
//				buyLog.setPiece(rs.getInt("piece"));
//				buyLog.setItemCode(rs.getInt("item_code"));
//
//				Item item = new Item();
//				item.setName(rs.getString("name"));
//				item.setCategoryId(rs.getInt("category_id"));
//
//				buyLog.setItem(item);
//
//				list.add(buyLog);
//
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//}






