package kr.board.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

// 로그인 인증 후 사용자 정보 저장하기 -> 3. userDetails
@Data
public class MemberUser extends User { //user의 부모 --> UserDetails interface
	
	private Member member; 

	public MemberUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public MemberUser(Member mvo) {
		//list<AuthVO> ==> Collection<SimpleGrantedAuthority>
		super(mvo.getMemID(), mvo.getMemPassword(), 
			  mvo.getAuthList().stream().map(auth->new SimpleGrantedAuthority(auth.getAuth()))
			  .collect(Collectors.toList()));
		this.member = mvo; 
		// TODO Auto-generated constructor stub
	}
}
