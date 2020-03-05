package com.example.Spring_boot_18.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Spring_boot_18.models.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByUsername(String username);
}
