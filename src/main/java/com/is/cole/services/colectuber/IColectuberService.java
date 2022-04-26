package com.is.cole.services.colectuber;

import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectuber.ColectivoUbicacionDto;
import com.is.cole.dtos.colectuber.InitialDataDto;
import com.is.cole.dtos.colectuber.InitialViajeDto;

public interface IColectuberService {
	public void postColectivoUbicacion(ColectivoUbicacionDto dto);
	public Result<ColectivoUbicacionDto> getColectivosUbicacion(); 
	public InitialDataDto getInitialData();	
	public InitialViajeDto getViajeChofer(String choferUsername);
	
	
}
