package com.is.cole.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.is.cole.entities.ColectivoUbicacion;

@Repository
public interface IColectivoUbicacionDao extends JpaRepository<ColectivoUbicacion, Integer> {
	
	/**
	 * Funcion para obtener la ubicacion de un colectivo, por medio del id del colectivo
	 * @param colectivoId
	 * @return
	 */
	@Query("SELECT u FROM ColectivoUbicacion u WHERE u.colectivo.id = ?1")
	public ColectivoUbicacion findByColectivoId(Integer colectivoId);
	

}
