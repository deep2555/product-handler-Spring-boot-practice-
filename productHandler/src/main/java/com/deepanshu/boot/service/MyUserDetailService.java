package com.deepanshu.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deepanshu.boot.entity.User;
import com.deepanshu.boot.repository.UserRepository;
import com.deepanshu.boot.security.UserPrinciple;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
		return userRepository.save(user);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userNamefetch = userRepository.findByUserName(username);
		if(userNamefetch.isEmpty()) {
			throw new UsernameNotFoundException(username + "username not found");
		}
		return new UserPrinciple(userNamefetch.get());
	}
}
