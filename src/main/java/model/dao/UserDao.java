package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.Level;
import model.vo.User;

public class UserDao {
	
	public boolean save(User one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into Users values(?,?,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getId());
			pstmt.setString(2, one.getPassword());
			pstmt.setInt(3, one.getBalance());
			pstmt.setString(4, one.getNickName());
			pstmt.setInt(5, one.getLevelId());
			pstmt.setInt(6, one.getUseMoney());

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
				int useMoney = rs.getInt("use_money");
				

				return new User(id, password, balance, nickName, levelId ,useMoney);
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

			String sql = "UPDATE USERS SET PASSWORD=?,BALANCE=?, NICKNAME=?, LEVEL_ID=?, use_money=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getPassword());
			pstmt.setInt(2, one.getBalance());
			pstmt.setString(3, one.getNickName());
			pstmt.setInt(4, one.getLevelId());
			pstmt.setInt(5, one.getUseMoney());
			pstmt.setString(6, one.getId());

			int n = pstmt.executeUpdate();
			System.out.println("유저 업데이트 성공했닝??-->" + n);
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

			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public User findUserWithlevelImgByUserId(String key) throws ClassNotFoundException { 
		
		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "select u.*,l.level_img from users u left join levels l on u.level_id = l.id where u.id=?";
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			
			pstmt.setString(1, key);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			if (rs.next()) {
				User user = new User();
				user.setId( rs.getString("id")); 
				user.setPassword(rs.getString("password"));
				user.setBalance(rs.getInt("balance"));
				user.setNickName(rs.getString("nickname"));
				user.setLevelId(rs.getInt("level_id"));
				
				Level l = new Level();
					
					l.setLevelImg(rs.getString("level_img"));
				
				user.setLevel(l);
				
				return user;
				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
