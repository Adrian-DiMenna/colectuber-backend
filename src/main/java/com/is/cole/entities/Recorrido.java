package com.is.cole.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Entidad que define los atributos basicos de un recorrido
 * @author Colectuber
 */
@Entity
@Table(name = "recorridos")
public class Recorrido implements BaseBean {
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PuntoDeRecorrido getPunto_inicial() {
		return punto_inicial;
	}

	public void setPunto_inicial(PuntoDeRecorrido punto_inicial) {
		this.punto_inicial = punto_inicial;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	/************************ Variables Privadas ******************************/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "puntos_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private PuntoDeRecorrido punto_inicial;

	@OneToOne
	@JoinColumn(name = "color_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Color color;

	private String nombre;
	private String descripcion;
}
