package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	//POJO도 매개변수로 request, response를 받아야 함.(frontcontroller의 업무를 대신하기 때문에 당연히 요청을 받아야함)
	//return type void => String
	public String memberContent(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	public String memberDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	public String memberInsert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	public String memberList(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	public String memberRegister(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	public String memberUpdate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}
