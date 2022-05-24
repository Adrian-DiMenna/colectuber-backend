package com.is.cole.controllers;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.is.cole.dtos.Usuarios.RoleDto;
import com.is.cole.dtos.Usuarios.UsuarioDto;
import com.is.cole.services.usuarios.IUsuariosService;


@RestController
@RequestMapping("/api/usuarios")
@Secured("ROLE_ADMIN")
public class UsuarioController {

	/****************************** Usuarios *************************************/

	/**
	 * Introducir nuevo Usuario
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> postUsuario(@RequestBody UsuarioDto dto) {
		try {
			UsuarioDto dtoGuardado = usuariosService.saveUsuario(dto);
			logger.info("Usuario: Post Usuario: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoGuardado);
		} catch (IllegalArgumentException e) {
			logger.error("Usuario: Post Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("Usuario: Post Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtener usuario por medio del indentificador
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable(value = "id") Integer id) {
		try {
			UsuarioDto dtoObtenido = usuariosService.getUsuario(id);
			logger.info("Usuario: Get Usuario: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtoObtenido);
		} catch (EntityNotFoundException e) {
			logger.error("Usuario: Get Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Usuario: Get Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtener todos los Usuarios
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllUsuario() {
		try {
			Result<UsuarioDto> dtos = usuariosService.getAllUsuario();
			logger.info("Usuario: Get All Usuarios: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		} catch (Exception e) {
			logger.error("Usuario: Get All Usuarios "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Eliminar un Usuario por medio del identificador
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer id) {
		try {
			usuariosService.deleteUsuario(id);
			logger.info("Usuario: Delete Usuario: Exito");
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (EntityNotFoundException e) {
			logger.error("Usuario: Delete Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Usuario: Delete Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/*************************** Roles ******************************************/

	/**
	 * Agregar un nuevo Role
	 * @param role
	 * @return
	 */
	@PostMapping("/roles")
	public ResponseEntity<?> create(@RequestBody RoleDto role) {
		try {
			RoleDto dto = usuariosService.saveRole(role);
			logger.info("Role: Post Role: Exito");
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		} catch (IllegalArgumentException e) {
			logger.error("Role: Post Role "+e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("Role: Post Role "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtener un role por medio de un identificador
	 * @param id
	 * @return
	 */
	@GetMapping("/roles/{id}")
	public ResponseEntity<?> getRole(@PathVariable("id") Integer id) {
		try {
			RoleDto dto = usuariosService.getRole(id);
			logger.info("Role: Get Role: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} catch (EntityNotFoundException e) {
			logger.error("Role: Get Role "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Role: Get Role "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Obtener todos los roles
	 * @return
	 */
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRole() {
		try {
			Result<RoleDto> dtos = usuariosService.getAllRole();
			logger.info("Role: Get All Roles: Exito");
			return ResponseEntity.status(HttpStatus.OK).body(dtos);
		} catch (Exception e) {
			logger.error("Role: Get All Roles "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Eliminar un role por medio del identificador
	 * @param id
	 * @return
	 */
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			usuariosService.deleteRole(id);
			logger.info("Role: Delete Role: Exito");
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (EntityNotFoundException e) {
			logger.error("Role: Delete Role "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Role: Delete Role "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Agrega un Rol a Usuario
	 * @param userId
	 * @param dto
	 * @return
	 */
	@PostMapping("/rol_usuario/{user_id}")
	public ResponseEntity<?> agregarRolAUsuario(@PathVariable("user_id") Integer userId, @RequestBody RoleDto dto) {
		try {
			if (dto.getId() != null) {
				usuariosService.agregarRoleAUsuario(userId, dto.getId());
				logger.info("Role Usuario: Post Role Usuario: Exito");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else if (dto.getNombre() != null) {
				usuariosService.agregarRoleAUsuario(userId, dto.getNombre());
				logger.info("Role Usuario: Post Role Usuario: Exito");
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			logger.error("Rol Usuario: Post Rol Usuario: Bad Request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (IllegalArgumentException e) {
			logger.error("Rol Usuario: Post Rol Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("Rol Usuario: Post Rol Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Elimina un rol a usuario
	 * @param userId
	 * @param dto
	 * @return
	 */
	@DeleteMapping("/rol_usuario/{user_id}")
	public ResponseEntity<?> sacarRolAUsuario(@PathVariable("user_id") Integer userId, @RequestBody RoleDto dto) {
		try {
			if (dto.getId() != null) {
				usuariosService.quitarRoleAUsuario(userId, dto.getId());
				logger.info("Role Usuario: Delete Role Usuario: Exito");
				return ResponseEntity.status(HttpStatus.OK).build();
			} else if (dto.getNombre() != null) {
				usuariosService.quitarRoleAUsuario(userId, dto.getNombre());
				logger.info("Role Usuario: Delete Role Usuario: Exito");
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			logger.error("Rol Usuario: Delete Rol Usuario: Bad Request");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (EntityNotFoundException e) {
			logger.error("Rol Usuario: Delete Rol Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Rol Usuario: Delete Rol Usuario "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Autowired
	private IUsuariosService usuariosService;
	private Logger logger = LogManager.getLogger(UsuarioController.class.getClass());

}
