package com.project.ssiach5ex1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		log.info("authentication is started");
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if(passwordEncoder.matches(password, userDetails.getPassword())){
			return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
		}

		throw new BadCredentialsException("Something went wrong!");
	}

	@Override
	public boolean supports(final Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}
}
