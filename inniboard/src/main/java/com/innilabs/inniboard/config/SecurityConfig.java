package com.innilabs.inniboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean 
	  public PasswordEncoder passwordEncoder() { // 4
	    return new BCryptPasswordEncoder();
	  }

	  @Override
	  public void configure(WebSecurity web) { // 5
	    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception { // 6
	    http
	          .authorizeRequests() // 7
	          	.antMatchers("/swagger-ui.html").permitAll()
	            //.antMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
	            //.antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
	            .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
	            //.anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
	        /*.and() 
	          .formLogin() // 8
	            .loginPage("/login") // 로그인 페이지 링크
	            .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
	        .and()
	          .logout() // 9
	            .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
		    .invalidateHttpSession(true) // 세션 날리기*/
	    ;
	  }

	 
}
