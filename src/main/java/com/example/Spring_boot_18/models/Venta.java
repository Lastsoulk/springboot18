package com.example.Spring_boot_18.models;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="CAP_P1_VENTA")
public class Venta {
	@Id
	@GeneratedValue
	@Column(name="ID_VENTA")
	private int idVenta;
	
	@NotNull(message="Fecha de la venta no puede ser nula")
	@Column(name="FECHA")
    private Date ventaFecha;
	
	@NotNull(message="Total de la venta no puede ser nula")
	@Column(name="TOTAL")
    private float ventaTotal;
	
	@NotNull(message="Descuento de la venta no puede ser nula")
	@Column(name="DESCUENTO")
    private float ventaDescuento;
    
	@NotNull(message="Cliente (ID) de la venta no puede ser nulo")
    @Column(name="ID_CLIENTE")
    private String clienteCodigo;

    public Venta() {
    	
    }


    public Venta(int idVenta,Date ventaFecha,float ventaTotal,float ventaDescuento,String clienteCodigo) {
		this.idVenta = idVenta;
		this.ventaFecha = ventaFecha;
		this.ventaTotal = ventaTotal;
		this.ventaDescuento = ventaDescuento;
		this.clienteCodigo = clienteCodigo;
	}


	public int getIdVenta() {
		return idVenta;
	}


	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}


	public Date getVentaFecha() {
        return ventaFecha;
    }

    public void setVentaFecha(Date venta_fecha) {
        this.ventaFecha = venta_fecha;
    }

    public float getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(float venta_total) {
        this.ventaTotal = venta_total;
    }

    public float getVentaDescuento() {
        return ventaDescuento;
    }

    public void setVentaDescuento(float venta_descuento) {
        this.ventaDescuento = venta_descuento;
    }

	public String getClienteCodigo() {
		return clienteCodigo;
	}

	public void setClienteCodigo(String cliente_codigo) {
		this.clienteCodigo = cliente_codigo;
	}
	
}
