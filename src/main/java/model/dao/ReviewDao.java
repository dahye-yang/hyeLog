package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Category;
import model.vo.Review;

public class ReviewDao {

	public boolean save(Review one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into reviews values(REVIEWS_SEQ.NEXTVAL,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getWriter());
			pstmt.setInt(2, one.getScore());
			pstmt.setString(3, one.getMessage());
			pstmt.setInt(4, one.getItemCode());

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

	public List<Review> findByItemCode(String one) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select * from reviews where item_code =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one);

			ResultSet rs = pstmt.executeQuery();
			List<Review> list = new ArrayList<Review>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				int score = rs.getInt("score");
				String message = rs.getString("message");

				Review x = new Review(id, writer, score, message);

				list.add(x);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Review> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM REVIEWS ORDER BY ID DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Review> list = new ArrayList<Review>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				int score = rs.getInt("score");
				String message = rs.getString("message");
				int itemCode = rs.getInt("item_code");

				Review one = new Review(id, writer, score, message, itemCode);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(Review one) throws ClassNotFoundException {
		boolean result = false;
		// 0. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "UPDATE reviews SET WRITER=? ,SCORE =? , MESSAGE=?, ITEM_CODE=? WHERE ID=? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getWriter());
			pstmt.setInt(2, one.getScore());
			pstmt.setString(3, one.getMessage());
			pstmt.setInt(4, one.getItemCode());
			pstmt.setInt(5, one.getId());


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

			String sql = "DELETE FROM reviews WHERE ID = ? ";
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
	
	public Review findById(int idcode) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM reviews WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idcode);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {				
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				int score = rs.getInt("score");
				String message = rs.getString("message");
				int itemCode = rs.getInt("item_code");
				
				return new Review(id, writer, score, message, itemCode);
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
