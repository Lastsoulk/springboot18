package com.example.Spring_boot_18.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_boot_18.models.Usuario;
import com.example.Spring_boot_18.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	 private UserRepository applicationUserRepository;
	 private BCryptPasswordEncoder bCryptPasswordEncoder;

	 public UserController(UserRepository applicationUserRepository,
	                          BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.applicationUserRepository = applicationUserRepository;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }

	    @PostMapping("/sign-up")
	    public void signUp(@RequestBody Usuario user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        applicationUserRepository.save(user);
	    }
}
