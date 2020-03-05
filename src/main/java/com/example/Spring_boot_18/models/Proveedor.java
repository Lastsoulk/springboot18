package com.example.Spring_boot_18.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="CAP_P1_PROVEEDOR")
public class Proveedor {
	@Id
	@GeneratedValue
	@Column(name="RUC")
	private int ruc;
	
	@NotNull(message="Dirección del proveedor no puede ser nulo")
	@Column(name="DIRECCION")
	private String direccion;
	
	@NotNull(message="Razón social del proveedor no puede ser nulo")
	@Column(name="RAZONSOCIAL")
	private String razonSocial;

	public Proveedor() {
	}

	public Proveedor(int ruc,  String direccion, String razonSocial) {
		this.ruc = ruc;
		this.direccion = direccion;
		this.razonSocial = razonSocial;
	}

	public int getRuc() {
		return ruc;
	}

	public void setRuc(int ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
}
