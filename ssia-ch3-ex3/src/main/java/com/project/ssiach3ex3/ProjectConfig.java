package com.project.ssiach3ex3;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ProjectConfig {

	@Bean
	public UserDetailsService userDetailsService(){
		var cs = new DefaultSpringSecurityContextSource(
			"ldap://127.0.0.1:33389/dc=springframework,dc=org");
		cs.afterPropertiesSet();

		LdapUserDetailsManager manager = new LdapUserDetailsManager(cs);
		manager.setUsernameMapper(
			new DefaultLdapUsernameToDnMapper("ou=groups", "uid"));
		manager.setGroupSearchBase("ou=groups");
		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
