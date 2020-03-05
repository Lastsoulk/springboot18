package com.example.Spring_boot_18.models;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.Spring_boot_18.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private UserRepository repo;
	
	public UserDetailsServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user =repo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException(username);
		return new User(user.getUsername(),user.getPassword(), new ArrayList());
	}

}
