package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class FileDelController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		// 파일다운로드 컨트롤러
		// 1. 파일이름 파라메터로 받기
		String filename = request.getParameter("filename");
		int num = Integer.parseInt(request.getParameter("num"));
		; 
		// 2. 클라이언트로부터 넘어오는 파일이름에 한글이 있는 경우 깨지지 않게 하기 위함
		filename = URLEncoder.encode(filename, "UTF-8");
		filename = filename.replace("+", " "); // + 를 공백으로 대체하는 메소드
		
		// 3. 파일이 저장된 경로 가져오기
		String UPLOAD_DIR = "file_repo";
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		// 디렉토리에 들어있는 모든정보를 파일정보로 가져오기 위해 객체생성
		File file = new File(uploadPath + "\\" + filename);
		
		// 4. 폴더에 파일 유무 확인하기
		if(file.exists()) {
			file.delete();
			System.out.println("디렉토리에서 파일삭제");
		}
		// 5. DB 테이블에서 파일컬럼을 null처러 -> update인데 삭제로 의미부여
		MemberDAO dao = new MemberDAO();
		dao.memberDeleteFile(num);
		
		return "redirect:" + ctx + "/memberContent.do?num=" + num;
	}

}
