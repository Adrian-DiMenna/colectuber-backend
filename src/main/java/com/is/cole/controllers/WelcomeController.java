package com.is.cole.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.is.cole.entities.AuthRequest;
import com.is.cole.util.JwtUtil;

@RestController
public class WelcomeController {
	
	@Autowired
	private JwtUtil jwUtil;
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@GetMapping("/Welcome")
	public String welcome() {
		return "Welcome to Colectuber";
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		}catch(Exception ex) {
			throw new Exception("Inavalid username/password");
		}
		
		return jwUtil.generateToken(authRequest.getUserName());
	}
	
	
		
}
