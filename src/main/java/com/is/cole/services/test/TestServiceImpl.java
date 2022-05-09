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
import com.is.cole.dtos.colores.ColorDto;
import com.is.cole.dtos.paradas.ParadaDto;
import com.is.cole.dtos.recorridos.PuntoDeRecorridoDto;
import com.is.cole.dtos.recorridos.RecorridoDto;
import com.is.cole.services.colectivos.IColectivoService;
import com.is.cole.services.colores.IColorService;
import com.is.cole.services.empresaColectivos.IEmpresaColectivosService;
import com.is.cole.services.lineas.ILineaColectivosService;
import com.is.cole.services.paradas.IParadaService;
import com.is.cole.services.recorridos.IRecorridoService;
import com.is.cole.services.usuarios.IUsuariosService;
import com.is.cole.services.viajes.IViajesService;

@Service
/**
 * Servicio para añadir datos iniciales de prueba
 * @author Colectuber
 */
public class TestServiceImpl implements ITestService {

	@Override
	@Transactional
	public void insertTestValues() {
		insertViaje(insertRecorrido(insertParadas(),insertColor()), insertColectivo(insertEmpresaColectivo(), insertLinea()),
				insertUsuarios(insertRoles()));

	}

	private Map<String, ViajeDto> insertViaje(Map<String, RecorridoDto> recorridoMap,
			Map<String, ColectivoDto> colectivoMap, Map<String, UsuarioDto> usuarioMap) {
		Map<String, ViajeDto> viajeMap = new HashMap<>();

		ViajeDto viajeUNI = new ViajeDto();
		viajeUNI.setDestino("Circuito");
		viajeUNI.setRecorrido_id(recorridoMap.get("Recorrido UNI").getId());
		viajeUNI.setChofer_id(usuarioMap.get("antonio").getId());
		viajeUNI.setColectivo_id(colectivoMap.get("Colectivo23").getId());

		viajeUNI = viajeService.saveViaje(viajeUNI);

		viajeMap.put("Viaje UNI", viajeUNI);

		return viajeMap;
	}

	private Map<String, RecorridoDto> insertRecorrido(Map<String, ParadaDto> paradaMap,
			Map<String, ColorDto> colorMap) {

		Map<String, RecorridoDto> map = new HashMap<>();

		RecorridoDto dtoRecorridoUni = new RecorridoDto();
		dtoRecorridoUni.setDescripcion("Recorrido Ruta 1, San Juan del Paraná a Circuito");
		dtoRecorridoUni.setNombre("Principal Ruta 1 Circuito");
		dtoRecorridoUni.setColor(colorMap.get("red").getNombre());

//		List<PuntoDeRecorridoDto> puntosUni = new ArrayList<>();
//
//		PuntoDeRecorridoDto puntoUni1 = new PuntoDeRecorridoDto();
//		puntoUni1.setPuntoPosicion(new PosicionDto());
//		puntoUni1.getPuntoPosicion().setLatitud(-27.25497891238917);
//		puntoUni1.getPuntoPosicion().setLongitud(-55.985275337417804);
//		
//		PuntoDeRecorridoDto puntoParadaUni2 = new PuntoDeRecorridoDto();
//		ParadaDto paradaDto = paradaMap.get("Parada UNI");
//		puntoParadaUni2.setParadaId(paradaDto.getId());
//		puntoParadaUni2.setPuntoPosicion(paradaDto.getPosicion());
//
//		PuntoDeRecorridoDto puntoUni3 = new PuntoDeRecorridoDto();
//		puntoUni3.setPuntoPosicion(new PosicionDto());
//		puntoUni3.getPuntoPosicion().setLatitud(-27.307803514569393);
//		puntoUni3.getPuntoPosicion().setLongitud(-55.88771250328124);
//
//		PuntoDeRecorridoDto puntoUni4 = new PuntoDeRecorridoDto();
//		puntoUni4.setPuntoPosicion(new PosicionDto());
//		puntoUni4.getPuntoPosicion().setLatitud(-27.306602964630766);
//		puntoUni4.getPuntoPosicion().setLongitud(-55.88678283402331);
//
//		PuntoDeRecorridoDto puntoUni5 = new PuntoDeRecorridoDto();
//		puntoUni5.setPuntoPosicion(new PosicionDto());
//		puntoUni5.getPuntoPosicion().setLatitud(-27.30583196240876);
//		puntoUni5.getPuntoPosicion().setLongitud(-55.88615065892791);
//
//		PuntoDeRecorridoDto puntoUni6 = new PuntoDeRecorridoDto();
//		puntoUni6.setPuntoPosicion(new PosicionDto());
//		puntoUni6.getPuntoPosicion().setLatitud(-27.30519312793853);
//		puntoUni6.getPuntoPosicion().setLongitud(-55.887452195889026);
//
//		puntosUni.add(puntoUni1);
//		puntosUni.add(puntoParadaUni2);
//		puntosUni.add(puntoUni3);
//		puntosUni.add(puntoUni4);
//		puntosUni.add(puntoUni5);
//		puntosUni.add(puntoUni6);
//
//		dtoRecorridoUni.setPuntos(puntosUni);

//		dtoRecorridoUni = recorridoService.saveRecorrido(dtoRecorridoUni);
//
//		map.put("Recorrido UNI", dtoRecorridoUni);

		
		List<PuntoDeRecorridoDto> puntosCircuito = new ArrayList<>();
		
		PuntoDeRecorridoDto puntoCircuito1 = new PuntoDeRecorridoDto();
		puntoCircuito1.setPuntoPosicion(new PosicionDto());
		puntoCircuito1.getPuntoPosicion().setLatitud(-27.255055213899475);
		puntoCircuito1.getPuntoPosicion().setLongitud(-55.98523242207336);
		
		PuntoDeRecorridoDto puntoCircuito2 = new PuntoDeRecorridoDto();
		puntoCircuito2.setPuntoPosicion(new PosicionDto());
		puntoCircuito2.getPuntoPosicion().setLatitud(-27.26003377430376);
		puntoCircuito2.getPuntoPosicion().setLongitud(-55.97991091936177);
		
		PuntoDeRecorridoDto puntoCircuito3 = new PuntoDeRecorridoDto();
		puntoCircuito3.setPuntoPosicion(new PosicionDto());
		puntoCircuito3.getPuntoPosicion().setLatitud(-27.26039618953818);
		puntoCircuito3.getPuntoPosicion().setLongitud(-55.978666374372764);

		PuntoDeRecorridoDto puntoCircuito4 = new PuntoDeRecorridoDto();
		puntoCircuito4.setPuntoPosicion(new PosicionDto());
		puntoCircuito4.getPuntoPosicion().setLatitud(-27.26039618953818);
		puntoCircuito4.getPuntoPosicion().setLongitud(-55.978666374372764);
		
		PuntoDeRecorridoDto puntoCircuito5 = new PuntoDeRecorridoDto();
		puntoCircuito5.setPuntoPosicion(new PosicionDto());
		puntoCircuito5.getPuntoPosicion().setLatitud(-27.26031449215873);
		puntoCircuito5.getPuntoPosicion().setLongitud(-55.97690938024482);
		
		PuntoDeRecorridoDto puntoCircuito6 = new PuntoDeRecorridoDto();
		puntoCircuito6.setPuntoPosicion(new PosicionDto());
		puntoCircuito6.getPuntoPosicion().setLatitud(-27.260201074629936);
		puntoCircuito6.getPuntoPosicion().setLongitud(-55.976411786723446);

		PuntoDeRecorridoDto puntoCircuito7 = new PuntoDeRecorridoDto();
		puntoCircuito7.setPuntoPosicion(new PosicionDto());
		puntoCircuito7.getPuntoPosicion().setLatitud(-27.25801209367452);
		puntoCircuito7.getPuntoPosicion().setLongitud(-55.970453423293115);

		PuntoDeRecorridoDto puntoCircuito8 = new PuntoDeRecorridoDto();
		puntoCircuito8.setPuntoPosicion(new PosicionDto());
		puntoCircuito8.getPuntoPosicion().setLatitud(-27.257944041762155);
		puntoCircuito8.getPuntoPosicion().setLongitud(-55.969751688839914);

		PuntoDeRecorridoDto puntoCircuito9 = new PuntoDeRecorridoDto();
		puntoCircuito9.setPuntoPosicion(new PosicionDto());
		puntoCircuito9.getPuntoPosicion().setLatitud(-27.257966725725538);
		puntoCircuito9.getPuntoPosicion().setLongitud(-55.969292371771616);
		
		
		PuntoDeRecorridoDto puntoCircuito10 = new PuntoDeRecorridoDto();
		puntoCircuito10.setPuntoPosicion(new PosicionDto());
		puntoCircuito10.getPuntoPosicion().setLatitud(-27.25859053324217);
		puntoCircuito10.getPuntoPosicion().setLongitud(-55.96760820909109);

		PuntoDeRecorridoDto puntoCircuito11 = new PuntoDeRecorridoDto();
		puntoCircuito11.setPuntoPosicion(new PosicionDto());
		puntoCircuito11.getPuntoPosicion().setLatitud(-27.29211732521895);
		puntoCircuito11.getPuntoPosicion().setLongitud(-55.93365369426801);
		
		PuntoDeRecorridoDto puntoCircuito12 = new PuntoDeRecorridoDto();
		puntoCircuito12.setPuntoPosicion(new PosicionDto());
		puntoCircuito12.getPuntoPosicion().setLatitud(-27.293007303588755);
		puntoCircuito12.getPuntoPosicion().setLongitud(-55.93158866664654);

		PuntoDeRecorridoDto puntoCircuito13 = new PuntoDeRecorridoDto();
		puntoCircuito13.setPuntoPosicion(new PosicionDto());
		puntoCircuito13.getPuntoPosicion().setLatitud(-27.291596199144017);
		puntoCircuito13.getPuntoPosicion().setLongitud(-55.91641809238406);

		PuntoDeRecorridoDto puntoCircuito14 = new PuntoDeRecorridoDto();
		puntoCircuito14.setPuntoPosicion(new PosicionDto());
		puntoCircuito14.getPuntoPosicion().setLatitud(-27.29197758048552);
		puntoCircuito14.getPuntoPosicion().setLongitud(-55.91429378287496);

		return map;
	}

	private Map<String, RoleDto> insertRoles() {
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

	private Map<String, UsuarioDto> insertUsuarios(Map<String, RoleDto> rolMap) {
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

	private Map<String, ParadaDto> insertParadas() {
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

	private Map<String, EmpresaDeColectivosDto> insertEmpresaColectivo() {
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

	private Map<String, ColorDto> insertColor() {

		Map<String, ColorDto> resultMap = new HashMap<>();

		String[] arreglo = { "red", "orange", "yellow", "green", "cyan", "blue", "purple", "gray" };

		for (String nombre : arreglo) {
			ColorDto newDto = new ColorDto();
			newDto.setNombre(nombre);
			newDto = colorService.saveColor(newDto);
			resultMap.put(newDto.getNombre(), newDto);
		}

		return resultMap;

	}
	
	/********************** Variables Privadas **********************/
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

	@Autowired
	private IColorService colorService;
}
