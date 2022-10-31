package kr.bit.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//JDBC -> MyBatis
//MemberVO를 DB와 연동하는 역할 
public class MemberDAO {
	private static SqlSessionFactory sqlSessionFactory; 

	// 초기화블럭 - 프로그램 실행 시 딱 한번만 실행되는 코드영역
	static {
		try {
			String resource = "kr/bit/mybatis/config.xml"; 
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource); // resource에 저장된 주소 읽기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); //connection pool
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//회원전체 리스트보기 - select
	public List<MemberVO> memberList() {
		SqlSession session = sqlSessionFactory.openSession();
		//String SQL = "select * from member"; -> JDBC 에서는 함께 작성했지만 MyBatis에선 Mapper파일에 작성하고 밑에 selectList의 ()속에 맵핑
		List<MemberVO> list = session.selectList("memberList"); //DB에 저장된 리스트 가져오는 코드, () 속에 SQL문장과 관련된 코드작성-> mapper파일과 맵핑
		//memberList는 mapper의 sql id 
		//selectList는 데이터에서 가져온 정보를 vo로 묶고 list에 담아주는 역할을 해줌
		session.close(); //sqlSession 반납
		return list;
	}
	
	//회원가입 - insert(파일업로드 없는경우)
	public int memberInsert(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession(); 
		int cnt = session.insert("memberInsert", vo); 
		session.commit();
		session.close();
		return cnt; 
	}
	
	//회원가입 - insert(파일업로드 있는경우)
	public int memberInsertFile(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession(); 
		int cnt = session.insert("memberInsertFile", vo); 
		session.commit();
		session.close();
		return cnt; 
	}
	
	//회원삭제 - delete
	public int memberDelete(int num) {
		SqlSession session = sqlSessionFactory.openSession(); 
		int cnt = session.delete("memberDelete", num); 
		session.commit();
		session.close();
		return cnt; 
	}
	
	//회원파일삭제 - update
	public int memberDeleteFile(int num) {
		SqlSession session = sqlSessionFactory.openSession(); 
		int cnt = session.update("memberDeleteFile", num); 
		session.commit();
		session.close();
		return cnt; 
	}
	
	//회원상세보기 - content
	public MemberVO memberContent(int num) {
		SqlSession session = sqlSessionFactory.openSession(); 
		MemberVO vo = session.selectOne("memberContent", num); 
		session.close();
		return vo; 
	}
	
	//회원수정하기 - update
	public int memberUpdate(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession(); 
		int cnt = session.update("memberUpdate", vo); 
		session.commit();
		session.close();
		return cnt; 
	}
	
	//회원 파일수정하기 - update
	public int memberUpdateFile(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession(); 
		int cnt = session.update("memberUpdateFile", vo); 
		session.commit();
		session.close();
		return cnt; 
	}
	
	//회원로그인
	public String memberLogin(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession(); 
		String user_name = session.selectOne("memberLogin", vo); 
		session.close();
		return user_name; 
	}
	
	//회원아이디 중복체크
	public String memberDbcheck(String id) {
		SqlSession session = sqlSessionFactory.openSession();
		String dbId = session.selectOne("memberDbcheck", id);
		String isDouble = "NO";
		if(dbId != null) {
			isDouble = "YES";
		}
		return isDouble;
	}
}
