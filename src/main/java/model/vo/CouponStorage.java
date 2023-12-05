package model.vo;

import java.sql.Date;

public class CouponStorage {
	private int no;
	private String userId;
	private Date expDate;
	
	public CouponStorage() {
		super();
	}
	public CouponStorage(int no, String userId, Date expDate) {
		super();
		this.no = no;
		this.userId = userId;
		this.expDate = expDate;
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
	
	
	
	
}
