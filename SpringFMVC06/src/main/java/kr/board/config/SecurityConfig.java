package kr.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.board.security.MemberUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//service 등록하기
	@Bean
	public UserDetailsService memberUserDetailsService() {
		return new MemberUserDetailsService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberUserDetailsService())
		.passwordEncoder(passwordEncoder());
		System.out.println("인증 메니저 시작");
	}

	//요청에대한 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//시큐리티 적용된 post에 대한 한글 인코딩 
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);
		
		//security 권한설정
		http
			.authorizeRequests()
				.antMatchers("/")
				.permitAll()
				.and()
			.formLogin()
				.loginPage("/memLoginForm.do") //사용자가 따로 만든 로그인 페이지를 사용하려고 할때 설정
				.loginProcessingUrl("/memLogin.do") //스프링 제공 로그인 controller(로그인, 즉 인증 처리를 하는 URL을 설정합)
				.permitAll()
				.and()
			.logout()
				.invalidateHttpSession(true) //session 끊기
				.logoutSuccessUrl("/")
				.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	//패스워드 인코딩 객체 설정
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
