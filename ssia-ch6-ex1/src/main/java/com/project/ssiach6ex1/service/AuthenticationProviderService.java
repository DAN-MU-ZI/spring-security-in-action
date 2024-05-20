package com.project.ssiach6ex1.service;

import com.project.ssiach6ex1.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationProviderService
	implements AuthenticationProvider {

	private final JpaUserDetailsServiceImpl userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final SCryptPasswordEncoder sCryptPasswordEncoder;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		CustomUserDetails user = userDetailsService.loadUserByUsername(username);

		switch (user.getUser().getAlgorithm()) {
			case BCRYPT -> {
				return checkPassword(user, password, bCryptPasswordEncoder);
			}
			case SCRYPT -> {
				return checkPassword(user, password, sCryptPasswordEncoder);
			}
		}

		throw new BadCredentialsException("Bad credentials");
	}

	private Authentication checkPassword(final CustomUserDetails user, final String rawPassword,
		final PasswordEncoder encoder) {

		if (encoder.matches(rawPassword, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
				user.getUsername(),
				user.getPassword(),
				user.getAuthorities());
		}

		throw new BadCredentialsException("Bad credentials");
	}

	@Override
	public boolean supports(final Class<?> aClass) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
	}
}
