package model.vo;

import java.sql.Date;

public class Notice {
	private int id;
	private String title;
	private String message;
	private Date noticeDate;
	
	public Notice() {
		super();
	}
	public Notice(int id, String title, String message, Date noticeDate) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.noticeDate = noticeDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	
}
