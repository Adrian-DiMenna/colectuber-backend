package com.is.cole.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.cole.dtos.recorridos.RecorridoDto;
import com.is.cole.services.recorridos.IRecorridoService;

@RestController
@RequestMapping("/api/recorridos")
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

}
