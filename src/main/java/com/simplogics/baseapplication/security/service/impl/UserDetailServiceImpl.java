package com.simplogics.baseapplication.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simplogics.baseapplication.entity.User;
import com.simplogics.baseapplication.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("#user.not.found"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}

	public List<SimpleGrantedAuthority> getAuthorities(User user) throws UsernameNotFoundException {
		if (user.getRole() == null)
			throw new UsernameNotFoundException("#user.no.roles.asssigned");

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
//		return new SimpleGrantedAuthority(user.getRole().name())
		return authorities;
	}

	public List<SimpleGrantedAuthority> getAuthoritiesFromRoleString(String role) throws UsernameNotFoundException {
		if (role == null)
			throw new UsernameNotFoundException("#user.no.roles.asssigned");

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
//		return new SimpleGrantedAuthority(user.getRole().name())
		return authorities;
	}
}
