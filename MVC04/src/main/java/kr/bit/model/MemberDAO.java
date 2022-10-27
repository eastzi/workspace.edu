package kr.bit.model;
import java.sql.*;
import java.util.ArrayList;
//JDBC
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
		} finally {
			dbClose(); //db연결 끊기 
		}
		return cnt; //cnt --> 성공시 1, 실패시 0
	}
	
	//MemberListController와 연관 -> 1. 회원(VO) 전체리스트(ArrayList) 가져오기 
	public ArrayList<MemberVO> memberList() {
		String SQL = "select * from member";
		getConnect(); 
		
		//4-4 묶음 데이터 담기
		ArrayList<MemberVO> list = new ArrayList<MemberVO>(); 
		
		//3. db에 있는 회원목록 가져오기 
		try {
			//DB와 미리 컴파일시키는 작업. values값을 제외한 변수들만.(오류체크 및 속도가 빨라지기 때문)
			//SQL에 파라메터가 없기때문에 추가 설정없이 실행가능 
			ps = conn.prepareStatement(SQL);  
			rs = ps.executeQuery(); //insert - executeUpdate / select - executeQuery
			
			//4. db에 데이터가 있는지 확인
			while(rs.next()) {
				//4-2 데이터 가져오기
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				//4-3 가져온 데이터 묶기
				MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				//4-4 묶음 데이터 담기 -> list에 전체 회원리스트가 저장됨
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		//5. 담은 전체 회원리스트 리턴 -> 메소드타입도 변경하기(ArrayList<MemberVO>)
		return list;
	}
	
	public int memberDelete(int num) {
		String SQL = "delete from member where num =?";
		getConnect();
		
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL); //DB와 미리 컴파일시키는 작업. values값을 제외한 변수들만.(오류체크 및 속도가 빨라지기 때문)
			ps.setInt(1, num); //1번째 ?가(sql의) 파라메터가 num 
			cnt = ps.executeUpdate(); //성공시 1, 실패시 0
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose(); 
		}
		return cnt;
	}
	
	public MemberVO memberContent(int num) {
		String SQL = "select * from member where num=?";
		getConnect(); 
		
		MemberVO vo = null; 

		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				//회원정보의 정보를 테이블에서 가져와서 vo로 묶고
				num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				vo = new MemberVO(num, id, pass, name, age, email, phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose(); 
		}
		return vo; 
	}
	
	//업데이트 함수 만들기
	public int memberUpdate(MemberVO vo) {
		String SQL = "update member set age=?, email=?, phone=? where num=?";
		getConnect(); 
		
		int cnt = -1; 
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, vo.getAge());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPhone());
			ps.setInt(4, vo.getNum());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(); 
		}
		return cnt; 
	}
	
	//2. DB사용 완료시 연결 끊기 -> 변수 갯수 모두 끊어줘야함.
	public void dbClose() {
		try {
			if(rs!=null) {
				rs.close();				
			}
			if(ps!=null) {
				ps.close();				
			}
			if(conn!=null) {
				conn.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
