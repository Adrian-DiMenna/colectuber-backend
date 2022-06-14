package com.is.cole.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.is.cole.entities.Usuarios;

@Repository
public interface IUserDao extends JpaRepository<Usuarios, Integer> {
	
	/**
	 * Funcion para obtener un usuario por medio de su correo
	 * @param username
	 * @return
	 */
	public Usuarios findByCorreo(String username);
}

