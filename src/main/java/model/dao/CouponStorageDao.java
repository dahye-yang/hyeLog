package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.Coupon;
import model.vo.CouponStorage;

public class CouponStorageDao {

	public boolean save(CouponStorage one) throws ClassNotFoundException {

		boolean result = false;
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = String.format("insert into coupon_storages values(coupon_storages_seq.NEXTVAL,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, one.getUserId());
			pstmt.setDate(2, one.getExpDate());
			pstmt.setInt(3, one.getCouponId());
			
			
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
	
	public List<CouponStorage> findCouponByUser(String id) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@3.34.199.133:1521:xe", "hyelog",
				"1111");) {

			String sql = "select s.* , c.alt, c.discount from coupon_storages s left join coupons C on s.coupon_id = C.id where s.user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			List<CouponStorage> list = new ArrayList<>();
			while (rs.next()) {
				int no = rs.getInt("no");
				String userId = rs.getString("user_id");
				Date expDate = rs.getDate("exp_date");
				int couponId = rs.getInt("couponId");
				
				String alt = rs.getString("alt");
				int discount = rs.getInt("discount");
				
				Coupon coupon = new Coupon(alt, discount);
				
				CouponStorage s = new CouponStorage(no, userId, expDate, couponId, coupon);
				
				list.add(s);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	}

	
