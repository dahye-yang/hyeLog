package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Level;
import model.vo.Notice;
import model.vo.Qna;
import model.vo.QnaCategory;
import model.vo.User;

public class QnaDao {
	
	public boolean save(Qna one) 
			throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "insert into QNAS values (QNAS_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
			System.out.println("sql = " + sql);
			PreparedStatement pstmst = conn.prepareStatement(sql);

			pstmst.setString(1, one.getWriter());
			pstmst.setString(2, one.getTitle());
			pstmst.setString(3, one.getQuestion());
			pstmst.setDate(4, one.getRegiDate());
			pstmst.setInt(5, one.getViewCnt());
			pstmst.setString(6, one.getAnswer());
			pstmst.setInt(7, one.getOpenType());
			pstmst.setInt(8, one.getQnaCate());
			
			int n = pstmst.executeUpdate(); 
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
	
			String sql = "UPDATE USERS SET Writer=?,Title=?, Question=?, RegiDate=?,ViewCnt=?, Answer=?,openType=?, QnaCate=? WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, one.getWriter());
			pstmt.setString(2, one.getTitle());
			pstmt.setString(3, one.getQuestion());
			pstmt.setDate(4, one.getRegiDate());
			pstmt.setInt(5, one.getViewCnt());
			pstmt.setString(6, one.getAnswer());
			pstmt.setInt(7, one.getOpenType());
			pstmt.setInt(8, one.getQnaCate());
			pstmt.setInt(9, one.getId());
			
			int n = pstmt.executeUpdate();
			if (n == 1) {
				result = true;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}
	
	public List<Qna> findAll() throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM QNAS";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Qna> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String writer = rs.getString(2);
				String title = rs.getString(3);
				String question = rs.getString(4);
				Date regiDate = rs.getDate(5);
				int viewCnt  = rs.getInt(6);
				String answer = rs.getString(7);
				int openType  = rs.getInt(8);
				int qnaCate = rs.getInt(9);
				Qna one = new Qna(id, writer, title, question, regiDate, viewCnt, answer, openType, qnaCate);
				list.add(one);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Qna> findAllWithCategory() throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select Q.* , c.name from Qnas Q left join Qna_categorys C on Q.Qna_cate = C.id";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Qna> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String writer = rs.getString(2);
				String title = rs.getString(3);
				String question = rs.getString(4);
				Date regiDate = rs.getDate(5);
				int viewCnt  = rs.getInt(6);
				String answer = rs.getString(7);
				int openType  = rs.getInt(8);
				int qnaCate = rs.getInt(9);
				
					QnaCategory q = new QnaCategory();
					q.setName(rs.getString("name"));
					
				Qna one = new Qna(id, writer, title, question, regiDate, viewCnt, answer, openType, qnaCate, q);
				list.add(one);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public Qna findCategoryById(int id) throws ClassNotFoundException { 
		
		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "select Q.* , c.name from Qnas Q left join Qna_categorys C on Q.Qna_cate = C.id where q.id=?";
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			
			pstmt.setInt(1, id);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			if (rs.next()) {
				Qna qna = new Qna();
				qna.setId(rs.getInt("id")); 
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setRegiDate(rs.getDate("regi_Date"));
				qna.setViewCnt(rs.getInt("view_cnt"));
				qna.setAnswer(rs.getString("answer"));
				qna.setOpenType(rs.getInt("opentype"));
				qna.setQnaCate(rs.getInt("qna_cate"));
				
				QnaCategory q = new QnaCategory();
					
					q.setName(rs.getString("name"));
				
					qna.setQnaCategory(q);
					
				return qna;
				
			} else {
				return null;
			}
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

			String sql = "DELETE FROM Qnas WHERE id = ? ";
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



	
	
