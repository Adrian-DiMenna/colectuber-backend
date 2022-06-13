package com.is.cole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad obtenida de un usuario para realizar la autenticacion  
 * @author Colectuber
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/************************ Variables Privadas ******************************/
	private String userName;
	private String password;


}
