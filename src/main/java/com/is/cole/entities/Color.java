package com.is.cole.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad para la representacion de los colores de los recorridos
 * @author Colectuber
 *
 */
@Entity
@Table(name = "colores")
public class Color implements BaseBean {

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String nombre;
	
}