package com.is.cole.services.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.is.cole.dtos.Usuarios.RoleDto;
import com.is.cole.dtos.Usuarios.UsuarioDto;
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.dtos.colectivos.EmpresaDeColectivosDto;
import com.is.cole.dtos.colectivos.LineaDeColectivosDto;
import com.is.cole.dtos.paradas.ParadaDto;
import com.is.cole.services.colectivos.IColectivoService;
import com.is.cole.services.empresaColectivos.IEmpresaColectivosService;
import com.is.cole.services.lineas.ILineaColectivosService;
import com.is.cole.services.paradas.IParadaService;
import com.is.cole.services.usuarios.IUsuariosService;

@Service
public class TestServiceImpl implements ITestService {
	
	@Autowired
	private IColectivoService colectivoService;
	
	@Autowired
	private IEmpresaColectivosService empresaService;
	
	@Autowired
	private ILineaColectivosService lineaService;

	@Autowired
	private IParadaService paradaService;
	
	@Autowired
	private IUsuariosService usuarioService;
	
	@Override
	public void insertTestValues() {
		
		LineaDeColectivosDto dtoLinea = new LineaDeColectivosDto();
		
		dtoLinea.setNumero("Linea 1");
		
		dtoLinea = lineaService.saveLineaColectivo(dtoLinea);
		
		
		EmpresaDeColectivosDto dtoEmpresa = new EmpresaDeColectivosDto();
		
		dtoEmpresa.setNombre("Urbano");
		dtoEmpresa.setCorreo_electronico("urbano@gmail.com");
		dtoEmpresa.setDireccion("Ruta 6");
		dtoEmpresa.setNumero_telefono("098756485");
		
		dtoEmpresa = empresaService.saveEmpresaColectivo(dtoEmpresa);
		
		ColectivoDto dtoColectivo = new ColectivoDto();
		
		dtoColectivo.setLineaId(dtoLinea.getId());
		dtoColectivo.setEmpresaId(dtoEmpresa.getId());
		dtoColectivo.setNumero("23");
		
		colectivoService.saveColectivo(dtoColectivo);
		
		ParadaDto dtoParada = new ParadaDto();
		
		dtoParada.setNombre("Zona UNI");
		dtoParada.setDescripcion("Es la parada de zona uni");
		dtoParada.setLatitud(25.15515);
		dtoParada.setLongitud(25.848945);
		dtoParada.setImage(null);
		
		
		paradaService.saveParada(dtoParada);
		
		UsuarioDto dtoUsuario = new UsuarioDto();
		RoleDto dtoRole = new RoleDto();
		
		dtoUsuario.setNombre("Angel");
		dtoUsuario.setApellido("David");
		dtoUsuario.setCorreo_electronico("angel.david@gmail.com");
		dtoUsuario.setPassword("muysecreto");
		
		dtoRole.setNombre("Administrador");
		dtoRole.setDescripcion("Es admin");
		
		dtoRole = usuarioService.saveRole(dtoRole);
		dtoUsuario = usuarioService.saveUsuario(dtoUsuario);
		usuarioService.agregarRoleAUsuario(dtoUsuario.getId(), dtoRole.getId());
		
		
	}
}
