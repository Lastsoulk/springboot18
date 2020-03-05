package com.example.Spring_boot_18.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring_boot_18.models.Venta;



@Repository
public interface VentaRepository extends JpaRepository <Venta,Integer>{

}
