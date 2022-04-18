package com.is.cole.dtos.Viajes;

import com.is.cole.dtos.BaseDto;
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.dtos.recorridos.RecorridoDto;

public class ViajeChoferDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private ColectivoDto colectivo;
	private RecorridoDto recorrido;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ColectivoDto getColectivo() {
		return colectivo;
	}
	public void setColectivo(ColectivoDto colectivo) {
		this.colectivo = colectivo;
	}
	public RecorridoDto getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(RecorridoDto recorrido) {
		this.recorrido = recorrido;
	}
	
	
	
}
