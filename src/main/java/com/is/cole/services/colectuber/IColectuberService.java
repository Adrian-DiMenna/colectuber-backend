package com.is.cole.services.colectuber;

import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectuber.ColectivoUbicacionDto;
import com.is.cole.dtos.colectuber.InitialDataDto;

public interface IColectuberService {
	public void postColectivoUbicacion(ColectivoUbicacionDto dto);
	public Result<ColectivoUbicacionDto> getColectivosUbicacion(); 
	public InitialDataDto getInitialData();
	public void getDistanciasPrueba(ColectivoUbicacionDto dto);
}
