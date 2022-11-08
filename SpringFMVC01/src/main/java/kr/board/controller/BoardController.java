package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	//boardMapper와 POJO 연결(WEB Layer와 Persistence Layer 연결)
	@Autowired
	private BoardMapper mapper;
	
	// 게시판 리스트 요청
	@RequestMapping("/boardList.do") // -> HandlerMapping
	public String boardList(Model model) {
		/* 1. 파라미터 수집하기 -> BoardMapper DI로 대신함.
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("게시판실습");
		vo.setContent("게시판실습");
		vo.setWriter("이스트");
		vo.setIndate("2022-11-07");
		vo.setCount(0);
		
 		List<Board> list = new ArrayList<Board>();
		
 		list.add(vo);
		list.add(vo);
		list.add(vo);
 		*/
		
		// 1. DB에서 리스트 가져오기
		List<Board> list = mapper.getLists();
 		
		// 2. 객체바인딩
		model.addAttribute("list", list);
		
		// 3. nextPage
		return "boardList"; //boardList.jsp -> viewResolver -> forward
	}
	
	// 게시판 글쓰기
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm"; //boardForm.jsp
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { // 파라메터 수집 -> boardForm.jsp의 3가지 파라메터(title, content, writer)
		mapper.boardInsert(vo); // 게시판 등록
		return "redirect:/boardList.do";
	}
	
	// 게시판 상세보기
	@GetMapping("/boardContent.do") //@RequestParam은 옆의 변수명읻 동일하면 생략가능
	public String boardContent(@RequestParam("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		
		//조회수 증가
		mapper.boardCount(idx);
		
		model.addAttribute("vo", vo);
		return "boardContent"; //boardContent.jsp
	}
	
	// 게시판 삭제하기
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	
	// 게시판 수정하기
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);		
		return "boardUpdate"; //boardUpdate.jsp
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
		return "redirect:/boardList.do";
	}
	
}
