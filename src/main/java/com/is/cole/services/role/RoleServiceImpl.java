package com.is.cole.services.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IRoleDao;
import com.is.cole.dtos.role.RoleDto;
import com.is.cole.entities.Roles;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao dao;
	
	
	
	@Override
	public RoleDto saveRole(RoleDto dto) {
		Roles role = parseDtoToBean(dto);
		Roles guardarRole = dao.save(role);
		return parseBeanToDto(guardarRole);
	}

	@Override
	public void deleteRole(Integer id) {
		dao.deleteById(id);
	}
	
	protected RoleDto parseBeanToDto(Roles bean) {
		RoleDto dto = new RoleDto();
		
		dto.setId(bean.getId());
		dto.setNombre(bean.getNombre());
		dto.setDescripcion(bean.getDescription());
	
		return dto;
	}
	
	
	protected Roles parseDtoToBean(RoleDto dto) {
		Roles bean = new Roles();
		
		bean.setId(dto.getId());
		bean.setNombre(dto.getNombre());
		bean.setDescription(dto.getDescripcion());
		
		return bean;
	}
}
	

	

