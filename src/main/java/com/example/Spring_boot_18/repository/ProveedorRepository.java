package com.example.Spring_boot_18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring_boot_18.models.Proveedor;



@Repository
public interface ProveedorRepository extends JpaRepository <Proveedor,Integer>{

}
