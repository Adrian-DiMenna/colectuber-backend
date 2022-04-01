package com.is.cole.services.viajes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IRoleDao;
import com.is.cole.daos.IRolesUsuarioDao;
import com.is.cole.daos.IViajeDao;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.Viajes.ViajeDto;
import com.is.cole.entities.Roles;

@Service
public class ViajesServiceImpl implements IViajesService{

	@Autowired
	private IViajeDao viajeDao;
	@Autowired
	private IRolesUsuarioDao roleUsuarioDao;
	@Autowired
	private IRoleDao roleDao;
	
	//Estados
	private final Integer PENDIENTE = 0; 
	private final Integer EN_CURSO = 1;
	private final Integer FINALIZADO = 2;
	
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
	
	
}
