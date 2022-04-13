package com.is.cole.controllers;

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

	@Autowired
	private IEmpresaColectivosService empresaService;
	
	@PostMapping
	public ResponseEntity<?> saveEmpresaColectivo(@RequestBody EmpresaDeColectivosDto dto){
		try {
			EmpresaDeColectivosDto dtoGuardado = empresaService.saveEmpresaColectivo(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpresaColectivo(@PathVariable("id") Integer empresaId){
		try {
			EmpresaDeColectivosDto dtoObtenido= empresaService.getEmpresaColectivo(empresaId);
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllEmpresaColectivo(){
		try {
			Result<EmpresaDeColectivosDto> dtos= empresaService.getAllEmpresaColectivo();
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpresaColectivo(@PathVariable("id") Integer empresaId){
		try {
			empresaService.deleteEmpresaColectivo(empresaId);
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
