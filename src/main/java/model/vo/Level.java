package model.vo;

public class Level {
	private int id;
	private String levelImg;
	
	public Level() {
		super();
	}
	public Level(int id, String levelImg) {
		super();
		this.id = id;
		this.levelImg = levelImg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLevelImg() {
		return levelImg;
	}
	public void setLevelImg(String levelImg) {
		this.levelImg = levelImg;
	}
	
	
}
