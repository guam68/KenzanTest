package com.kenzan.processor.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "api/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
		.anyRequest().permitAll()
		.and()
		.httpBasic();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String userQuery = "SELECT username, password, enabled FROM user WHERE username = ?";
		String authQuery = "SELECT username, role FROM User WHERE username=?";
		
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(userQuery)
		.authoritiesByUsernameQuery(authQuery)
		.passwordEncoder(passwordEncoder);
	}

}
