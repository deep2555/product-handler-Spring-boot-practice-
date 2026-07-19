package com.deepanshu.boot.util;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilters extends OncePerRequestFilter{
		
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

	        String authHeader = request.getHeader("Authorization");
	        String username = null;
	        String token = null;
	        // extract token and username
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            username = jwtUtil.extractUserName(token);
	        }
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            if (userDetails != null) {
	                List<String> roles = jwtUtil.extractroles(token);
	                List<SimpleGrantedAuthority> authorities = roles.stream()
	                        .map(role -> new SimpleGrantedAuthority(role))
	                        .toList();

	                if (jwtUtil.validToken(token, userDetails.getUsername())) {
	                    UsernamePasswordAuthenticationToken authToken =
	                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authToken);
	                }
	            }
	        }
	        filterChain.doFilter(request, response);
	    }
	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		String authHeader = request.getHeader("Authorization");
//		String username = null;
//		String token = null;
//		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
//			token = authHeader.substring(7);
//			System.out.println("token is : " + token);
//			username = jwtUtil.extractUserName(token);
//		}
//		
//		if(username!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userByUsername = userDetailsService.loadUserByUsername(username);
//			List<String> extractroles = jwtUtil.extractroles(token);
//			List<SimpleGrantedAuthority> authorities = extractroles.stream().map(role -> new SimpleGrantedAuthority(extractroles)).toList();
//			
//			if(jwtUtil.validToken(token, userByUsername.getUsername())) {
//				UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userByUsername,null, authorities);
//				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(authtoken);
//			}
//		}
//		
//		filterChain.doFilter(request, response);
//	}

}
