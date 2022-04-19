package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.revature.service.JwtUserDetailsService;

/**
 * Configuration for web security
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private JwtAuthenticationEntryPoint entry;
	private UserDetailsService serv;
	private JwtRequestFilter filter;

	@Autowired
	public WebSecurityConfig(JwtAuthenticationEntryPoint entry, JwtUserDetailsService serv, JwtRequestFilter filter) {
		this.entry = entry;
		this.serv = serv;
		this.filter = filter;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(serv).passwordEncoder(passwordEncoder());
	}

	/**
	 * Password encoding for the application using BCryptPasswordEncoder
	 * 
	 * @return - PasswordEncoder being used
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/h2-console/**").permitAll();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		
		httpSecurity.cors().and().csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/login", "/register", "/changePassword").permitAll().
				// all other requests need to be authenticated
				anyRequest().authenticated()
				// make sure we use stateless session; session won't be used to
				// store user's state.
				.and().exceptionHandling().authenticationEntryPoint(entry).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
