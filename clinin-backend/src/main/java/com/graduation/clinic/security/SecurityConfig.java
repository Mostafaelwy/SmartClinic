package com.graduation.clinic.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.graduation.clinic.security.filters.JwtAuthenticationfilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationfilter jwtAuthenticationfilter;
	private final AuthenticationProvider authenticationProvider;

	public SecurityConfig(JwtAuthenticationfilter jwtAuthenticationfilter,AuthenticationProvider authenticationProvider) {
		super();
		this.jwtAuthenticationfilter = jwtAuthenticationfilter;
		this.authenticationProvider=authenticationProvider;
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
			.authorizeHttpRequests().requestMatchers("/auth/**").permitAll().and()
			.authorizeHttpRequests().requestMatchers("/swagger-ui/**").permitAll().and()
			.authorizeHttpRequests().requestMatchers("/v3/api-docs/**").permitAll().and()
			.authorizeHttpRequests().requestMatchers("/error/**").permitAll().and()
			.authorizeHttpRequests().requestMatchers("/smart/doctor/**").hasAuthority("DOCTOR").and()
			.authorizeHttpRequests().requestMatchers("/smart/patient/**").hasAuthority("PATIENT").and()
			.authorizeHttpRequests().requestMatchers("/smart/receptionist/**").hasAuthority("RECEPTIONIST").and()
			.authorizeHttpRequests().requestMatchers("/smart/**").authenticated().and()
			
			.csrf()
			.disable()
			.cors().disable()
			.sessionManagement(sess -> sess
 	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class)
			
			.authenticationProvider(authenticationProvider);
		return http.build();
	}
}
