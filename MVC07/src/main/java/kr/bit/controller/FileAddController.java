package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileAddController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파일 업로드 컨트롤러
		// post방식으로 넘어온 파일 처리
		String UPLOAD_DIR = "file_repo"; // 파일이 저장되는 폴더지정
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		File currentDirPath = new File(uploadPath); // 업로드할 경로를 file객체로 만들기
		if(!currentDirPath.exists()) {
			currentDirPath.mkdir(); //currentDirPath가 없으면 make directory로 하나 만들기
		}   //임시저장공간
		
		// 파일을 업로드 할때 먼저 저장될 임시 저장경로 설정
		// 파일 업로드시 필요한 API -> commons-fileupload, commons-io mvn에서 다운 후 lib에 붙여넣기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024); //업로드파일 용량 1MB
		
		String fileName = null;
		
		// 파일업로드 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// request안에 여러개의 파일이 업로드된 경우
			// items --> FileItem[ ], FileItem[ ], FileItem[ ]...
			List<FileItem> items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = items.get(i);
				if(fileItem.isFormField()) {
					//폼필드이면
					System.out.println(fileItem.getFieldName()+ "=" + fileItem.getString("utf-8"));
				}else {
					//파일이면
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\"); // window인 경우 \\, linux인 경우 /
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						fileName = fileItem.getName().substring(idx + 1); //파일이름 가져오기
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						
						//파일이름 중복체크
						if(uploadFile.exists()) {
							fileName = System.currentTimeMillis() + "_" + fileName; 
							uploadFile = new File(currentDirPath + "\\" + fileName);
						}
						// 임시경로에서 새로운경로에 파일 쓰기
						try {
							fileItem.write(uploadFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} 
		// $.ajax() 쪽으로 업로드 된 최종파일이름 전송
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(fileName); 		
		return null;
	}

}
