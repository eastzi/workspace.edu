package kr.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.board.entity.Member;
import kr.board.entity.MemberUser;
import kr.board.mapper.MemberMapper;

public class MemberUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper; 
	
	//로그인시 아이디와 db에 있는 아이디 일치확인 및 해당아이디의 pw 일치확인 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		// 로그인 처리하기(username = id)
		Member mvo = memberMapper.memLogin(username);
		
		// Member Type이 <--X--> UserDetails 타입과 맞지 않다 --> userDetails는 사용자 정보를 담는 인터페이스.
		// Member타입의 인스턴스를 UserDetails로 처리하려면
		// 1. Member클래스에 UserDetails인터페이스를 구현해 주는 방법
		// 2. Member클래스가 이미 UserDetails인터페이스를 구현한 User클래스를 상속하는 방법
		// 3. 조합을 이용해서 Member를 포함하는 별도의 클래스를 만드는 방법
		// =>별도의 클래스를 만들고 Member의 인스턴스를 감싸는 형태로 만들면
		// Member의 모든 정보를 추가적으로 사용가능 하다는 장점
		
		//UserDetails ---> User ---> User를 상속받는 MemberUser 클래스 생성(사용자정보 + 권한정보를 가지는 클래스)
		if(mvo != null) {
			return new MemberUser(mvo); 
		}else {
			throw new UsernameNotFoundException("user with username" + username + "does not exist.");
		}
		
	}
	

}
