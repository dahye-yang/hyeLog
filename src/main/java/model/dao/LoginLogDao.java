package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.LoginLog;


public class LoginLogDao {
	
		public boolean save(LoginLog log) throws ClassNotFoundException {
			boolean result = false;
			// 1. 데이터 베이스 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
					"1111");) {
				// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
				String sql = "INSERT INTO LOGIN_LOGS VALUES(LOGIN_LOGS_SEQ.NEXTVAL, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, log.getUserId());
				pstmt.setDate(2, log.getLogAt());
				
				int n = pstmt.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴.
				if (n == 1) {
					result = true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	
	

	//하루 첫날 포인트 지급을 위해서
	public List<LoginLog> findByLogAt(Date date) throws ClassNotFoundException {
		// log_at 오름차순
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {
			
			String sql = "SELECT * FROM LOGIN_LOGS WHERE TO_CHAR(LOG_AT,'YYYYMMDD') = TO_CHAR(?,'YYYYMMDD') ORDER BY LOG_AT ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, date);

			ResultSet rs = pstmt.executeQuery();
			List<LoginLog> logs = new ArrayList<LoginLog>();
			while (rs.next()) {
				LoginLog log = new LoginLog();
				log.setId(rs.getInt("id"));
				log.setUserId(rs.getString("user_id"));
				log.setLogAt(rs.getDate("log_at"));
				logs.add(log);
			}
			return logs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
