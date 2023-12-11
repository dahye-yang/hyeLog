package model.vo;

public class User {
	private String id;
	private String password;
	private int balance;
	private String nickName;
	private int levelId;
	private int useMoney;
	
	private Level level;
	
	
	public User() {
		super();
	}
	public User(String id, String password, int balance, String nickName, int levelId) {
		super();
		this.id = id;
		this.password = password;
		this.balance = balance;
		this.nickName = nickName;
		this.levelId = levelId;
	}
	
	
	public User(String id, String password, int balance, String nickName, int levelId, int useMoney, Level level) {
		super();
		this.id = id;
		this.password = password;
		this.balance = balance;
		this.nickName = nickName;
		this.levelId = levelId;
		this.useMoney = useMoney;
		this.level = level;
	}
	
	
	public User(String id, String password, int balance, String nickName, int levelId, int useMoney) {
		super();
		this.id = id;
		this.password = password;
		this.balance = balance;
		this.nickName = nickName;
		this.levelId = levelId;
		this.useMoney = useMoney;
	}
	public User(String id, String password, int balance, String nickName, int levelId, Level level) {
		super();
		this.id = id;
		this.password = password;
		this.balance = balance;
		this.nickName = nickName;
		this.levelId = levelId;
		this.level = level;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
		}
	public int getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(int useMoney) {
		this.useMoney = useMoney;
	}
	
	
}
