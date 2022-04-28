package com.is.cole.services.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.dtos.PosicionDto;
import com.is.cole.dtos.Usuarios.RoleDto;
import com.is.cole.dtos.Usuarios.UsuarioDto;
import com.is.cole.dtos.Viajes.ViajeDto;
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
import com.is.cole.services.viajes.IViajesService;

@Service
/**
 * Servicio para añadir datos iniciales de prueba
 * @author COLECTUBER
 *
 */
public class TestServiceImpl implements ITestService {
	
	@Override
	@Transactional
	public void insertTestValues() {
		insertViaje(
			insertRecorrido(insertParadas()),
			insertColectivo(insertEmpresaColectivo(), insertLinea()),
			insertUsuarios(insertRoles())
		);
		
	}
	
	private Map<String, ViajeDto> insertViaje(
		Map<String, RecorridoDto>recorridoMap,
		Map<String, ColectivoDto>colectivoMap,
		Map<String, UsuarioDto>usuarioMap
	){
		Map<String, ViajeDto> viajeMap= new HashMap<>();
		
		ViajeDto viajeUNI = new ViajeDto();
		viajeUNI.setDestino("Quiteria");
		viajeUNI.setRecorrido_id(recorridoMap.get("Recorrido UNI").getId());
		viajeUNI.setChofer_id(usuarioMap.get("antonio").getId());
		viajeUNI.setColectivo_id(colectivoMap.get("Colectivo23").getId());
		
		viajeUNI = viajeService.saveViaje(viajeUNI);
		
		viajeMap.put("Viaje UNI", viajeUNI);
		
		return viajeMap;
	}
	
	private Map<String, RecorridoDto> insertRecorrido(
			Map<String, ParadaDto>paradaMap){
		
		Map<String, RecorridoDto> map= new HashMap<>();
		
		
		RecorridoDto dtoRecorridoUni = new RecorridoDto();
		dtoRecorridoUni.setDescripcion("Recorrido Zona Centro a UNI ");
		dtoRecorridoUni.setNombre("Principal UNI");
		
		List<PuntoDeRecorridoDto> puntosUni = new ArrayList<>();
		
		PuntoDeRecorridoDto puntoUni1 = new PuntoDeRecorridoDto();
		puntoUni1.setPuntoPosicion(new PosicionDto());
		puntoUni1.getPuntoPosicion().setLatitud(-27.309675906048817);
		puntoUni1.getPuntoPosicion().setLongitud(-55.8866588781164);
		
		PuntoDeRecorridoDto puntoParadaUni2 = new PuntoDeRecorridoDto();
		ParadaDto paradaDto= paradaMap.get("Parada UNI");
		puntoParadaUni2.setParadaId(paradaDto.getId());
		puntoParadaUni2.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoUni3 = new PuntoDeRecorridoDto();
		puntoUni3.setPuntoPosicion(new PosicionDto());
		puntoUni3.getPuntoPosicion().setLatitud(-27.307803514569393);
		puntoUni3.getPuntoPosicion().setLongitud(-55.88771250328124);
		
		PuntoDeRecorridoDto puntoUni4 = new PuntoDeRecorridoDto();
		puntoUni4.setPuntoPosicion(new PosicionDto());
		puntoUni4.getPuntoPosicion().setLatitud(-27.306602964630766);
		puntoUni4.getPuntoPosicion().setLongitud(-55.88678283402331);
		
		PuntoDeRecorridoDto puntoUni5 = new PuntoDeRecorridoDto();
		puntoUni5.setPuntoPosicion(new PosicionDto());
		puntoUni5.getPuntoPosicion().setLatitud(-27.30583196240876);
		puntoUni5.getPuntoPosicion().setLongitud(-55.88615065892791);
		
		PuntoDeRecorridoDto puntoUni6 = new PuntoDeRecorridoDto();
		puntoUni6.setPuntoPosicion(new PosicionDto());
		puntoUni6.getPuntoPosicion().setLatitud(-27.30519312793853);
		puntoUni6.getPuntoPosicion().setLongitud(-55.887452195889026);
		
		puntosUni.add(puntoUni1);
		puntosUni.add(puntoParadaUni2);
		puntosUni.add(puntoUni3);
		puntosUni.add(puntoUni4);
		puntosUni.add(puntoUni5);
		puntosUni.add(puntoUni6);
		
		dtoRecorridoUni.setPuntos(puntosUni);
		
		dtoRecorridoUni= recorridoService.saveRecorrido(dtoRecorridoUni);
		
		map.put("Recorrido UNI", dtoRecorridoUni);
		
		
		return map;
	}
	
	
	
	private Map<String, RoleDto> insertRoles(){
		Map<String, RoleDto> map = new HashMap<String, RoleDto>();
		
		RoleDto rolAdmin = new RoleDto();
		rolAdmin.setNombre("ROLE_ADMIN");
		rolAdmin.setDescripcion("El usuario con rol administrador tiene acceso a todo el sistema.");
		map.put("admin", usuarioService.saveRole(rolAdmin));
		
		RoleDto rolChofer = new RoleDto();
		rolChofer.setNombre("ROLE_CHOFER");
		rolChofer.setDescripcion("El usuario con rol chofer representa la persona que maneja un colectivo");
		map.put("chofer", usuarioService.saveRole(rolChofer));
		
		return map;
	}
	
	private Map<String, UsuarioDto> insertUsuarios(Map<String, RoleDto> rolMap){
		Map<String, UsuarioDto> map = new HashMap<String, UsuarioDto>();
		
		UsuarioDto admin = new UsuarioDto();
		admin.setNombre("Administrador");
		admin.setApellido("Purete");
		admin.setCorreo_electronico("admin");
		admin.setPassword("muysecreto");
		map.put("admin", usuarioService.saveUsuario(admin));
		usuarioService.agregarRoleAUsuario(map.get("admin").getId(), rolMap.get("admin").getId());
		
		
		UsuarioDto chofer = new UsuarioDto();
		chofer.setNombre("Antonio");
		chofer.setApellido("Villas");
		chofer.setCorreo_electronico("antonio@gmail.com");
		chofer.setPassword("muysecreto");
		map.put("antonio", usuarioService.saveUsuario(chofer));
		usuarioService.agregarRoleAUsuario(map.get("antonio").getId(), rolMap.get("chofer").getId());
		
		return map;
	}
	
	private Map<String, ParadaDto> insertParadas(){
		Map<String, ParadaDto> map = new HashMap<String, ParadaDto>();
		
		ParadaDto paradaZonaUNI = new ParadaDto();
		paradaZonaUNI.setNombre("Zona UNI");
		paradaZonaUNI.setDescripcion("Es la parada de zona uni");
		paradaZonaUNI.setPosicion(new PosicionDto());
		paradaZonaUNI.getPosicion().setLatitud(-27.30855247497118);
		paradaZonaUNI.getPosicion().setLongitud(-55.88844384308095);
		paradaZonaUNI.setImage(null);
		map.put("Parada UNI", paradaService.saveParada(paradaZonaUNI));
		
		return map;
	}
	

	private Map<String, ColectivoDto> insertColectivo(Map<String, EmpresaDeColectivosDto> empresaMap,
			Map<String, LineaDeColectivosDto> lineaMap) {

		Map<String, ColectivoDto> map = new HashMap<>();

		ColectivoDto dto = new ColectivoDto();
		dto.setEmpresaId(empresaMap.get("urbano").getId());
		dto.setLineaId(lineaMap.get("Linea").getId());
		dto.setNumero("23");

		dto = colectivoService.saveColectivo(dto);
		map.put("Colectivo23", dto);

		return map;
	}
	
	private Map<String, LineaDeColectivosDto> insertLinea() {
		Map<String, LineaDeColectivosDto> map = new HashMap<>();

		LineaDeColectivosDto lineaDto = new LineaDeColectivosDto();
		lineaDto.setNumero("16");
		lineaDto = lineaService.saveLineaColectivo(lineaDto);
		map.put("Linea", lineaDto);

		return map;
	}
	
	private Map<String, EmpresaDeColectivosDto> insertEmpresaColectivo(){
		Map<String, EmpresaDeColectivosDto> map = new HashMap<>();
		
		EmpresaDeColectivosDto empresaUrbano = new EmpresaDeColectivosDto();
		empresaUrbano.setNombre("Empresa Urbano");
		empresaUrbano.setCorreo_electronico("empresa@gmail.com");
		empresaUrbano.setDireccion("Algun lugar");
		empresaUrbano.setNumero_telefono("0987654321");
		empresaUrbano = empresaService.saveEmpresaColectivo(empresaUrbano);
		map.put("urbano", empresaUrbano);
		
		return map;
	}
	
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
	
	@Autowired
	private IViajesService viajeService;

}
