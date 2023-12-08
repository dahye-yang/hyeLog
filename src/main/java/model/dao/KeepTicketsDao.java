package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.KeepTickets;

public class KeepTicketsDao {
	public boolean save(KeepTickets one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into keep_tickets values(?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getCode());
			pstmt.setString(2, one.getUserId());
			pstmt.setDate(3, one.getExqiredAt());

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

	public KeepTickets findByCode(String keycode) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM keep_tickets WHERE CODE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keycode);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String code = rs.getString("code"); // rs.getInt("code");
				String userId = rs.getString("user_id"); // rs.getString("name");
				Date expiredAt = rs.getDate("expired_At"); // rs.getInt("price");

				return new KeepTickets(code, userId, expiredAt);
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
