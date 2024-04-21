package com.project.ssiach2ex6;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		log.info("authentication started");

		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());

		if("hello".equals(username)&&"12345".equals(password)){
			return new UsernamePasswordAuthenticationToken(username, password, List.of());
		}

		throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
	}

	@Override
	public boolean supports(final Class<?> authenticationType) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
	}
}
