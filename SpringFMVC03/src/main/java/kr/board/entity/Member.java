package kr.board.entity;

import lombok.Data;

// 회원가입 엔티티
@Data
public class Member {
	private int memIdx;
	private String memID;
	private String memPassword;
	private String memName;
	private int memAge;
	private String memGender;
	private String memEmail;
	private String memProfile;
}
