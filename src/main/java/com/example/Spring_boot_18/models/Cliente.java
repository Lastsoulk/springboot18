package com.example.Spring_boot_18.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="CAP_P1_CLIENTE")
public class Cliente{

	@Id
	@GeneratedValue
	@Column(name="ID_CLIENTE")
	private String identificacion;
	
	@NotNull(message="Nombre del cliente, no null")
	@Column(name="NOMBRE")
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String Nombre;
	
	@NotNull(message="Apellido del cliente, no null")
	@Column(name="APELLIDO")
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String Apellido;
	
	@NotNull(message="Dirección del cliente, no null")
	@Column(name="DIRECCION")
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String Direccion;
	
	@NotNull(message="Fecha de nacimiento del cliente, no null")
	@Column(name="FECHA_NACIMIENTO")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date fecha_Nacimiento;
	
	public Cliente() {
		
	}
	
	public Cliente(String identificacion, String Nombre,String Apellido, String Direccion,Date fecha_Nacimiento) {
		this.Nombre=Nombre;
		this.identificacion=identificacion;
		this.Apellido=Apellido;
		this.Direccion=Direccion;
		this.fecha_Nacimiento=fecha_Nacimiento;
	}

	public String getNombre() {
		return this.Nombre;
	}
	public String getApellido() {
		return this.Apellido;
	}
	public String getDireccion() {
		return this.Direccion;
	}
	public Date getFecha_Nacimiento() {
		return this.fecha_Nacimiento;
	}
	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
	public void setApellido(String Apellido) {
		this.Apellido = Apellido;
	}
	public void setDireccion(String Direccion) {
		this.Direccion = Direccion;
	}
	public void setFecha_Nacimiento(Date fecha_Nacimiento){
		this.fecha_Nacimiento=fecha_Nacimiento;
	}

	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

}