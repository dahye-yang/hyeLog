package model.vo;

import java.sql.Date;

public class Point {
	private int no;
	private String userId;
	private String alt;
	private int point;
	private Date pointDate;
	
	public Point() {
		super();
	}

	public Point(int no, String userId, String alt, int point, Date pointDate) {
		super();
		this.no = no;
		this.userId = userId;
		this.alt = alt;
		this.point = point;
		this.pointDate = pointDate;
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

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getPointDate() {
		return pointDate;
	}

	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}
	
	
}
