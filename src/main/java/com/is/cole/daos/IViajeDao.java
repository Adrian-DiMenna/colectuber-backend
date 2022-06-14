package com.is.cole.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.is.cole.entities.Viaje;

@Repository
public interface IViajeDao extends JpaRepository<Viaje,Integer>{

	/**
	 * Funcion para obtener un viaje por medio del id del usuario
	 * @param usuarioId
	 * @return
	 */
	@Query("SELECT u FROM Viaje u WHERE u.usuario.id = ?1")
	public Viaje findByUsuarioId(Integer usuarioId);
	
	/**
	 * Obtener un viaje por medio del correo del usuario
	 * @param choferUsername
	 * @return
	 */
	@Query("SELECT u FROM Viaje u WHERE u.usuario.correo = ?1")
	public Viaje findByUsername(String choferUsername);
	
	/**
	 * Funcion para obtener un viaje por medio del id del colectivo
	 * @param id
	 * @return
	 */
	@Query("SELECT u FROM Viaje u WHERE u.colectivo.id = ?1")
	public Viaje findByColectivoId(Integer id);
	
}
