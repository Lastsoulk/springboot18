package com.example.Spring_boot_18.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "CAP_P1_PRODUCTO")
public class Producto {
	@Id
	@GeneratedValue
	@Column(name="CODIGO")
	private int codigo;
	
	@NotNull(message="Nombre del producto no puede ser nulo")
	@Column(name="NOMBRE")
	private String productoNombre;
	

	private Producto() {
	}

	private Producto(int codigo, String PRODUCTO_NOMBRE) {
		this.codigo = codigo;
		this.productoNombre = PRODUCTO_NOMBRE;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getProductoNombre() {
		return productoNombre;
	}

	public void setProductoNombre(String PRODUCTO_NOMBRE) {
		this.productoNombre = PRODUCTO_NOMBRE;
	}


}
