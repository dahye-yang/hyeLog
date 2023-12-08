package model.vo;

import java.sql.Date;

public class BuyLog {
	private int id;
	private String userId;
	private int price;
	private Date buyDate;
	private int piece;
	private int itemCode;
	
	public BuyLog() {
		super();
	}

	public BuyLog(int id, String userId, int price, Date buyDate, int piece, int itemCode) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.buyDate = buyDate;
		this.piece = piece;
		this.itemCode = itemCode;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	
	
}
