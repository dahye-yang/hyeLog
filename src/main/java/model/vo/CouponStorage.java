package model.vo;

import java.sql.Date;

public class CouponStorage {
	private int no;
	private String userId;
	private Date expDate;
	private int couponId;
	
	private Coupon coupon;
	
	public CouponStorage() {
		super();
	}
	
	
	public CouponStorage(int no, String userId, Date expDate, int couponId) {
		super();
		this.no = no;
		this.userId = userId;
		this.expDate = expDate;
		this.couponId = couponId;
	}
	
	

	public CouponStorage(int no, String userId, Date expDate, int couponId, Coupon coupon) {
		super();
		this.no = no;
		this.userId = userId;
		this.expDate = expDate;
		this.couponId = couponId;
		this.coupon = coupon;
	}


	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}


	public Coupon getCoupon() {
		return coupon;
	}


	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	
	
	
	
	
	
}
