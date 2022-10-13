package kr.bit.model;
import java.sql.*;

//MemberVO를 DB와 연동하는 역할 
public class MemberDAO {
	private Connection conn; 
	private PreparedStatement ps; 
	private ResultSet rs; 
	
	//DB에 데이터 저장하기
	//1. 데이터베이스 연결객체 생성 -> connection객체
	public void getConnect() {
		//데이터베이스 접속 URL
		String URL="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
		String user="root"; 
		String password="admin12345"; 
		
		//2. MySql Driver Loading하기 -> lib에 mysql jar 포함 -> driver class를 메모리에 로딩하기 
		try { //3. class의 유무가 확실하지 않으므로 예외처리 
			//1-2. connection객체 생성
			Class.forName("com.mysql.jdbc.Driver"); //2-1. 실행시점에 드라이버를 메모리에 올리기 = 동적로딩(실행시점에 객체생성 방법)  
			//4. 로딩이 완료되면 접속시도 -> 접속 성공시 연결정보를 넘겨줌(DriverManager가 메모리에 생성된 객체와 연결됨)
			conn = DriverManager.getConnection(URL, user, password); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//5. 회원저장하기
	public int memberInsert(MemberVO vo) {
		//                                                                 ? = 파라메터(1,2,3,4,5,6)
		String SQL="insert into member(id, pass, name, age, email, phone) values(?,?,?,?,?,?)"; 
		getConnect();
		
		int cnt = -1;  //-1은 의미없음. 그냥 초기화하는 역할 임의로 지정된 숫자일뿐.
		
		//6. sql문장을 전송하는 객체 생성
		try {
			ps = conn.prepareStatement(SQL); //DB와 미리 컴파일시키는 작업. values값을 제외한 변수들만.(오류체크 및 속도가 빨라지기 때문) 
			ps.setString(1, vo.getId()); //ps가 가지고 있는 컴파일된 sql member객체의 1번째에 vo에 있는 id를 set하라.
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());
			
			cnt = ps.executeUpdate(); //ps가 가지고 있는 완성된 SQL 실행(전송) 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt; //cnt --> 성공시 1, 실패시 0
	}
}
