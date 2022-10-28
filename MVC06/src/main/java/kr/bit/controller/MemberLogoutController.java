package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLogoutController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctx = request.getContextPath(); 
		// 로그아웃 - 세션을 가져오서 세션제거
		// 1. 세션제거하는 방법
		// (1) 강제종료
		request.getSession().invalidate();
		
		// (2) 브라우저 종료 -> SESSIONID가 브라우저쿠키에 저장되기 때문
		
		// (3) 세션이 종료되기까지 기다리는 것 -> SESSIONTIMEOUT = 30분(1800초) -> 톰켓서버에 기본설정되어 있음.
		
		// 2. 세션종료시 되돌아갈 페이지
		return "redirect:" + ctx + "/memberList.do";
	}

}
