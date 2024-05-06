package com.project.ssiach5ex4.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
		final AuthenticationException exception) throws IOException, ServletException {

		response.setHeader("failed", LocalDateTime.now().toString());
	}
}
