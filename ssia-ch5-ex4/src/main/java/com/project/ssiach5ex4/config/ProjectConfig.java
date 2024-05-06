package com.project.ssiach5ex4.config;

import com.project.ssiach5ex4.handlers.CustomAuthenticationFailureHandler;
import com.project.ssiach5ex4.handlers.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler authenticationFailureHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(auth-> auth
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler));
		http.authorizeHttpRequests(req ->
			req.anyRequest().authenticated());

		return http.build();
	}
}
