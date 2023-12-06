package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Category;

public class CategoryDao {

	public List<Category> findAll() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select * from categorys";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			List<Category> list = new ArrayList<Category>();
			while (rs.next()) {
				int id = rs.getInt("id"); 
				String name = rs.getString("name"); 

				Category one = new Category(id, name);

				list.add(one);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public Category findOne(String one) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select * from categorys where id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one);

			ResultSet rs = pstmt.executeQuery();
			Category cate = new Category();
			if (rs.next()) {
				cate.setId(rs.getInt("id"));
				cate.setName(rs.getString("name"));
				
			}
			return cate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
