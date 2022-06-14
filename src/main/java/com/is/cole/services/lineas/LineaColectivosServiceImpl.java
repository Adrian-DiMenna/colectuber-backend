package com.is.cole.services.lineas;

import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.is.cole.daos.ILineaDao;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectivos.LineaDeColectivosDto;
import com.is.cole.entities.Linea;

@Service
public class LineaColectivosServiceImpl implements ILineaColectivosService {

	// Metodos
	
	/**
	 * Se guarda una linea de colectivos (Sirve tanto para crear uno nuevo,
	 * como tambien para actualizar uno anterior)
	 */
	@Override
	@Transactional
	public LineaDeColectivosDto saveLineaColectivo(LineaDeColectivosDto dto) {

		Linea beanGuardado = lineaDao.save(parseDtoToBeanLineaColectivo(dto));
		return parseBeanToDtoLineaColectivo(beanGuardado);

	}
	
	/**
	 * Se obtiene una linea de colectivo por medio de su id
	 */
	@Override
	@Transactional
	public LineaDeColectivosDto getLineaColectivo(Integer lineaId) {
		Linea beanObtenido = lineaDao.getById(lineaId);
		return parseBeanToDtoLineaColectivo(beanObtenido);
	}
	
	/**
	 * Se elimina una linea de colectivo por medio de su id
	 */
	@Override
	@Transactional
	public void deleteLineaColectivo(Integer lineaId) {
		lineaDao.deleteById(lineaId);
	}
	
	/**
	 * Se obtiene todas las lineas de colectivos
	 */
	@Override
	@Transactional
	public Result<LineaDeColectivosDto> getAllLineaColectivo() {
		Result<LineaDeColectivosDto> dtos = new Result<>();

		dtos.setResult(lineaDao.findAll().stream().map(lineaBean -> parseBeanToDtoLineaColectivo(lineaBean))
				.collect(Collectors.toList()));
		return dtos;
	}

	// Parses

	/**
	 * Convierte de Bean a Dto la entidad LineaDeColectivo
	 * 
	 * @param bean
	 * @return
	 */
	private LineaDeColectivosDto parseBeanToDtoLineaColectivo(Linea bean) {
		LineaDeColectivosDto dto = new LineaDeColectivosDto();
		dto.setId(bean.getId());
		dto.setNumero(bean.getNumero());
		return dto;
	}

	/**
	 * Convierte de Dto a Bean la entidad LineaDeColectivo
	 * 
	 * @param dto
	 * @return
	 */
	private Linea parseDtoToBeanLineaColectivo(LineaDeColectivosDto dto) {
		Linea bean = new Linea();
		bean.setId(dto.getId());
		bean.setNumero(dto.getNumero());
		return bean;
	}

	@Autowired
	private ILineaDao lineaDao;

}
