package com.is.cole.dtos.Viajes;

import com.is.cole.dtos.BaseDto;
import com.is.cole.dtos.Usuarios.UsuarioChoferDto;
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.dtos.recorridos.RecorridoDto;

public class ViajeChoferDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private UsuarioChoferDto chofer;
	private ColectivoDto colectivo;
	private RecorridoDto recorrido;
	
	public UsuarioChoferDto getChofer() {
		return chofer;
	}
	public void setChofer(UsuarioChoferDto chofer) {
		this.chofer = chofer;
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
