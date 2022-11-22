package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@RequestMapping("/board")
@RestController //@responseBody + @controller -> ajax 통신용(JSON) controller
public class BoardRestController {
	
	@Autowired
	private BoardMapper boardMapper; 
	
	//@RequestMapping("/boardList.do")
	//@ResponseBody
	@GetMapping("/all")
	public List<Board> boardList() { // VO, DTO 타입의 객체를 리턴
		List<Board> list = boardMapper.getLists(); 
		
		return list; 
		// 객체를 리턴한다 == JSON 데이터 형식으로 변환하여 리턴한다. -> jackson-databind API 이용
	}
	
	//@RequestMapping("/boardInsert.do")
	//@ResponseBody
	@PostMapping("/new") // /board/new
	public void boardInsert(Board vo) {
		boardMapper.boardInsert(vo);
	}
	
	//@RequestMapping("/boardDelete.do")
	//@ResponseBody
	@DeleteMapping("/{idx}")
	public void boardDelete(@PathVariable("idx") int idx) {
		boardMapper.boardDelete(idx);
	}
	
	//@RequestMapping("/boardUpdate.do")
	//@ResponseBody
	@PutMapping("/update")
	public void boardUdpate(@RequestBody Board vo) { //JSON데이터를 받기 위해 @RequestBody 필요
		boardMapper.boardUpdate(vo);
	}
	
	//@RequestMapping("/boardContent.do")
	//@ResponseBody
	@GetMapping("/{idx}")
	public Board boardContent(@PathVariable("idx") int idx) {
		Board vo = boardMapper.boardContent(idx);
		return vo; // vo객체가 JSON으로 변경되어 데이터 전송
	}
	
	//@RequestMapping("/boardCount.do")
	//@ResponseBody
	@PutMapping("/count/{idx}")
	public Board boardCount(@PathVariable("idx") int idx) {
		// 1. 조회수 누적시키기
		boardMapper.boardCount(idx);
		
		// 2. 조회수가 수정된 값 가져오기(누적 완료된)
		Board vo = boardMapper.boardContent(idx);
		return vo;
	}
}
