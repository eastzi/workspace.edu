package kr.board.entity;

import lombok.Data;

@Data
public class Board {
	/*
	 * entity는 게시판 구조를 변수로 생성
	 * 해당 클래스명과 테이블명을 동일하게 작성
	 */
	private int idx; //번호
	private String memID; //회원아이디 추가
	private String title; //제목
	private String content; //내용
	private String writer; //작성자
	private String indate; //작성일
	private int count; //조회수
	
	/*
	setter, getter를 이제는 lombok api로 대신함.
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	*/
	
	
}
