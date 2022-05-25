package com.is.cole.controllers;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.cole.dtos.Result;
import com.is.cole.dtos.paradas.ParadaDto;
import com.is.cole.services.paradas.IParadaService;

@RestController
@RequestMapping("/api/paradas")
@Secured("ROLE_ADMIN")
public class ParadaController {

	/**
	 * Agrega una parada
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> postParada(@RequestBody ParadaDto dto) {
		try {
			ParadaDto dtoGuardado = paradaService.saveParada(dto);
			logger.info("Parada: Post Parada: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
		} catch (IllegalArgumentException e) {
			logger.error("Parada: Post Parada"+e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("Parada: Post Parada"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtiene una parada por medio de un identificador
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getParada(@PathVariable(value = "id") Integer id) {
		try {
			ParadaDto dtoObtenido = paradaService.getParadaById(id);
			logger.info("Parada: Get Parada: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
		} catch (EntityNotFoundException e) {
			logger.error("Parada: Get Parada "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Parada: Get Parada "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtener todas las paradas
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllParadas() {
		try {
			Result<ParadaDto> dtos = paradaService.getAllParadas();
			logger.info("Parada: Get All Paradas: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		} catch (Exception e) {
			logger.error("Parada: Get All Paradas "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Eliminar una parada por medio de su identificador
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteParada(@PathVariable(value = "id") Integer id) {
		try {
			paradaService.deleteById(id);
			logger.info("Parada: Delete Parada: Exito");
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (EntityNotFoundException e) {
			logger.error("Parada: Delete Parada "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Parada: Delete Parada "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Autowired
	private IParadaService paradaService;
	private Logger logger = LogManager.getLogger(ParadaController.class.getClass());
}
