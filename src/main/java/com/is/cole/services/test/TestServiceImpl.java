package com.is.cole.services.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.dtos.PosicionDto;
import com.is.cole.dtos.Usuarios.RoleDto;
import com.is.cole.dtos.Usuarios.UsuarioDto;
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.dtos.colectivos.EmpresaDeColectivosDto;
import com.is.cole.dtos.colectivos.LineaDeColectivosDto;
import com.is.cole.dtos.paradas.ParadaDto;
import com.is.cole.dtos.recorridos.PuntoDeRecorridoDto;
import com.is.cole.dtos.recorridos.RecorridoDto;
import com.is.cole.services.colectivos.IColectivoService;
import com.is.cole.services.empresaColectivos.IEmpresaColectivosService;
import com.is.cole.services.lineas.ILineaColectivosService;
import com.is.cole.services.paradas.IParadaService;
import com.is.cole.services.recorridos.IRecorridoService;
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
	
	@Autowired
	private IRecorridoService recorridoService;
	
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
		
		dtoParada.setPosicion(new PosicionDto());
		dtoParada.getPosicion().setLatitud(25.15515);
		dtoParada.getPosicion().setLongitud(25.848945);

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
		
		
		RecorridoDto dtoRecorrido = new RecorridoDto();
		dtoRecorrido.setDescripcion("Recorrido desde la uni hasta el circuito");
		dtoRecorrido.setNombre("Principal A");
		
		List<PuntoDeRecorridoDto> puntos = new ArrayList<>();
		
		PuntoDeRecorridoDto punto1 = new PuntoDeRecorridoDto();
		punto1.setPuntoPosicion(new PosicionDto());
		punto1.getPuntoPosicion().setLatitud(4.5);
		punto1.getPuntoPosicion().setLongitud(5.5);
		
		PuntoDeRecorridoDto punto2 = new PuntoDeRecorridoDto();
		punto2.setPuntoPosicion(new PosicionDto());
		punto2.getPuntoPosicion().setLatitud(5.5);
		punto2.getPuntoPosicion().setLongitud(6.5);
		
		PuntoDeRecorridoDto punto3 = new PuntoDeRecorridoDto();
		punto3.setPuntoPosicion(new PosicionDto());
		punto3.getPuntoPosicion().setLatitud(6.5);
		punto3.getPuntoPosicion().setLongitud(7.5);
		
		PuntoDeRecorridoDto punto4 = new PuntoDeRecorridoDto();
		punto4.setPuntoPosicion(new PosicionDto());
		punto4.getPuntoPosicion().setLatitud(7.5);
		punto4.getPuntoPosicion().setLongitud(8.5);
		
		puntos.add(punto1);
		puntos.add(punto2);
		puntos.add(punto3);
		puntos.add(punto4);
		
		dtoRecorrido.setPuntos(puntos);
		
		recorridoService.saveRecorrido(dtoRecorrido);
		
		
		
		
		
	}
}
