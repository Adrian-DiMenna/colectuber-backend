package com.is.cole.services.colectuber;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IColectivoDao;
import com.is.cole.daos.IColectivoUbicacionDao;
import com.is.cole.dtos.PosicionDto;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectuber.ColectivoUbicacionDto;
import com.is.cole.dtos.colectuber.InitialDataDto;
import com.is.cole.entities.ColectivoUbicacion;
import com.is.cole.services.colectivos.IColectivoService;
import com.is.cole.services.paradas.IParadaService;
import com.is.cole.services.recorridos.IRecorridoService;

@Service
public class ColectuberServiceImpl implements IColectuberService{
	
	@Autowired
	private IColectivoUbicacionDao colectivoUbicacionDao;
	
	@Autowired
	private IColectivoDao colectivoDao;
	
	@Autowired
	private IColectivoService colectivoService;
	
	@Autowired
	private IParadaService paradaService;
	
	@Autowired
	private IRecorridoService recorridoService;
	
	
	@Override
	public InitialDataDto getInitialData() {
		
		InitialDataDto dto = new InitialDataDto();
		
		dto.setColectivos(colectivoService.getAllColectivo().getResult());
		
		dto.setParadas(paradaService.getAllParadas().getResult());
		
		dto.setRecorridos(recorridoService.getAllRecorrido().getResult());
		
		dto.setColectivoUbicacion(getColectivosUbicacion().getResult());
		
		return dto;
	}
	
	
	@Override
	public void postColectivoUbicacion(ColectivoUbicacionDto dto) {
		ColectivoUbicacion ubi = parseDtoToBeanColectivoUbicacion(dto);
		colectivoUbicacionDao.save(ubi);
	}
	
	@Override
	public Result<ColectivoUbicacionDto> getColectivosUbicacion() {
		Result<ColectivoUbicacionDto> dtos = new Result<>();

		List<ColectivoUbicacionDto> listaDtos= colectivoUbicacionDao.findAll().stream()
		.map(colectivoUbicacion->parseBeanToDtoColectivoUbicacion(colectivoUbicacion))
		.collect(Collectors.toList());
		
		dtos.setResult(listaDtos);
		
		return dtos;
	}
	
	
	
	/*Parses*/
	
	private ColectivoUbicacion parseDtoToBeanColectivoUbicacion(ColectivoUbicacionDto dto) {
		ColectivoUbicacion ubi= new ColectivoUbicacion();
		ubi.setId(dto.getColectivoId());
		ubi.setColectivo(colectivoDao.getById(dto.getColectivoId()));
		ubi.setLat(dto.getPosicionColectivo().getLatitud());
		ubi.setLng(dto.getPosicionColectivo().getLongitud());
		ubi.setTime(System.currentTimeMillis());

		return ubi;
		
	}
	
	private ColectivoUbicacionDto parseBeanToDtoColectivoUbicacion(ColectivoUbicacion bean) {
		
		ColectivoUbicacionDto dto = new ColectivoUbicacionDto();
		
		PosicionDto posDto= new PosicionDto();
		posDto.setLatitud(bean.getLat());
		posDto.setLongitud(bean.getLng());
		
		dto.setColectivoId(bean.getColectivo().getId());
		dto.setPosicionColectivo(posDto);
	
		return dto;
		
	}

	

	

}
