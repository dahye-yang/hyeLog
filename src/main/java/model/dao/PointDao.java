package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.LoginLog;
import model.vo.Point;
import model.vo.User;

public class PointDao {
	public boolean save(Point one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into Points values(POINTS_SEQ.NEXTVAL,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getUserId());
			pstmt.setString(2, one.getAlt());
			pstmt.setInt(3, one.getPoint());
			pstmt.setDate(4, one.getPointDate());
	

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
	
	public boolean update(Point one) throws ClassNotFoundException {
		boolean result = false;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {
	
			String sql = "UPDATE USERS SET POINTS_SEQ.NEXTVAL, ALT=?, NICKNAME=?, POINT=?, POINT_DATE=? WHERE USER_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getAlt());
			pstmt.setInt(2, one.getPoint());
			pstmt.setDate(3, one.getPointDate());
			pstmt.setString(4, one.getUserId());
	
			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

public Point findPointByUserId(String idKey) throws ClassNotFoundException {
	
	Class.forName("oracle.jdbc.driver.OracleDriver");

	try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
			"1111");) {

		String sql = "SELECT * FROM POINTS WHERE USER_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idKey);			

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			int no = rs.getInt("no"); 
			String userId = rs.getString("user_id"); 
			String alt = rs.getString("alt"); 
			int point = rs.getInt("point");
			Date pointDate = rs.getDate("point_date");
			
			return new Point(no, userId, alt, point, pointDate);
		} else {
			return null;
		}

	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	
}
	
}



