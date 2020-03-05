package com.example.Spring_boot_18;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@SpringBootApplication
public class SpringBoot18Application {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot18Application.class, args);
	}
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	@RequestMapping("/")
    String home() {
        return "Hello, World!";
    }
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
