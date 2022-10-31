package kr.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileGetController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파일다운로드 컨트롤러
		// 1. filename 받기 
		String filename = request.getParameter("filename");
		System.out.println(filename);
		
		// 2. 파일 다운로드 받을 폴더 경로 설정 -> 디렉토리
		String UPLOAD_DIR = "file_repo";
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		// 디렉토리에 들어있는 모든정보를 파일정보로 가져오기 위해 객체생성
		File f = new File(uploadPath + "\\" + filename);
		
		// 3. 클라이언트로부터 넘어오는 파일이름에 한글이 있는 경우 깨지지 않게 하기 위함
		filename = URLEncoder.encode(filename, "UTF-8");
		filename = filename.replace("+", " "); // + 를 공백으로 대체하는 메소드
		
		// 4. 다운로드 준비 -> 서버에서 클라이언트에게 다운로드 준비를 시키는 부분(다운로드 창 띄우기) 
		response.setContentLength((int)f.length());
		response.setContentType("application/x-msdownload;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + filename + ";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		// 5. 실제다운로드 시작
		FileInputStream in = new FileInputStream(f); // 파일읽기 준비
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[104];
		while(true) {
			int count = in.read(buffer);
			if(count == -1) { //읽을 데이터가 없으면
				break;
			}
			out.write(buffer, 0, count); //다운로드 -> 버퍼에있는 데이터를 0번부터 읽어들인 데이터 수 만큼(0%....100%)
		}
		in.close();
		out.close();
		
		return null;
	}

}
