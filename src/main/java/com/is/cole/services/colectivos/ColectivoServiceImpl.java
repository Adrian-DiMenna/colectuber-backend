package com.is.cole.services.colectivos;

import java.util.stream.Collectors;

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
		Colectivo beanGuardado = colectivoDao.save(parseDtoToBeanColectivo(dto));
		return parseBeanToDtoColectivo(beanGuardado);
	}

	@Override
	public ColectivoDto getColectivo(Integer id) {
		Colectivo beanObtenido = colectivoDao.getById(id);
		return parseBeanToDtoColectivo(beanObtenido);
	}

	@Override
	public void deleteColectivo(Integer id) {
		colectivoDao.deleteById(id);
		
	}

	@Override
	public Result<ColectivoDto> getAllColectivo() {
		Result<ColectivoDto> dtosObtenido = new Result<>();
		
		dtosObtenido.setResult(
			colectivoDao.findAll().stream()
			.map(cole -> parseBeanToDtoColectivo(cole))
			.collect(Collectors.toList())
		);
		return dtosObtenido;
	}
	
	//Parses
	
	private ColectivoDto parseBeanToDtoColectivo(Colectivo bean) {
		ColectivoDto dto = new ColectivoDto();
		dto.setEmpresaId(bean.getEmpresaColectivo().getId());
		dto.setId(bean.getId());
		dto.setLineaId(bean.getLineaColectivo().getId());
		dto.setNumero(bean.getNumeroColectivo());
		
		return dto;
	}
	
	
	private Colectivo parseDtoToBeanColectivo(ColectivoDto dto) {
		Colectivo bean = new Colectivo();
		bean.setEmpresaColectivo(empresaDao.getById(dto.getEmpresaId()));
		bean.setLineaColectivo(lineaDao.getById(dto.getLineaId()));
		bean.setId(dto.getId());
		bean.setNumeroColectivo(dto.getNumero());
		
		return bean;
		
	}

	


}
