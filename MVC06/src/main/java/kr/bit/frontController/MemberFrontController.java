package kr.bit.frontController;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;
import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("*.do") //확장자가 .do로 끝나는 것은 모두 받을 수 있음
public class MemberFrontController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// FrontController의 역할
		
		request.setCharacterEncoding("utf-8");
		//1. 클라이언트가 어떤 요청을 했는지 파악하기(요청을 하면 request 객체에 요청경로가 저장됨)
		String url = request.getRequestURI(); 
		System.out.println(url);
		
		String ctx = request.getContextPath();
		System.out.println(ctx);
		
		//실제로 요청한 명령이 무엇인지 파악하기 -> MVC04 이후 주소 뽑아내기
		String command = url.substring(ctx.length());
		System.out.println(command);
		
		// 2. 요청에 따른 분기작업 -> FrontController가 처리하기에는 버거운 작업들 -> Controller(POJO)가 처리
		Controller controller = null;
		String nextPage = null;
		
		// HandlerMapping
		/*
		if(command.equals("/memberList.do")) { //회원리스트
			controller = new MemberListController(); 
			nextPage = controller.requestHandler(request, response);
		}else if(command.equals("/memberInsert.do")) { //회원가입
			controller = new MemberInsertController(); 
			nextPage = controller.requestHandler(request, response);
		}else if(command.equals("/memberRegister.do")) { //회원가입화면
			controller = new MemberRegisterController(); 
			nextPage = controller.requestHandler(request, response);
		}else if(command.equals("/memberContent.do")) { //상세보기
			controller = new MemberContentController();
			nextPage = controller.requestHandler(request, response);
		}else if(command.equals("/memberUpdate.do")) {
			controller = new MemberUpdateController(); 
			nextPage = controller.requestHandler(request, response);
		}else if(command.equals("/memberDelete.do")) {
			controller = new MemberDeleteController(); 
			nextPage = controller.requestHandler(request, response);
		}
		*/
		HandlerMapping mapping = new HandlerMapping(); 
		controller = mapping.getController(command);
		nextPage = controller.requestHandler(request, response);
		
		//forward, redirect 결정
		if(nextPage != null) {
			if(nextPage.indexOf("redirect:") != -1) {
				//                 redirect:/MVC04/memberList.do
				response.sendRedirect(nextPage.split(":")[1]); //redirect
				//                             split은 " " 를 기준으로 자른 뒤 뒤에 배열이 넘어감
				//                             여기선 : 을 기준으로 redirect 주소를 자른 후 /MVC04/memberList.do를 넘김
			}else { //                                              "prefix" + 뷰 이름 + "subfix"
				RequestDispatcher rd = request.getRequestDispatcher(ViewResolver.makeView(nextPage)); //forward
				rd.forward(request, response);
			}
		}
	}

}
