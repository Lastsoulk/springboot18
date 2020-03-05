package com.example.Spring_boot_18.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Spring_boot_18.models.*;

@Repository
public interface ClientRepository extends JpaRepository <Cliente,String>{

	

	Optional<Cliente> findByIdentificacion(String identificacion);

	void deleteByIdentificacion(int cliente_codigo);
}
