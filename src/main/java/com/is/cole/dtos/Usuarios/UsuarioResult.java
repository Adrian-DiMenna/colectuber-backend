package com.is.cole.dtos.Usuarios;

import java.util.List;

import com.is.cole.dtos.BaseDto;

public class UsuarioResult extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	private List<UsuarioDto> usuarios;

	public List<UsuarioDto> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDto> lista) {
		this.usuarios = lista;
	}
	
}
