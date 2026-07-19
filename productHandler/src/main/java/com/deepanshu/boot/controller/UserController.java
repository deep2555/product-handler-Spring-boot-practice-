package com.deepanshu.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.boot.DTO.UserDTO;
import com.deepanshu.boot.entity.User;
import com.deepanshu.boot.service.MyUserDetailService;
import com.deepanshu.boot.util.JWTUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MyUserDetailService userDetailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userDetailService.createUser(user);
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody UserDTO userDTO) {

		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		// list of roles
		List<String> listOfRoles = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

		UserDetails userByUsername = userDetailsService.loadUserByUsername(userDTO.getUserName());

		return jwtUtil.generateToken(userByUsername.getUsername(), listOfRoles);

	}

}
