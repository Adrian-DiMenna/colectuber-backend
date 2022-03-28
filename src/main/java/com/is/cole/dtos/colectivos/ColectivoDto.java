package com.is.cole.dtos.colectivos;

import com.is.cole.dtos.BaseDto;

public class ColectivoDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer empresaColectivoId;
	private Integer lineaColectivoId;
	private String numero;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmpresaColectivoId() {
		return empresaColectivoId;
	}
	public void setEmpresaColectivoId(Integer empresaColectivoId) {
		this.empresaColectivoId = empresaColectivoId;
	}
	public Integer getLineaColectivoId() {
		return lineaColectivoId;
	}
	public void setLineaColectivoId(Integer lineaColectivoId) {
		this.lineaColectivoId = lineaColectivoId;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	

}
