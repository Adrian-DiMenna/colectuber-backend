package com.is.cole.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.is.cole.daos.IUserDao;
import static java.lang.String.format;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final IUserDao userDao;
	
	
	public SecurityConfig(IUserDao userDao) {
		this.userDao= userDao;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> userDao
	            .findByUserName(username)
	            .orElseThrow(
	                () -> new UsernameNotFoundException(
	                    format("User: %s, not found", username)
	                )
	            ));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// TODO configure web security
	}
}

