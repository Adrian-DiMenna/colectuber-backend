package com.is.cole.services.colectivos;

import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectivos.LineaDeColectivosDto;

public interface IColectivosService {
	

	//Lineas de Colectivo
	public LineaDeColectivosDto saveLineaColectivo(LineaDeColectivosDto dto);
	public LineaDeColectivosDto getLineaColectivo(Integer lineaId);
	public void deleteLineaColectivo(Integer lineaId);
	public Result<LineaDeColectivosDto> getAllLineaColectivo();
}
