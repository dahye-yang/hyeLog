package model.vo;

public class ItemImg {
	private int no;
	private int code;
	private String itemimgUrl;
	
	public ItemImg() {
		super();
	}
		
	
	public ItemImg(int code, String itemimgUrl) {
		super();
		this.code = code;
		this.itemimgUrl = itemimgUrl;
	}


	public ItemImg(int no, int code, String itemimgUrl) {
		super();
		this.no = no;
		this.code = code;
		this.itemimgUrl = itemimgUrl;
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
	public String getitemimgUrl() {
		return itemimgUrl;
	}
	public void setitemimgUrl(String itemimgUrl) {
		this.itemimgUrl = itemimgUrl;
	}
	
	
}
