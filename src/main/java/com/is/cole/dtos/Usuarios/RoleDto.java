package com.is.cole.dtos.Usuarios;

import com.is.cole.dtos.BaseDto;

/**
 * Clase para el parseo de la entidad de roles de usuarios
 * @author Acer
 *
 */
public class RoleDto extends BaseDto {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/************************ Variables Privadas ******************************/
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nombre;

	private String descripcion;
}
