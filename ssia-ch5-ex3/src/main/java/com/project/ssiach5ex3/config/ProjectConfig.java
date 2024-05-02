package com.project.ssiach5ex3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic(c->{
			c.realmName("OTHER");
			c.authenticationEntryPoint(new CustomEntryPoint());
		});  

		http.authorizeHttpRequests(req ->
			req.anyRequest().authenticated()
		);

		return http.build();
	}
}
