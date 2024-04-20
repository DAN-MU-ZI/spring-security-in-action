package com.project.ssiach2ex5.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.project.ssiach2ex5.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic(withDefaults());
		http.authorizeHttpRequests(auth->
			auth.anyRequest().authenticated());

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		var userDetailsService = new InMemoryUserDetailsManager();

		var user = User.withUsername("hello")
			.password("12345")
			.authorities("read")
			.build();
		userDetailsService.createUser(user);

		return userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
