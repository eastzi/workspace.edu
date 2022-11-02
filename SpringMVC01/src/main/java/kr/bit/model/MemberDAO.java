package kr.bit.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//JDBC -> MyBatis
//MemberVO를 DB와 연동하는 역할 
//@-> Spring Container에서 관리해주는 대상
@Repository //root-context.xml 스캔 대상
public class MemberDAO {
	
	//Spring
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// MVC방법 -> private static SqlSessionFactory sqlSessionFactory; 

	/* 초기화블럭 - 프로그램 실행 시 딱 한번만 실행되는 코드영역 => MVC에서는 필요했으나 Spring에서는 자동으로 스프링컨테이너에 생성되므로 필요없어짐.(root-context.xml)
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
	*/
	
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
	
	//회원가입 - insert
	public int memberInsert(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession(); 
		int cnt = session.insert("memberInsert", vo); 
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
}
