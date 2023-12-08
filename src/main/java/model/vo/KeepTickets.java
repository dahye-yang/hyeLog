package model.vo;

import java.sql.Date;

public class KeepTickets {
	private String code;
	private String userId;
	private Date expiredAt;
	
	public KeepTickets() {
		super();
	}
	
	public KeepTickets(String code, String userId, Date expiredAt) {
		super();
		this.code = code;
		this.userId = userId;
		this.expiredAt = expiredAt;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getExqiredAt() {
		return expiredAt;
	}
	public void setExqiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}
	
	
}
