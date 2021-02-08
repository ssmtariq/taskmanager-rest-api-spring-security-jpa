package com.ssmtariq.taskmanager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ROLE_ADMIN;
import static com.ssmtariq.taskmanager.constant.TaskManagerApiConstants.ROLE_USER;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint entryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			    .antMatchers("/prepare/demo/**").permitAll()
			    .antMatchers("/projects/user/{id}").hasRole(ROLE_ADMIN)
			    .antMatchers("/project/**").hasRole(ROLE_USER)
			    .antMatchers("/projects/**").hasRole(ROLE_USER)
			    .antMatchers("/tasks/user/{id}").hasRole(ROLE_ADMIN)
			    .antMatchers("/task/**").hasRole(ROLE_USER)
			    .antMatchers("/tasks/**").hasRole(ROLE_USER)
			    .and().httpBasic()
				.authenticationEntryPoint(entryPoint)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
