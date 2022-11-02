package kr.bit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.bit.model.MemberVO;

@Mapper // MyBatis로 Spring에서 인식 -> SqlSessionFactory + SqlSession을 자동으로 불러옴.
public interface MemberMapper {
	// interface method name = SQL id=" "
	
	public List<MemberVO> memberList(); //SQL id="memberList"
	
	public int memberInsert(MemberVO vo); //SQL id="memberInsert"
	
	public int memberDelete(int num); //SQL id="memberDelete"
	
	public MemberVO memberContent(int num); //SQL id="memberContent"
	
	public int memberUpdate(MemberVO vo); //SQL id="memberUpdate"
}
