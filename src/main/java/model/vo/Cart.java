package model.vo;

import java.util.List;

public class Cart {
	
	private int id;
	private String userId;
	private int cartPiece;
	private int itemCode;
	
	private List<Item> items;
	private Item item; 
	
	public Cart() {
		super();
	}

	public Cart(int id, String userId, int cartPiece, int itemCode) {
		super();
		this.id = id;
		this.userId = userId;
		this.cartPiece = cartPiece;
		this.itemCode = itemCode;
	}

	public Cart(int id, int cartPiece, int itemCode) {
		super();
		this.id = id;
		this.cartPiece = cartPiece;
		this.itemCode = itemCode;
	}

	public Cart(int id, String userId, int cartPiece, int itemCode, Item item) {
		super();
		this.id = id;
		this.userId = userId;
		this.cartPiece = cartPiece;
		this.itemCode = itemCode;
		this.item = item;
	}

	public Cart(int id, String userId, int cartPiece, int itemCode, List<Item> items) {
		super();
		this.id = id;
		this.userId = userId;
		this.cartPiece = cartPiece;
		this.itemCode = itemCode;
		this.items = items;
	}
	
	

	public Cart(int cartPiece, int itemCode) {
		super();
		this.cartPiece = cartPiece;
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

	public int getCartPiece() {
		return cartPiece;
	}

	public void setCartPiece(int cartPiece) {
		this.cartPiece = cartPiece;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
	
}
