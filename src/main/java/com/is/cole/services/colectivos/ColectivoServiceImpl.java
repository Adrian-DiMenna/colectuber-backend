package com.is.cole.services.colectivos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IColectivoDao;
import com.is.cole.daos.IEmpresaDeColectivosDao;
import com.is.cole.daos.ILineaDao;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.entities.Colectivo;

@Service
public class ColectivoServiceImpl implements IColectivoService{

	@Autowired
	private IColectivoDao colectivoDao;
	@Autowired
	private ILineaDao lineaDao;
	@Autowired
	private IEmpresaDeColectivosDao empresaDao;
	
	
	//Metodos
	
	@Override
	public ColectivoDto saveColectivo(ColectivoDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColectivoDto getColectivo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteColectivo(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Result<ColectivoDto> getAllColectivo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Parses
	
	private ColectivoDto parseBeanToDtoColectivo(Colectivo bean) {
		ColectivoDto dto = new ColectivoDto();
		dto.setEmpresaColectivoId(bean.getEmpresaColectivo().getId());
		dto.setId(bean.getId());
		dto.setLineaColectivoId(bean.getLineaColectivo().getId());
		dto.setNumero(bean.getNumeroColectivo());
		
		return dto;
	}
	

	


}
