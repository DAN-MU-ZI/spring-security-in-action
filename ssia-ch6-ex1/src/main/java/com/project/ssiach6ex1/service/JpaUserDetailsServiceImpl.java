package com.project.ssiach6ex1.service;

import com.project.ssiach6ex1.entity.CustomUserDetails;
import com.project.ssiach6ex1.entity.Users;
import com.project.ssiach6ex1.repository.UsersRepository;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsServiceImpl implements UserDetailsService {

	private final UsersRepository usersRepository;

	@Override
	public CustomUserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException(
			"Problem during authentication!"
		);

		Users user = usersRepository
			.findUsersByUsername(username)
			.orElseThrow(s);

		return new CustomUserDetails(user);
	}
}
