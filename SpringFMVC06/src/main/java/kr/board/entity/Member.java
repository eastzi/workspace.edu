package kr.board.entity;

import java.util.List;

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
	private String memProfile; //사진정보
	private List<AuthVO> authList; // authList[0].auth, authList[1].auth, authList[2].auth
}
