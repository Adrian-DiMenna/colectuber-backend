package com.is.cole.dtos.colectuber;

import com.is.cole.dtos.BaseDto;
import com.is.cole.dtos.PosicionDto;

public class ColectivoUbicacionDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	private PosicionDto posicionColectivo;
	private Integer colectivoId;
	private Integer indice;
	private Double porcentaje;
	private Integer recorrido_id;

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

	public Integer getRecorrido_id() {
		return recorrido_id;
	}

	public void setRecorrido_id(Integer recorrido_id) {
		this.recorrido_id = recorrido_id;
	}

	public PosicionDto getPosicionColectivo() {
		return posicionColectivo;
	}

	public void setPosicionColectivo(PosicionDto posicionColectivo) {
		this.posicionColectivo = posicionColectivo;
	}

	public Integer getColectivoId() {
		return colectivoId;
	}

	public void setColectivoId(Integer colectivoId) {
		this.colectivoId = colectivoId;
	}
	
	

}
