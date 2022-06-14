package com.is.cole.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entidad para la representacion de los colectivos
 * @author Acer
 *
 */
@Entity
@Table(name = "colectivos",uniqueConstraints =@UniqueConstraint(name="uk_numero",columnNames = {"numero_colectivo"}))
public class Colectivo implements BaseBean{

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EmpresaDeColectivos getEmpresaColectivo() {
		return empresaColectivo;
	}
	public void setEmpresaColectivo(EmpresaDeColectivos empresaColectivo) {
		this.empresaColectivo = empresaColectivo;
	}
	public Linea getLineaColectivo() {
		return lineaColectivo;
	}
	public void setLineaColectivo(Linea lineaColectivo) {
		this.lineaColectivo = lineaColectivo;
	}
	public String getNumeroColectivo() {
		return numeroColectivo;
	}
	public void setNumeroColectivo(String numeroColectivo) {
		this.numeroColectivo = numeroColectivo;
	}
	
	/************************ Variables Privadas ******************************/
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique= true)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="empresa_id",referencedColumnName="id")
	private EmpresaDeColectivos empresaColectivo;
	
	@ManyToOne
	@JoinColumn(name="linea_id",referencedColumnName="id")
	private Linea lineaColectivo;
	
	@Column(name="numero_colectivo")
	private String numeroColectivo;
	
	
	

}
