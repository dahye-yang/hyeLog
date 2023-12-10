package model.vo;

public class Coupon {
	private int id;
	private String alt;
	private int discount;
	
	public Coupon() {
		super();
	}
	
	
	public Coupon(String alt, int discount) {
		super();
		this.alt = alt;
		this.discount = discount;
	}


	public Coupon(int id, String alt, int discount) {
		super();
		this.id = id;
		this.alt = alt;
		this.discount = discount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
}
