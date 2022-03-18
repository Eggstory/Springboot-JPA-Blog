package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 아래 3개의 어노테이션은 시큐리티에서 세트임
@Configuration		// 빈등록(IoC 관리)
@EnableWebSecurity	// 시큐리티 필터가 등록이 된다 : 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Bean	// IoC가 됨 (스프링이 관리하게됨)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() 					// csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeHttpRequests()			// Request가 들어올때 라는 뜻
			.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")	// 경로로 들어오는 사람은 누구나 들어올 수 있다.
			.permitAll()
			.anyRequest()						// 이게 아닌 다른 모든 요청들은
			.authenticated()					// 인증이 되야 한다는 뜻
		.and()
			.formLogin()
			.loginPage("/auth/loginForm");
	}
}
