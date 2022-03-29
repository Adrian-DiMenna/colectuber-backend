package com.is.cole.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectivos.LineaDeColectivosDto;
import com.is.cole.services.lineas.ILineaColectivosService;

@RestController
@RequestMapping("/api/lineas_colectivos")
public class LineaDeColectivosController {

	@Autowired
	private ILineaColectivosService lineaService;
	
	
	@PostMapping
	public ResponseEntity<?> saveLineaColectivo(@RequestBody LineaDeColectivosDto dto){
		try {
			LineaDeColectivosDto dtoGuardado = lineaService.saveLineaColectivo(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLineaColectivo(@PathVariable("id") Integer lineaId){
		try {
			LineaDeColectivosDto dtoObtenido= lineaService.getLineaColectivo(lineaId);
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllLineaColectivo(){
		try {
			Result<LineaDeColectivosDto> dtos= lineaService.getAllLineaColectivo();
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLineaColectivo(@PathVariable("id") Integer lineaId){
		try {
			lineaService.deleteLineaColectivo(lineaId);
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
