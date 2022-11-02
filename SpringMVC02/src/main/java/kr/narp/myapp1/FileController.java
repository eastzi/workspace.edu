package kr.narp.myapp1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {
	
	@RequestMapping("/upload.do")
	public String upload(MultipartHttpServletRequest multipartRequest, 
			HttpServletRequest request, Model model) throws Exception { 
		// 첨부파일 - MultipartHttpServletRequest
		
		String UPLOAD_DIR = "repo"; // 파일이 저장되는 폴더지정
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		
		// 1. 클라이언트에서 넘어오는 파라메터 - id, name
		Map map = new HashMap();
		//String id = multipartRequest.getParameter("id");
		//String name = multipartRequest.getParameter("name");
		Enumeration<String> e = multipartRequest.getParameterNames(); // id, name 파라메터를 꺼냄 / 파일이름은 아직 읽어들이지 못함 
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			String value = multipartRequest.getParameter(name);
			//System.out.println(name + ":" + value);
			map.put(name, value);
		}
		
		// 2. 파일을 담고있는 파라메터 읽어오기
		// 파일명을 읽어오는게 아니라 파일의 파라메터명을 읽어오는 것 (file1, file2 ...) ->name='file"+cnt+"
		Iterator<String> it = multipartRequest.getFileNames(); 
		List<String> fileList = new ArrayList<String>();
		while(it.hasNext()) {
			String paramfName = it.next();
			//System.out.println(paramfName);
			MultipartFile mFile = multipartRequest.getFile(paramfName);
			String oName = mFile.getOriginalFilename();
			System.out.println(oName);
			fileList.add(oName);
			// 파일 업로드할 폴더유무 확인
			File file = new File(uploadPath + "\\" + paramfName);
			if(mFile.getSize() != 0) {
				if(!file.exists()) {
					if(file.getParentFile().mkdirs()) { // 저장폴더인 repo폴더 상위폴더에서 디렉토리 생성
						file.createNewFile(); // 임시로 파일 생성
					}
				}
				mFile.transferTo(new File(uploadPath + "\\" + oName)); //파일업로드
			}
		}
		
		map.put("fileList", fileList);
	
		//객체바인딩
		model.addAttribute("map", map);
		
		return "result";
	}
	
	@RequestMapping("/download.do")
	public void download(@RequestParam("filename") String filename,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			// 파일다운로드 컨트롤러
			// 1. filename 받기 
			//String filename = request.getParameter("filename");
			//System.out.println(filename);
			
			// 2. 파일 다운로드 받을 폴더 경로 설정 -> 디렉토리
			String UPLOAD_DIR = "repo";
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
			
	}

}
