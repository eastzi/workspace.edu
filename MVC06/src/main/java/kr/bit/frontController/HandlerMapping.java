package kr.bit.frontController;

import java.util.HashMap;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberLoginController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;

public class HandlerMapping {
	private HashMap<String, Controller> mappings; 
	public HandlerMapping() {
		//HashMap 에 key값과 value값 저장
		//key값으로 요청하면 value를 내줌
		mappings = new HashMap<String, Controller>();
		mappings.put("/memberList.do", new MemberListController());
		mappings.put("/memberInsert.do", new MemberInsertController());
		mappings.put("/memberRegister.do", new MemberRegisterController());
		mappings.put("/memberContent.do", new MemberContentController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());
		mappings.put("/memberDelete.do", new MemberDeleteController());
		mappings.put("/memberLogin.do", new MemberLoginController());
	}
	
	//FrontController가 key값으로 요청 시 value를 넘겨주는 메소드
	public Controller getController(String key) {
		return mappings.get(key); 
	}
}
