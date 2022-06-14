package com.is.cole.dtos.colores;

import com.is.cole.dtos.BaseDto;

/**
 * Clase para el parseo de la entidad color para el envio y recibo
 * @author Acer
 *
 */
public class ColorDto extends BaseDto {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String color) {
		this.nombre = color;
	}
	
	/************************ Variables Privadas ******************************/
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;


}
