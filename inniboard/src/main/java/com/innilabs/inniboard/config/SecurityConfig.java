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
	            //.antMatchers("/login", "/signup", "/user").permitAll() // ������ ���� ���
	            //.antMatchers("/").hasRole("USER") // USER, ADMIN�� ���� ����
	            .antMatchers("/admin").hasRole("ADMIN") // ADMIN�� ���� ����
	            //.anyRequest().authenticated() // ������ ��û���� ������ ������ ��� ���� ������ �־�� ���� ����
	        /*.and() 
	          .formLogin() // 8
	            .loginPage("/login") // �α��� ������ ��ũ
	            .defaultSuccessUrl("/") // �α��� ���� �� �����̷�Ʈ �ּ�
	        .and()
	          .logout() // 9
	            .logoutSuccessUrl("/login") // �α׾ƿ� ������ �����̷�Ʈ �ּ�
		    .invalidateHttpSession(true) // ���� ������*/
	    ;
	  }

	 
}
