package model.vo;

public class Review {
	private int id;
	private String writer;
	private int score;
	private String message;
	private int itemCode;
	
	private Item item;
	
	public Review() {
		super();
	}

	public Review(int id, String writer, int score, String message, int itemCode) {
		super();
		this.id = id;
		this.writer = writer;
		this.score = score;
		this.message = message;
		this.itemCode = itemCode;
	}
	
	

	public Review(int id, String writer, int score, String message, int itemCode, Item item) {
		super();
		this.id = id;
		this.writer = writer;
		this.score = score;
		this.message = message;
		this.itemCode = itemCode;
		this.item = item;
	}

	
	
	public Review(int id, String writer, int score, String message) {
		super();
		this.id = id;
		this.writer = writer;
		this.score = score;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	@Override
	public String toString() {
		return "Review [id=" + id + ", writer=" + writer + ", score=" + score + ", message=" + message + ", itemCode="
				+ itemCode + ", item=" + item + "]";
	}
	
	
}
