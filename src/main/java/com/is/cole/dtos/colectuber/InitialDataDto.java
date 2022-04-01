package com.is.cole.dtos.colectuber;

import java.util.List;

import com.is.cole.dtos.BaseDto;
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.dtos.paradas.ParadaDto;
import com.is.cole.dtos.recorridos.RecorridoDto;

public class InitialDataDto extends BaseDto{

	private static final long serialVersionUID = 1L;

	private List<ColectivoDto> colectivos;
	
	private List<ParadaDto> paradas;
	
	private List<RecorridoDto> recorridos;

	public List<ColectivoDto> getColectivos() {
		return colectivos;
	}

	public void setColectivos(List<ColectivoDto> colectivos) {
		this.colectivos = colectivos;
	}
	
	public List<ParadaDto> getParadas() {
		return paradas;
	}

	public void setParadas(List<ParadaDto> paradas) {
		this.paradas = paradas;
	}

	public List<RecorridoDto> getRecorridos() {
		return recorridos;
	}

	public void setRecorridos(List<RecorridoDto> recorridos) {
		this.recorridos = recorridos;
	}

	
}
