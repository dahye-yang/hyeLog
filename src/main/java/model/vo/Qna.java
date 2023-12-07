package model.vo;

import java.sql.Date;

public class Qna {
	
	private int id;
	private String writer;
	private String title;
	private String question;
	private Date regiDate;
	private int viewCnt;
	private String answer;
	private int qnaCate;
	
	private QnaCategory qnaCategory;

	public Qna() {
		super();
	}

	public Qna(int id, String writer, String title, String question, Date regiDate, int viewCnt, String answer,
			int qnaCate) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.question = question;
		this.regiDate = regiDate;
		this.viewCnt = viewCnt;
		this.answer = answer;
		this.qnaCate = qnaCate;
	}

	public Qna(int id, String writer, String title, String question, Date regiDate, int viewCnt, String answer,
			int qnaCate, QnaCategory qnaCategory) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.question = question;
		this.regiDate = regiDate;
		this.viewCnt = viewCnt;
		this.answer = answer;
		this.qnaCate = qnaCate;
		this.qnaCategory = qnaCategory;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getQnaCate() {
		return qnaCate;
	}

	public void setQnaCate(int qnaCate) {
		this.qnaCate = qnaCate;
	}

	public QnaCategory getQnaCategory() {
		return qnaCategory;
	}

	public void setQnaCategory(QnaCategory qnaCategory) {
		this.qnaCategory = qnaCategory;
	}
	
	
	
	
	

}
