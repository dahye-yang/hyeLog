package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Notice;
import model.vo.Review;

public class NoticeDao {
	public boolean save(Notice one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into notices values(NOTICES_SEQ.NEXTVAL,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getTitle());
			pstmt.setString(2, one.getMessage());
			pstmt.setDate(3, one.getNoticeDate());
			pstmt.setInt(4, one.getViewCnt());

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
	
	public List<Notice> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM Notices ORDER BY ID DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Notice> list = new ArrayList<Notice>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String message = rs.getString("message");
				Date noticeDate = rs.getDate("notice_date");
				int viewCnt = rs.getInt("view_cnt");

				Notice one = new Notice(id, title, message, noticeDate, viewCnt);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(Notice one) throws ClassNotFoundException {
		boolean result = false;
		// 0. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "UPDATE notices SET TITLE=?, MESSAGE=?, NOTICE_DATE =?, VIEW_CNT=? WHERE ID=? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getTitle());
			pstmt.setString(2, one.getMessage());
			pstmt.setDate(3, one.getNoticeDate());
			pstmt.setInt(4, one.getViewCnt());
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

			String sql = "DELETE FROM notices WHERE ID = ? ";
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
