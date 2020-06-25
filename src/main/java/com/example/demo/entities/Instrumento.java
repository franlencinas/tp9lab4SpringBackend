package com.example.demo.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instrumento extends BaseEntity {

	private String instrumento;
	
	private String marca;
	
	private String modelo;
	
	private String imagen;
	
	private int precio;
	
	private String costoEnvio;
	
	private int cantidadVendida;
	
	private String descripcion;
	
	public Instrumento() {
		
	}
	
	public Instrumento(String instrumento, String marca, String modelo, String imagen, int precio, String costoEnvio,
			int cantidadVendida, String descripcion) {
		super();
		this.instrumento = instrumento;
		this.marca = marca;
		this.modelo = modelo;
		this.imagen = imagen;
		this.precio = precio;
		this.costoEnvio = costoEnvio;
		this.cantidadVendida = cantidadVendida;
		this.descripcion = descripcion;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(String costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}