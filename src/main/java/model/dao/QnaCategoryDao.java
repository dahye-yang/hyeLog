package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.QnaCategory;

public class QnaCategoryDao {

	public List<QnaCategory> findAll() throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "SELECT * FROM QNA_CATEGORYS";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<QnaCategory> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);

				QnaCategory one = new QnaCategory(id, name);
				list.add(one);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}