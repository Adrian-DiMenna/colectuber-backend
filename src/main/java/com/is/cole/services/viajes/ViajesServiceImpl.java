package com.is.cole.services.viajes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IColectivoDao;
import com.is.cole.daos.IRecorridoDao;
import com.is.cole.daos.IRoleDao;
import com.is.cole.daos.IRolesUsuarioDao;
import com.is.cole.daos.IUserDao;
import com.is.cole.daos.IViajeDao;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.Viajes.ViajeDto;
import com.is.cole.entities.Roles;
import com.is.cole.entities.Viaje;

@Service
public class ViajesServiceImpl implements IViajesService {

	@Autowired
	private IViajeDao viajeDao;
	@Autowired
	private IUserDao usuarioDao;
	@Autowired
	private IColectivoDao colectivoDao;
	@Autowired
	private IRecorridoDao recorridoDao;
	
	// Estados
	private final Integer PENDIENTE = 0;
	private final Integer EN_CURSO = 1;
	private final Integer INACTIVO = 2;
	private final Integer FINALIZADO = 3;

	// Umbrales de tiempo
	private final Long UMBRAL_ACTIVO = (long) 100000;
	private final Long UMBRAL_TERMINADO = (long) 2000000;

	/********************** Normal CRUDs **********************/

	@Override
	public ViajeDto saveViaje(ViajeDto dto) {

		return null;
	}

	@Override
	public void deleteViaje(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViajeDto getViaje(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ViajeDto> getAllViaje() {
		// TODO Auto-generated method stub
		return null;
	}

	/********************** Special functions **********************/

	@Override
	public ViajeDto getByChoferIdViaje(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/********************** Specific functions **********************/

	/********************** Parses **********************/

	private ViajeDto parseBeanToDtoViaje(Viaje bean) {
		ViajeDto dto = new ViajeDto();
		dto.setChofer_id(bean.getUsuario().getId());
		dto.setColectivo_id(bean.getColectivo().getId());
		dto.setDestino(bean.getDestino());
		dto.setRecorrido_id(bean.getRecorrido().getId());

		// set state
		Long last_update = bean.getLast_update();
		Long this_time = new Date().getTime();

		if (bean.getTerminado() == true) {
			dto.setEstado(FINALIZADO);
		} else if (last_update == null) {
			dto.setEstado(PENDIENTE);
		} else {
			Long diff_time = this_time - last_update;
			if (diff_time > UMBRAL_TERMINADO) {
				dto.setEstado(FINALIZADO);
			} else if (diff_time > 0 && diff_time < UMBRAL_ACTIVO) {
				dto.setEstado(EN_CURSO);
			} else {
				dto.setEstado(INACTIVO);
			}
		}
		return dto;
	}
	
	private Viaje parseDtoToBeanViaje(ViajeDto dto) {
		Viaje bean = new Viaje();
		bean.setColectivo(colectivoDao.getById(dto.getColectivo_id()));
		bean.setDestino(dto.getDestino());
		bean.setId(dto.getId());
		bean.setRecorrido(recorridoDao.getById(dto.getRecorrido_id()));
		bean.setUsuario(usuarioDao.getById(dto.getChofer_id()));
		
		//Estados
		if(dto.getEstado()== FINALIZADO) {
			bean.setTerminado(true);
		}else {
			bean.setTerminado(false);
		}
		bean.setLast_update(null);
		
		return bean;	
	}
}
