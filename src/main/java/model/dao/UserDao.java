package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.User;

public class UserDao {
public boolean save(User one) throws ClassNotFoundException {
		
		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into Users values(?,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getId());
			pstmt.setString(2, one.getPassword());
			pstmt.setInt(3, one.getBalance());
			pstmt.setString(4, one.getNickName());
			pstmt.setInt(5, one.getLevelId());
			
			int n = pstmt.executeUpdate();

			if (n == 1) {
				result = true;
				System.out.println("executeUpdate ==> "+ n);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public User findById(String idKey) throws ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM USERS WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idKey);			

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id"); 
				String password = rs.getString("password"); 
				int balance = rs.getInt("balance"); 
				String nickName = rs.getString("nickName");
				int levelId = rs.getInt("level_Id");
				
			
				return new User(id, password, balance, nickName, levelId);
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public boolean update(User one) throws ClassNotFoundException {
		boolean result = false;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {
	
			String sql = "UPDATE USERS SET PASSWORD=?,BALANCE=?, NICKNAME=?, LEVEL_ID=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getPassword());
			pstmt.setInt(2, one.getBalance());
			pstmt.setString(3, one.getNickName());
			pstmt.setInt(4, one.getLevelId());
			pstmt.setString(5, one.getId());
			
			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}
	
	
	public boolean deletById(String playerid) throws ClassNotFoundException {
		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "DELETE FROM USERS WHERE ID = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playerid);			

			int n = pstmt.executeUpdate();
			
			if(n == 1) {
				result =  true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result =  false;
		}
		return result;
	}
}
