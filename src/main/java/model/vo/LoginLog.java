package model.vo;

import java.sql.Date;

public class LoginLog {

	private int id;
	private String userId;
	private Date logAt;

	
	
	public LoginLog() {
		super();
	}

	
	public LoginLog(String userId, Date logAt) {
		super();
		this.userId = userId;
		this.logAt = logAt;
	}


	public LoginLog(int id, String userId, Date logAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.logAt = logAt;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLogAt() {
		return logAt;
	}

	public void setLogAt(Date logAt) {
		this.logAt = logAt;
	}

}
