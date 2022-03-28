package com.is.cole.services.colectivos;

import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectivos.EmpresaDeColectivosDto;

public interface IColectivosService {
	
	//Empresas de Colectivo
	public EmpresaDeColectivosDto saveEmpresaColectivo(EmpresaDeColectivosDto dto);
	public EmpresaDeColectivosDto getEmpresaColectivo(Integer empresaId);
	public void deleteEmpresaColectivo(Integer empresaId);
	public Result<EmpresaDeColectivosDto> getAllEmpresaColectivo();
	
	
}
