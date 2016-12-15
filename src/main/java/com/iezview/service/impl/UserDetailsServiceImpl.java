package com.iezview.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iezview.domain.IezSwayRole;
import com.iezview.domain.IezSwayUser;
import com.iezview.service.IezSwayUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired(required = true)
	private IezSwayUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final IezSwayUser cUser = userService.getByUsername(username);
		if (cUser == null) {
			throw new UsernameNotFoundException(username + " cannot be found");
		}
		final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (IezSwayRole role : cUser.getIezSwayRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(cUser.getUsername(), cUser.getPassword(), authorities);
	}

}
