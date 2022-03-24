package com.is.cole.services.role;

import com.is.cole.dtos.role.RoleDto;

public interface IRoleService {
	public RoleDto saveRole(RoleDto dto);
	public void deleteRole(Integer id);
}
