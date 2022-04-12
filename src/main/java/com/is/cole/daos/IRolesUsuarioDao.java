package com.is.cole.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.is.cole.entities.UsersRoles;

@Repository
public interface IRolesUsuarioDao extends JpaRepository<UsersRoles, Integer> {
	@Query("SELECT u FROM UsersRoles u WHERE u.usuarios.id = ?1 AND u.roles.id = ?2")
	public UsersRoles findByUsuarioIdRoleId(Integer usuarioId, Integer roleId);
	
	@Query("SELECT u FROM UsersRoles u WHERE u.usuarios.id = ?1")
	public List<UsersRoles> findByUsuarioId(Integer usuarioId);
	
	
}
