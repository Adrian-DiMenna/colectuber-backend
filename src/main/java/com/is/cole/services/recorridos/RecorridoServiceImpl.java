package com.is.cole.services.recorridos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IParadaDao;
import com.is.cole.daos.IPuntoDeRecorridoDao;
import com.is.cole.daos.IRecorridoDao;
import com.is.cole.dtos.PosicionDto;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.recorridos.PuntoDeRecorridoDto;
import com.is.cole.dtos.recorridos.RecorridoDto;
import com.is.cole.entities.PuntoDeRecorrido;
import com.is.cole.entities.Recorrido;

@Service
public class RecorridoServiceImpl implements IRecorridoService{

	@Autowired
	private IParadaDao paradaDao;
	@Autowired
	private IPuntoDeRecorridoDao puntoRecorridoDao;
	@Autowired
	private IRecorridoDao recorridoDao;
	
	
	@Override
	public RecorridoDto saveRecorrido(RecorridoDto dto) {
		List<PuntoDeRecorrido> puntosBean= parseDtosToBeanPuntosDeRecorrido(dto.getPuntos());
		
		Recorrido recorridoBean = parseDtoToBeanRecorrido(dto);
		recorridoBean.setPunto_inicial(puntosBean.get(0));
		
		Recorrido recorridoBeanSaved = recorridoDao.save(recorridoBean);
		List<PuntoDeRecorrido> puntosBeanSaved = puntoRecorridoDao.saveAll(puntosBean);
		
		RecorridoDto recorridoSaved = parseBeanToDtoRecorrido(recorridoBeanSaved);
		recorridoSaved.setPuntos(parseBeansToDtosPuntosDeRecorrido(puntosBeanSaved));
		return recorridoSaved;
	}

	@Override
	public RecorridoDto getRecorrido(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecorrido(Integer id) {
		recorridoDao.deleteById(id);
	} 
	
	@Override
	public Result<RecorridoDto> getAllRecorrido() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//Metodos Auxiliares
	
	//Parses Lista de puntos de Recorrido
	
	private List<PuntoDeRecorrido> parseDtosToBeanPuntosDeRecorrido(List<PuntoDeRecorridoDto> dtos) {
		
		List<PuntoDeRecorrido> beans = new ArrayList<PuntoDeRecorrido>();
		
		for(int i=0; i< dtos.size(); i++) {
			PuntoDeRecorridoDto dto = dtos.get(i);
			PuntoDeRecorridoDto dtoSig = dtos.get(i+1);
			PuntoDeRecorrido beanNuevo = parseDtoToBeanPuntoRecorrido(dto);
			beanNuevo.setSig_punto(parseDtoToBeanPuntoRecorrido(dtoSig));
			beans.add(beanNuevo);
		}
		
		
		return beans;
	}
	
	private List<PuntoDeRecorridoDto> parseBeansToDtosPuntosDeRecorrido(List<PuntoDeRecorrido> beans){
		
		List<PuntoDeRecorridoDto> dtos = new ArrayList<PuntoDeRecorridoDto>();
		beans.stream().map(
			bean -> dtos.add(parseBeanToDtoPuntoRecorrido(bean))
		).collect(Collectors.toList());
		
		return dtos;
	}
	
	
	
	//Parses PuntoRecorrido
	
	private PuntoDeRecorrido parseDtoToBeanPuntoRecorrido(PuntoDeRecorridoDto dto) {
		if(dto == null) {
			return null;
		}
		PuntoDeRecorrido bean = new PuntoDeRecorrido();
		bean.setId(dto.getId());
		if(dto.getParadaId() != null) {
			bean.setParada(paradaDao.getById(dto.getParadaId()));
			bean.setLatitud(null);
			bean.setLongitud(null);
		}else {
			bean.setLatitud(dto.getPuntoPosicion().getLatitud());
			bean.setLongitud(dto.getPuntoPosicion().getLongitud());
		}
		
		return bean;
	}
	
	private PuntoDeRecorridoDto parseBeanToDtoPuntoRecorrido(PuntoDeRecorrido bean) {
		if(bean == null) {
			return null;
		}
		PuntoDeRecorridoDto dto = new PuntoDeRecorridoDto();
		
		PosicionDto puntoDto = new PosicionDto();
		puntoDto.setLatitud(bean.getLatitud());
		puntoDto.setLongitud(bean.getLongitud());
		
		dto.setId(bean.getId());
		dto.setParadaId(bean.getParada().getId());
		dto.setPuntoPosicion(puntoDto);
		
		return dto;
	}
	
	// Parses Recorrido
	
	private Recorrido parseDtoToBeanRecorrido(RecorridoDto dto) {
		Recorrido bean = new Recorrido();
		bean.setDescripcion(dto.getDescripcion());
		bean.setId(dto.getId());
		bean.setNombre(dto.getNombre());
		return bean;
	}
	
	private RecorridoDto parseBeanToDtoRecorrido(Recorrido bean) {
		RecorridoDto dto = new RecorridoDto();
		dto.setDescripcion(bean.getDescripcion());
		dto.setId(bean.getId());
		dto.setNombre(bean.getNombre());
		return dto;
	}

	
	
	

}
