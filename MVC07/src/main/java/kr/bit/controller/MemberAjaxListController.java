package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberAjaxListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DB에서 회원리스트 가져오기
		MemberDAO dao = new MemberDAO(); 
		List<MemberVO> list = dao.memberList(); 
		
		//arrayList를 json으로 변경하는 API -> GSON
		Gson g = new Gson();
		String json = g.toJson(list);
		System.out.println(json); //[{  }, {  }, {  }....]
		
		//$.ajax ---> json
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
		return null;
	}

}
