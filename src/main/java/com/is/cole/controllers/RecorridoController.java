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
import com.is.cole.dtos.recorridos.RecorridoDto;
import com.is.cole.services.recorridos.IRecorridoService;

@RestController
@RequestMapping("/api/recorridos")
@Secured("ROLE_ADMIN")
public class RecorridoController {
	
	@Autowired
	private IRecorridoService recorridoService;
	
	@PostMapping
	public ResponseEntity<?> postRecorrido(@RequestBody RecorridoDto dto){
		try {
			recorridoService.saveRecorrido(dto);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(Exception e){
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRecorrido(@PathVariable("id") Integer id){
		try {
			RecorridoDto dto = recorridoService.getRecorrido(id);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRecorridos(){
		try {
			Result<RecorridoDto> dtos= recorridoService.getAllRecorrido();
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRecorrido(@PathVariable("id") Integer id){
		try {
			recorridoService.deleteRecorrido(id);
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
