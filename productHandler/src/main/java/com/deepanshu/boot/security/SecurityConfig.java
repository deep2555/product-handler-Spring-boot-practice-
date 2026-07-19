package com.deepanshu.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.deepanshu.boot.util.JwtRequestFilters;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	 @Autowired
	 private UserDetailsService userDetailsService;
	 @Autowired
	 private JwtRequestFilters jwtRequestFilters;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(request ->{
				request.requestMatchers("/user/register","/user/login").permitAll();
				 request.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
				 request.anyRequest().authenticated();
		}).authenticationProvider(authenticationProvider())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtRequestFilters, UsernamePasswordAuthenticationFilter.class);
			//.httpBasic(Customizer.withDefaults());
		
		return security.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// get the authentication provider
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}
	
	// request recieve in authentication manager and it send to the provider
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	// for testing when we donot have database
//	@Bean
//	public UserDetailsService userDetailsService() {
//		// for admin 
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles("ADMIN")
//				.build();
//		
//		// for seller
//		UserDetails seller = User.builder()
//				.username("seller")
//				.password(passwordEncoder().encode("seller"))
//				.roles("SELLER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,seller);
//	}
}
