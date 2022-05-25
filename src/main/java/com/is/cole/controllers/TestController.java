package com.is.cole.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.is.cole.services.test.ITestService;

/**
 * Controller para la inserccion de datos de prueba a la base de datos
 * @author Colectuber
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

	/**
	 * Se Inserta todos los datos de prueba a la base de datos
	 * @return
	 */
	@PostMapping("/values")
	public ResponseEntity<?> postTestValues() {
		try {
			testService.insertTestValues();
			logger.info("Test: Post Data: Exito");
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (IllegalArgumentException e) {
			logger.error("Test: Post Data "+e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("Test: Post Data "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@Autowired
	private ITestService testService;
	private Logger logger = LogManager.getLogger(TestController.class.getClass());

}
