package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.vo.Coupon;

public class CouponDao {
		public Coupon findDiscountById(int num) throws ClassNotFoundException { 
		
		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "select discount from coupons where id =?";
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			
			pstmt.setInt(1, num);

			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setDiscount(rs.getInt("discount"));

				
				return coupon;
				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
