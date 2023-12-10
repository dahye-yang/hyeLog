package model.vo;

import java.util.List;

public class Item {
	private int code;
	private String name;
	private int price;
	private String detail;
	private int categoryId;
	
	private List<ItemImg> itemImg;
	private String image;
	
	public Item() {
		super();
	}
	
	
	public Item(String name, int price, int categoryId) {
		super();
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}


	public Item(int code, String name, int price, String detail, int categoryId) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.detail = detail;
		this.categoryId = categoryId;
	}
	
	

	public Item(String name, int price, int categoryId, String image) {
		super();
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.image = image;
	}
	public Item(int code, String name, int price, String detail, int categoryId, List<ItemImg> itemImg) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.detail = detail;
		this.categoryId = categoryId;
		this.itemImg = itemImg;
	}
	
	
	public Item(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public List<ItemImg> getItemImg() {
		return itemImg;
	}
	public void setItemImg(List<ItemImg> itemImg) {
		this.itemImg = itemImg;
	}
	@Override
	public String toString() {
		return "Item [code=" + code + ", name=" + name + ", price=" + price + ", detail=" + detail + ", categoryId="
				+ categoryId + ", itemImg=" + itemImg + "]";
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
