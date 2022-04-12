package com.is.cole.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/Welcome")
	public String welcome() {
		return "Welcome to Colectuber";
	}
}
