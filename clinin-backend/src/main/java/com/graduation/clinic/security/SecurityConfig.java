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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
//			.authorizeHttpRequests().requestMatchers("/smart/doctor/test/**").permitAll().and()
			.authorizeHttpRequests().requestMatchers("/smart/doctor/**").hasAuthority("DOCTOR").and()
			.authorizeHttpRequests().requestMatchers("/smart/patient/**").hasAuthority("PATIENT").and()
			.authorizeHttpRequests().requestMatchers("/smart/receptionist/**").hasAuthority("RECEPTIONIST").and()
			.authorizeHttpRequests().requestMatchers("/smart/**").authenticated().and()
			
			.csrf()
			.disable()
			.cors().and()
			.sessionManagement(sess -> sess
 	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class)
			
			.authenticationProvider(authenticationProvider);
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
