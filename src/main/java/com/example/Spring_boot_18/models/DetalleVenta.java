package com.example.Spring_boot_18.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="CAP_P1_DETALLEVENTA")
public class DetalleVenta {
	@Id
	@GeneratedValue
	@Column(name="ID_DETALLEVENTA")
	private int codigo;
	
	@NotNull(message="Cantidad no puede ser nula")
	@Column(name="ID_PRODUCTO")
    private int idProducto;
    
	@NotNull(message="Producto (ID) no puede ser nulo")
	@Column(name="ID_VENTA")
    private int idVenta;
    
	@NotNull(message="Venta (ID) no puede ser nulo")
	@Column(name="PRECIO_UNITARIO")
    private int precioUnitario;
	
	@NotNull(message="Cantidad_Vendido")
	@Column(name="CANTIDAD_VENDIDO")
    private int cantidad;
	
	public DetalleVenta() {
		
	}

	public DetalleVenta(int codigo, @NotNull(message = "Cantidad no puede ser nula") int idProducto,
			@NotNull(message = "Producto (ID) no puede ser nulo") int idVenta,
			@NotNull(message = "Venta (ID) no puede ser nulo") int precioUnitario,
			@NotNull(message = "Cantidad_Vendido") int cantidad) {
		super();
		this.codigo = codigo;
		this.idProducto = idProducto;
		this.idVenta = idVenta;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
    
    
}
