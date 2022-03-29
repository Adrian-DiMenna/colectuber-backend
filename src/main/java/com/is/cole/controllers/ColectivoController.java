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
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.services.colectivos.IColectivoService;

@RestController
@RequestMapping("/api/colectivos")
public class ColectivoController {
	
	@Autowired
	private IColectivoService colectivoService;
	

	@PostMapping
	public ResponseEntity<?> saveColectivo(@RequestBody ColectivoDto dto){
		try {
			ColectivoDto dtoGuardado = colectivoService.saveColectivo(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getColectivo(@PathVariable("id") Integer colectivoId){
		try {
			ColectivoDto dtoObtenido= colectivoService.getColectivo(colectivoId);
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllColectivo(){
		try {
			Result<ColectivoDto> dtos= colectivoService.getAllColectivo();
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteColectivo(@PathVariable("id") Integer colectivoId){
		try {
			colectivoService.deleteColectivo(colectivoId);
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
