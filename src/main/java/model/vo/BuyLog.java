package model.vo;

import java.sql.Date;

public class BuyLog {
	private int id;
	private String userId;
	private int price;
	private Date buyDate;
	private int piece;
	private int itemCode;
	
	private Item item; 
	private ItemImg itemImg;
	private Cart cart;
	
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
	
	

	public BuyLog(int id, String userId, int price, Date buyDate, Cart cart) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.buyDate = buyDate;
		this.cart = cart;
	}

	public BuyLog(int id, int price, Date buyDate, int piece, int itemCode, Item item, ItemImg itemImg) {
		super();
		this.id = id;
		this.price = price;
		this.buyDate = buyDate;
		this.piece = piece;
		this.itemCode = itemCode;
		this.item = item;
		this.itemImg = itemImg;
	}

	public BuyLog(int id, String userId, int price, Date buyDate, int piece, int itemCode, Item item) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.buyDate = buyDate;
		this.piece = piece;
		this.itemCode = itemCode;
		this.item = item;
	}
	
	

	public BuyLog(int id, String userId, int price, Date buyDate, int piece, int itemCode, Item item, ItemImg itemImg) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.buyDate = buyDate;
		this.piece = piece;
		this.itemCode = itemCode;
		this.item = item;
		this.itemImg = itemImg;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemImg getItemImg() {
		return itemImg;
	}

	public void setItemImg(ItemImg itemImg) {
		this.itemImg = itemImg;
	}
	
	
	
}
