package model.vo;

public class ItemImg {
	private int no;
	private int code;
	private String itemImg;
	
	public ItemImg() {
		super();
	}
		
	
	public ItemImg(int code, String itemImg) {
		super();
		this.code = code;
		this.itemImg = itemImg;
	}


	public ItemImg(int no, int code, String itemImg) {
		super();
		this.no = no;
		this.code = code;
		this.itemImg = itemImg;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getItemImg() {
		return itemImg;
	}
	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}
	
	
}
