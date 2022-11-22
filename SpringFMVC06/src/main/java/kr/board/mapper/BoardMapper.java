package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Board;

@Mapper // -> myBatis API
public interface BoardMapper {
	public List<Board> getLists(); //전체리스트 가져오기
	public void boardInsert(Board vo); //boardForm.jsp 파라메터 수집하기
	public Board boardContent(int idx); //게시판 상세보기
	public void boardDelete(int idx); //게시판 삭제하기
	public void boardUpdate(Board vo); //게시판 수정하기 
	public void boardCount(int idx); //게시판 조회수 증가
}
