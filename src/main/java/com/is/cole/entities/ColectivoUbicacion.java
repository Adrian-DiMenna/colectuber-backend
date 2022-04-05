package com.is.cole.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ubicaciones")
public class ColectivoUbicacion implements BaseBean{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, unique = true)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="colectivo_id",referencedColumnName="id")
	private Colectivo colectivo;
	
	private Double latitud;
	private Double longitud;
	private Long tiempo;
	
	@ManyToOne
	@JoinColumn(name="recorrido_id",referencedColumnName="id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Recorrido recorrido;
	
	private Integer indice;
	private Double porcentaje;
	
	
	public Recorrido getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(Recorrido recorrido) {
		this.recorrido = recorrido;
	}
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public Colectivo getColectivo() {
		return colectivo;
	}
	public void setColectivo(Colectivo colectivo) {
		this.colectivo = colectivo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLat() {
		return latitud;
	}
	public void setLat(Double lat) {
		this.latitud = lat;
	}
	public Double getLng() {
		return longitud;
	}
	public void setLng(Double lng) {
		this.longitud = lng;
	}
	public Long getTime() {
		return tiempo;
	}
	public void setTime(Long time) {
		this.tiempo = time;
	}
	
	

}
