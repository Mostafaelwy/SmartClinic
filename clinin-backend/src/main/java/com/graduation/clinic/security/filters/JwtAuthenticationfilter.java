package com.graduation.clinic.security.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.graduation.clinic.service.JwtService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationfilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final String tokenSubstringWord = "Bearer";
	
	
	public JwtAuthenticationfilter(JwtService jwtService,UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService=userDetailsService;
	}

	@Override
	protected void doFilterInternal(
			
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
			
			) throws ServletException, IOException {
	
		String AuthHeader=request.getHeader("authorization");
		String Jwt;
		String userName;
		
		if(AuthHeader ==null || !AuthHeader.startsWith(tokenSubstringWord)) {
			filterChain.doFilter(request, response);
			return;
		}
		Jwt=AuthHeader.substring(7);
		userName=jwtService.extractUserName(Jwt);
		if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
			if(jwtService.isTokenValid(userDetails, Jwt)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
