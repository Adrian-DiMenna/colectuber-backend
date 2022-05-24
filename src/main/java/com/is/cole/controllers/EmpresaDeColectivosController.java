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
import com.is.cole.dtos.colectivos.EmpresaDeColectivosDto;
import com.is.cole.services.empresaColectivos.IEmpresaColectivosService;

@RestController
@RequestMapping("/api/empresas_colectivos")
@Secured("ROLE_ADMIN")
public class EmpresaDeColectivosController {

	/**
	 * Agrega una nueva empresa de Colectivo
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveEmpresaColectivo(@RequestBody EmpresaDeColectivosDto dto) {
		try {
			EmpresaDeColectivosDto dtoGuardado = empresaService.saveEmpresaColectivo(dto);
			logger.info("Empresa Colectivo: Post Empresa: exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
		} catch (IllegalArgumentException e) {
			logger.error("Empresa Colectivo: Post Empresa "+e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("Empresa Colectivo: Post Empresa "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtiene una nueva empresa de colectivo por medio de un identificador
	 * @param empresaId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpresaColectivo(@PathVariable("id") Integer empresaId) {
		try {
			EmpresaDeColectivosDto dtoObtenido = empresaService.getEmpresaColectivo(empresaId);
			logger.info("Empresa Colectivo: Get Empresa: exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
		} catch (EntityNotFoundException e) {
			logger.error("Empresa Colectivo: Get Empresa "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Empresa Colectivo: Get Empresa "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtiene todas las empresas de colectivo
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllEmpresaColectivo() {
		try {
			Result<EmpresaDeColectivosDto> dtos = empresaService.getAllEmpresaColectivo();
			logger.info("Empresa Colectivo: Get All Empresas: exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		} catch (Exception e) {
			logger.error("Empresa Colectivo: Get All Empresas "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Elimina una empresa de colectivo por medio de un identificador
	 * @param empresaId
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpresaColectivo(@PathVariable("id") Integer empresaId) {
		try {
			empresaService.deleteEmpresaColectivo(empresaId);
			logger.info("Empresa Colectivo: Delete Empresa: exito");
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Empresa Colectivo: Delete Empresa "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Autowired
	private IEmpresaColectivosService empresaService;
	private Logger logger = LogManager.getLogger(EmpresaDeColectivosController.class.getClass());
}
