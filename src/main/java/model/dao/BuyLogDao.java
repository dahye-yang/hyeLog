package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.BuyLog;




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
	
	//주문조회
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
}
