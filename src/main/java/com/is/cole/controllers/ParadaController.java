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
import com.is.cole.dtos.paradas.ParadaDto;
import com.is.cole.services.paradas.IParadaService;

@RestController
@RequestMapping("/api/paradas")
public class ParadaController {
	
	@Autowired
	private IParadaService paradaService;
	
	@PostMapping
	public ResponseEntity<?> postParada(@RequestBody ParadaDto dto){
		try {
			ParadaDto dtoGuardado = paradaService.saveParada(dto);
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getParada(@PathVariable(value = "id")Integer id){
		try {
			ParadaDto dtoObtenido = paradaService.getParadaById(id);
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllParadas(){
		try {
			Result<ParadaDto> dtos = paradaService.getAllParadas();
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteParada(@PathVariable(value = "id")Integer id){
		try {
			paradaService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(Exception e) {
			System.err.print(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
}