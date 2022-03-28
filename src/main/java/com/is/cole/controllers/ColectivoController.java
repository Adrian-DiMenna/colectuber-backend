package com.is.cole.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.is.cole.services.colectivos.IColectivoService;

@RestController
@RequestMapping("/api/colectivos")
public class ColectivoController {
	
	@Autowired
	private IColectivoService colectivoService;
	
	
}
