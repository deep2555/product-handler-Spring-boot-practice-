/**
 * 
 */
package com.deepanshu.boot.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.deepanshu.boot.entity.User;

/**
 * this class reponsible to implement userDEtails its provide info about user
 * like name, password
 */
public class UserPrinciple implements UserDetails {

	private User user;

	public UserPrinciple(User user) {
			this.user = user;
	}

	// describe the roles for the user
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority("ROLE_SELLER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

}
