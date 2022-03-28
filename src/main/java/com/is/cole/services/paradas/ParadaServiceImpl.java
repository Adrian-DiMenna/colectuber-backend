package com.is.cole.services.paradas;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IParadaDao;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.paradas.ParadaDto;
import com.is.cole.entities.Parada;

import java.util.List;

@Service
public class ParadaServiceImpl implements IParadaService {

	@Autowired
	private IParadaDao paradaDao;
	
	@Override
	public ParadaDto saveParada(ParadaDto dto) {
		Parada bean = parseDtotoBean(dto);
		Parada beanGuardado = paradaDao.save(bean);
		return parseBeanToDto(beanGuardado);
	}

	@Override
	public Result<ParadaDto> getAllParadas() {
		Result<ParadaDto> result = new Result<>();
		List<ParadaDto> list = paradaDao.findAll().stream().map((bean)->{
			return parseBeanToDto(bean);
		}).collect(Collectors.toList());
		result.setResult(list);
		return result;
	}

	@Override
	public ParadaDto getParadaById(Integer id) {
		Parada beanObtenido = paradaDao.getById(id);
		return parseBeanToDto(beanObtenido);
	}

	@Override
	public void deleteById(Integer id) {
		paradaDao.deleteById(id);
	}
	
	private ParadaDto parseBeanToDto(Parada bean) {
		ParadaDto dto = new ParadaDto();
		
		dto.setId(bean.getId());
		dto.setNombre(bean.getNombre());
		dto.setDescripcion(bean.getDescripcion());
		dto.setImage(bean.getImagen());
		dto.setLongitud(bean.getLongitud());
		dto.setLatitud(bean.getLatitud());
		
		return dto;
	}
	
	private Parada parseDtotoBean(ParadaDto dto) {
		Parada bean = new Parada();
		
		bean.setId(dto.getId());
		bean.setNombre(dto.getNombre());
		bean.setDescripcion(dto.getDescripcion());
		bean.setImagen(dto.getImage());
		bean.setLongitud(dto.getLongitud());
		bean.setLatitud(dto.getLatitud());
		
		return bean;
	}

}
