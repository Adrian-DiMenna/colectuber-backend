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
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.services.colectivos.IColectivoService;

@RestController
@RequestMapping("/api/colectivos")
@Secured("ROLE_ADMIN")
public class ColectivoController {
	
	/**
	 * Agrega un Colectivo nuevo
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveColectivo(@RequestBody ColectivoDto dto){
		try {
			ColectivoDto dtoGuardado = colectivoService.saveColectivo(dto);
			logger.info("Colectivo: Post Colectivo: exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
		} catch(IllegalArgumentException e) {
			logger.error("Colectivo: Post Colectivo "+e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}catch(Exception e) {
			logger.error("Colectivo: Post Colectivo "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * Obtener un colectivo por medio de su identificador
	 * @param colectivoId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getColectivo(@PathVariable("id") Integer colectivoId){
		try {
			ColectivoDto dtoObtenido= colectivoService.getColectivo(colectivoId);
			logger.info("Colectivo: Get Colectivo: exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
		}catch(EntityNotFoundException e) {
			logger.error("Colectivo: Get Colectivo "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e) {
			logger.error("Colectivo: Get Colectivo "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * Obtener todos los colectivos
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllColectivo(){
		try {
			Result<ColectivoDto> dtos= colectivoService.getAllColectivo();
			logger.info("Colectivo: Get All Colectivos: exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtos);	
		}catch(Exception e) {
			logger.error("Colectivo: Get All Colectivos "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/**
	 * Eliminar un colectivo por medio de su identificador
	 * @param colectivoId
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteColectivo(@PathVariable("id") Integer colectivoId){
		try {
			colectivoService.deleteColectivo(colectivoId);
			logger.info("Colectivo: Delete Colectivo: exito");
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(EntityNotFoundException e) {
			logger.error("Colectivo: Delete Colectivo "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(Exception e) {
			logger.error("Colectivo: Delete Colectivo "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Autowired
	private IColectivoService colectivoService;
	private Logger logger = LogManager.getLogger(ColectivoController.class.getClass());
	
}
