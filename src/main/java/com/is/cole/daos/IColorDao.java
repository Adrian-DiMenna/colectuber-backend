package com.is.cole.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.is.cole.entities.Color;

@Repository
public interface IColorDao extends JpaRepository<Color, Integer> {
	/**
	 * Obtener un color por medio de su nombre
	 * @param nombre
	 * @return
	 */
	public Color findByNombre(String nombre);
	
}
