package com.is.cole.dtos.colectivos;

import com.is.cole.dtos.BaseDto;

/**
 * Clase para el parseo de la entidad Lineas de Colectivos para el envio y recibo
 * @author Acer
 *
 */
public class LineaDeColectivosDto extends BaseDto{
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/************************ Variables Privadas ******************************/
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String numero;
}
