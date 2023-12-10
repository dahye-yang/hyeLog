package model.vo;

public class Like {
	private int id;
	private String userId;
	private int itemCode;
	
	private Item item;
	
	public Like() {
		super();
	}
	public Like(int id, String userId, int itemCode) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemCode = itemCode;
	}
	
	
	public Like(int id, String userId, int itemCode, Item item) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemCode = itemCode;
		this.item = item;
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
	
	
}
