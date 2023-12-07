package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.vo.Qna;
import model.vo.User;



public class QnaDao {
	
	public boolean save(Qna one) 
			throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "insert into QNAS values (QNAS_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
			System.out.println("sql = " + sql);
			PreparedStatement pstmst = conn.prepareStatement(sql);

			pstmst.setString(1, one.getWriter());
			pstmst.setString(2, one.getTitle());
			pstmst.setString(3, one.getQuestion());
			pstmst.setDate(4, one.getRegiDate());
			pstmst.setInt(5, one.getViewCnt());
			pstmst.setString(6, one.getAnswer());
			pstmst.setInt(7, one.getQnaCate());
			
			int n = pstmst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴. ==> 성공하면 1을 도출하고 실패하면 erorr 등장.
			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	
	public boolean update(Qna one) throws ClassNotFoundException {
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

}
