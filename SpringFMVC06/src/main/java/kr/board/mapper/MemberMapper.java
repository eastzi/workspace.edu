package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.AuthVO;
import kr.board.entity.Board;
import kr.board.entity.Member;

@Mapper // -> myBatis API
public interface MemberMapper {
	public Member registerCheck(String memID); 
	public int register(Member m); // 회원등록 (성공1, 실패0)
	public Member memLogin(String username); // 로그인 체크
	public int memUpdate(Member mvo); //회원정보수정(성공1, 실패0)
	public Member getMember(String memID); 
	public void memProfileUpdate(Member mvo); //새로운 이미지 업데이트
	public void authInsert(AuthVO saveVO);
	public void authDelete(String memID); 
}
