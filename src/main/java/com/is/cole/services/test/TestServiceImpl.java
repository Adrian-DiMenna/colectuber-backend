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

		//Puente Ruta 1 Quiteria
		PuntoDeRecorridoDto puntoCircuito14 = new PuntoDeRecorridoDto();
		puntoCircuito14.setPuntoPosicion(new PosicionDto());
		puntoCircuito14.getPuntoPosicion().setLatitud(-27.29197758048552);
		puntoCircuito14.getPuntoPosicion().setLongitud(-55.91429378287496);

		PuntoDeRecorridoDto puntoCircuito15 = new PuntoDeRecorridoDto();
		puntoCircuito15.setPuntoPosicion(new PosicionDto());
		puntoCircuito15.getPuntoPosicion().setLatitud(-27.296690944851655);
		puntoCircuito15.getPuntoPosicion().setLongitud(-55.90573004662675);
		
		PuntoDeRecorridoDto puntoCircuito16 = new PuntoDeRecorridoDto();
		puntoCircuito16.setPuntoPosicion(new PosicionDto());
		puntoCircuito16.getPuntoPosicion().setLatitud(-27.30208712147181);
		puntoCircuito16.getPuntoPosicion().setLongitud(-55.89740446981006);
		
		PuntoDeRecorridoDto puntoCircuito17 = new PuntoDeRecorridoDto();
		puntoCircuito17.setPuntoPosicion(new PosicionDto());
		puntoCircuito17.getPuntoPosicion().setLatitud(-27.30490390675379);
		puntoCircuito17.getPuntoPosicion().setLongitud(-55.894311124265414);
		
		PuntoDeRecorridoDto puntoParadaRuta1_1 = new PuntoDeRecorridoDto();
		ParadaDto paradaDto = paradaMap.get("Parada Petrobras");
		puntoParadaRuta1_1.setParadaId(paradaDto.getId());
		puntoParadaRuta1_1.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoParadaRuta1_2 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Hotel City");
		puntoParadaRuta1_2.setParadaId(paradaDto.getId());
		puntoParadaRuta1_2.setPuntoPosicion(paradaDto.getPosicion());

		PuntoDeRecorridoDto puntoParadaRuta1_3 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Zona Uni");
		puntoParadaRuta1_3.setParadaId(paradaDto.getId());
		puntoParadaRuta1_3.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoParadaRuta1_4 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Supermercado La Familia");
		puntoParadaRuta1_4.setParadaId(paradaDto.getId());
		puntoParadaRuta1_4.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoParadaRuta1_5 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Colegio Kennedy");
		puntoParadaRuta1_5.setParadaId(paradaDto.getId());
		puntoParadaRuta1_5.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoCircuito18 = new PuntoDeRecorridoDto();
		puntoCircuito18.setPuntoPosicion(new PosicionDto());
		puntoCircuito18.getPuntoPosicion().setLatitud(-27.31366580260424);
		puntoCircuito18.getPuntoPosicion().setLongitud(-55.87762052625255);
		
		PuntoDeRecorridoDto puntoCircuito19 = new PuntoDeRecorridoDto();
		puntoCircuito19.setPuntoPosicion(new PosicionDto());
		puntoCircuito19.getPuntoPosicion().setLatitud(-27.314833422390926);
		puntoCircuito19.getPuntoPosicion().setLongitud(-55.87607447616899);
		
		//Puente Costanera
		PuntoDeRecorridoDto puntoCircuito20 = new PuntoDeRecorridoDto();
		puntoCircuito20.setPuntoPosicion(new PosicionDto());
		puntoCircuito20.getPuntoPosicion().setLatitud(-27.316187453488972);
		puntoCircuito20.getPuntoPosicion().setLongitud(-55.87528488630374);

		PuntoDeRecorridoDto puntoCircuito21 = new PuntoDeRecorridoDto();
		puntoCircuito21.setPuntoPosicion(new PosicionDto());
		puntoCircuito21.getPuntoPosicion().setLatitud(-27.321210960696177);
		puntoCircuito21.getPuntoPosicion().setLongitud(-55.87404252463375);
		
		//Rotonda
		PuntoDeRecorridoDto puntoCircuito22 = new PuntoDeRecorridoDto();
		puntoCircuito22.setPuntoPosicion(new PosicionDto());
		puntoCircuito22.getPuntoPosicion().setLatitud(-27.32161322388676);
		puntoCircuito22.getPuntoPosicion().setLongitud(-55.87420817285771);
		
		PuntoDeRecorridoDto puntoCircuito23 = new PuntoDeRecorridoDto();
		puntoCircuito23.setPuntoPosicion(new PosicionDto());
		puntoCircuito23.getPuntoPosicion().setLatitud(-27.322143031734818);
		puntoCircuito23.getPuntoPosicion().setLongitud(-55.87388791962573);
		
		PuntoDeRecorridoDto puntoParadaRuta1_6 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Super 6 Costanera");
		puntoParadaRuta1_6.setParadaId(paradaDto.getId());
		puntoParadaRuta1_6.setPuntoPosicion(paradaDto.getPosicion());

		PuntoDeRecorridoDto puntoCircuito24 = new PuntoDeRecorridoDto();
		puntoCircuito24.setPuntoPosicion(new PosicionDto());
		puntoCircuito24.getPuntoPosicion().setLatitud(-27.326914443343984);
		puntoCircuito24.getPuntoPosicion().setLongitud(-55.873217270546064);

		PuntoDeRecorridoDto puntoParadaAvCaballero_1 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Hotel Real");
		puntoParadaAvCaballero_1.setParadaId(paradaDto.getId());
		puntoParadaAvCaballero_1.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoCircuito25 = new PuntoDeRecorridoDto();
		puntoCircuito25.setPuntoPosicion(new PosicionDto());
		puntoCircuito25.getPuntoPosicion().setLatitud(-27.326640286418417);
		puntoCircuito25.getPuntoPosicion().setLongitud(-55.870054699268124);

		PuntoDeRecorridoDto puntoCircuito26 = new PuntoDeRecorridoDto();
		puntoCircuito26.setPuntoPosicion(new PosicionDto());
		puntoCircuito26.getPuntoPosicion().setLatitud(-27.326691848255166);
		puntoCircuito26.getPuntoPosicion().setLongitud(-55.86946038247785);

		PuntoDeRecorridoDto puntoParadaAvCaballero_2 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Mundo Mania");
		puntoParadaAvCaballero_2.setParadaId(paradaDto.getId());
		puntoParadaAvCaballero_2.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoCircuito27 = new PuntoDeRecorridoDto();
		puntoCircuito27.setPuntoPosicion(new PosicionDto());
		puntoCircuito27.getPuntoPosicion().setLatitud(-27.32651241295923);
		puntoCircuito27.getPuntoPosicion().setLongitud(-55.86694382233417);
		
		PuntoDeRecorridoDto puntoCircuito28 = new PuntoDeRecorridoDto();
		puntoCircuito28.setPuntoPosicion(new PosicionDto());
		puntoCircuito28.getPuntoPosicion().setLatitud(-27.32630113110705);
		puntoCircuito28.getPuntoPosicion().setLongitud(-55.8653410969685);
	
		PuntoDeRecorridoDto puntoParadaCarlosAntLopez_1 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Esquina Doña Reina");
		puntoParadaCarlosAntLopez_1.setParadaId(paradaDto.getId());
		puntoParadaCarlosAntLopez_1.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoParadaCarlosAntLopez_2 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Hierro Center");
		puntoParadaCarlosAntLopez_2.setParadaId(paradaDto.getId());
		puntoParadaCarlosAntLopez_2.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoParadaCarlosAntLopez_3 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Banco Atlas");
		puntoParadaCarlosAntLopez_3.setParadaId(paradaDto.getId());
		puntoParadaCarlosAntLopez_3.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoParadaCarlosAntLopez_4 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Colegio Centro");
		puntoParadaCarlosAntLopez_4.setParadaId(paradaDto.getId());
		puntoParadaCarlosAntLopez_4.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoParadaCarlosAntLopez_5 = new PuntoDeRecorridoDto();
		paradaDto = paradaMap.get("Parada Terminal");
		puntoParadaCarlosAntLopez_5.setParadaId(paradaDto.getId());
		puntoParadaCarlosAntLopez_5.setPuntoPosicion(paradaDto.getPosicion());
		
		PuntoDeRecorridoDto puntoCircuito29 = new PuntoDeRecorridoDto();
		puntoCircuito29.setPuntoPosicion(new PosicionDto());
		puntoCircuito29.getPuntoPosicion().setLatitud(-27.340895094890367);
		puntoCircuito29.getPuntoPosicion().setLongitud(-55.86373487761778);
		
		PuntoDeRecorridoDto puntoCircuito30 = new PuntoDeRecorridoDto();
		puntoCircuito30.setPuntoPosicion(new PosicionDto());
		puntoCircuito30.getPuntoPosicion().setLatitud(-27.34154946300029);
		puntoCircuito30.getPuntoPosicion().setLongitud(-55.86406703624476);
		
		PuntoDeRecorridoDto puntoCircuito31 = new PuntoDeRecorridoDto();
		puntoCircuito31.setPuntoPosicion(new PosicionDto());
		puntoCircuito31.getPuntoPosicion().setLatitud(-27.343572046007036);
		puntoCircuito31.getPuntoPosicion().setLongitud(-55.859460480730995);
		
		PuntoDeRecorridoDto puntoCircuito32 = new PuntoDeRecorridoDto();
		puntoCircuito32.setPuntoPosicion(new PosicionDto());
		puntoCircuito32.getPuntoPosicion().setLatitud(-27.34374657000926);
		puntoCircuito32.getPuntoPosicion().setLongitud(-55.85921048596473);
		
		
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

		ParadaDto paradaRuta1_1 = new ParadaDto();
		paradaRuta1_1.setNombre("Parada Petrobras");
		paradaRuta1_1.setDescripcion("Parada ruta 1 frente Av. 4");
		paradaRuta1_1.setPosicion(new PosicionDto());
		paradaRuta1_1.getPosicion().setLatitud(-27.30610661477075);
		paradaRuta1_1.getPosicion().setLongitud(-55.89254610085299);
		paradaRuta1_1.setImage(null);
		
		map.put("Parada Petrobras", paradaService.saveParada(paradaRuta1_1));
		
		ParadaDto paradaRuta1_2 = new ParadaDto();
		paradaRuta1_2.setNombre("Parada Hotel City");
		paradaRuta1_2.setDescripcion("Parada ruta 1 frente al hotel City");
		paradaRuta1_2.setPosicion(new PosicionDto());
		paradaRuta1_2.getPosicion().setLatitud(-27.306981212223608);
		paradaRuta1_2.getPosicion().setLongitud(-55.891181000556216);
		paradaRuta1_2.setImage(null);
		
		map.put("Parada Hotel City", paradaService.saveParada(paradaRuta1_2));
		
		ParadaDto paradaRuta1_3 = new ParadaDto();
		paradaRuta1_3.setNombre("Parada Zona Uni");
		paradaRuta1_3.setDescripcion("Parada ruta 1 UNI");
		paradaRuta1_3.setPosicion(new PosicionDto());
		paradaRuta1_3.getPosicion().setLatitud(-27.308664364353458);
		paradaRuta1_3.getPosicion().setLongitud(-55.88851370435971);
		paradaRuta1_3.setImage(null);
		
		map.put("Parada Zona Uni", paradaService.saveParada(paradaRuta1_3));

		ParadaDto paradaRuta1_4 = new ParadaDto();
		paradaRuta1_4.setNombre("Parada Supermercado La Familia");
		paradaRuta1_4.setDescripcion("Parada ruta 1, frente al supermercado la familia");
		paradaRuta1_4.setPosicion(new PosicionDto());
		paradaRuta1_4.getPosicion().setLatitud(-27.3114841856797);
		paradaRuta1_4.getPosicion().setLongitud(-55.88390687216109);
		paradaRuta1_4.setImage(null);
		
		map.put("Parada Supermercado La Familia", paradaService.saveParada(paradaRuta1_4));
		
		ParadaDto paradaRuta1_5 = new ParadaDto();
		paradaRuta1_5.setNombre("Parada Colegio Kennedy");
		paradaRuta1_5.setDescripcion("Parada ruta 1, frente al colegio John F. Kennedy");
		paradaRuta1_5.setPosicion(new PosicionDto());
		paradaRuta1_5.getPosicion().setLatitud(-27.312631684828208);
		paradaRuta1_5.getPosicion().setLongitud(-55.88072449336672);
		paradaRuta1_5.setImage(null);
		
		map.put("Parada Colegio Kennedy", paradaService.saveParada(paradaRuta1_5));

		ParadaDto paradaRuta1_6 = new ParadaDto();
		paradaRuta1_6.setNombre("Parada Super 6 Costanera");
		paradaRuta1_6.setDescripcion("Parada ruta 1, frente super 6 costanera");
		paradaRuta1_6.setPosicion(new PosicionDto());
		paradaRuta1_6.getPosicion().setLatitud(-27.32357055692744);
		paradaRuta1_6.getPosicion().setLongitud(-55.873611839255304);
		paradaRuta1_6.setImage(null);
		
		map.put("Parada Super 6 Costanera", paradaService.saveParada(paradaRuta1_6));
	
		ParadaDto paradaAvCaballero_1 = new ParadaDto();
		paradaAvCaballero_1.setNombre("Parada Hotel Real");
		paradaAvCaballero_1.setDescripcion("Parada Avenida Caballero, frente hotel real");
		paradaAvCaballero_1.setPosicion(new PosicionDto());
		paradaAvCaballero_1.getPosicion().setLatitud(-27.32670628556545);
		paradaAvCaballero_1.getPosicion().setLongitud(-55.87047722135734);
		paradaAvCaballero_1.setImage(null);
		
		map.put("Parada Hotel Real", paradaService.saveParada(paradaAvCaballero_1));
		
		ParadaDto paradaAvCaballero_2 = new ParadaDto();
		paradaAvCaballero_2.setNombre("Parada Mundo Mania");
		paradaAvCaballero_2.setDescripcion("Parada Avenida Caballero, frente a mundo mania");
		paradaAvCaballero_2.setPosicion(new PosicionDto());
		paradaAvCaballero_2.getPosicion().setLatitud(-27.32654541257507);
		paradaAvCaballero_2.getPosicion().setLongitud(-55.867412775419176);
		paradaAvCaballero_2.setImage(null);
		
		map.put("Parada Mundo Mania", paradaService.saveParada(paradaAvCaballero_2));
	
		ParadaDto paradaCarlosAntLopez_1 = new ParadaDto();
		paradaCarlosAntLopez_1.setNombre("Parada Esquina Doña Reina");
		paradaCarlosAntLopez_1.setDescripcion("Parada Carlos A. Lopez, esquina doña reina");
		paradaCarlosAntLopez_1.setPosicion(new PosicionDto());
		paradaCarlosAntLopez_1.getPosicion().setLatitud(-27.32751327416177);
		paradaCarlosAntLopez_1.getPosicion().setLongitud(-55.86524488183438);
		paradaCarlosAntLopez_1.setImage(null);
		
		map.put("Parada Esquina Doña Reina", paradaService.saveParada(paradaCarlosAntLopez_1));
		
		ParadaDto paradaCarlosAntLopez_2 = new ParadaDto();
		paradaCarlosAntLopez_2.setNombre("Parada Hierro Center");
		paradaCarlosAntLopez_2.setDescripcion("Parada Carlos A. Lopez, esquina hierro center");
		paradaCarlosAntLopez_2.setPosicion(new PosicionDto());
		paradaCarlosAntLopez_2.getPosicion().setLatitud(-27.328896067206827);
		paradaCarlosAntLopez_2.getPosicion().setLongitud(-55.86509726431732);
		paradaCarlosAntLopez_2.setImage(null);
		
		map.put("Parada Hierro Center", paradaService.saveParada(paradaCarlosAntLopez_2));
		
		ParadaDto paradaCarlosAntLopez_3 = new ParadaDto();
		paradaCarlosAntLopez_3.setNombre("Parada Banco Atlas");
		paradaCarlosAntLopez_3.setDescripcion("Parada Carlos A. Lopez, esquina banco atlas");
		paradaCarlosAntLopez_3.setPosicion(new PosicionDto());
		paradaCarlosAntLopez_3.getPosicion().setLatitud(-27.33151247100019);
		paradaCarlosAntLopez_3.getPosicion().setLongitud(-55.86479042955511);
		paradaCarlosAntLopez_3.setImage(null);
		
		map.put("Parada Banco Atlas", paradaService.saveParada(paradaCarlosAntLopez_3));
	
		ParadaDto paradaCarlosAntLopez_4 = new ParadaDto();
		paradaCarlosAntLopez_4.setNombre("Parada Colegio Centro");
		paradaCarlosAntLopez_4.setDescripcion("Parada Carlos A. Lopez, esquina stargol");
		paradaCarlosAntLopez_4.setPosicion(new PosicionDto());
		paradaCarlosAntLopez_4.getPosicion().setLatitud(-27.333958604879);
		paradaCarlosAntLopez_4.getPosicion().setLongitud(-55.864521921736795);
		paradaCarlosAntLopez_4.setImage(null);
		
		map.put("Parada Colegio Centro", paradaService.saveParada(paradaCarlosAntLopez_4));

		ParadaDto paradaCarlosAntLopez_5 = new ParadaDto();
		paradaCarlosAntLopez_5.setNombre("Parada Terminal");
		paradaCarlosAntLopez_5.setDescripcion("Parada Carlos A. Lopez, costado de la terminal de bus");
		paradaCarlosAntLopez_5.setPosicion(new PosicionDto());
		paradaCarlosAntLopez_5.getPosicion().setLatitud(-27.3404180233383);
		paradaCarlosAntLopez_5.getPosicion().setLongitud(-55.86382006960933);
		paradaCarlosAntLopez_5.setImage(null);
		
		map.put("Parada Terminal", paradaService.saveParada(paradaCarlosAntLopez_5));
		
		ParadaDto paradaSgtoRevenchon = new ParadaDto();
		paradaSgtoRevenchon.setNombre("Parada Gobernacion");
		paradaSgtoRevenchon.setDescripcion("Parada Sgto.Revenchon, costado de la gobernacion");
		paradaSgtoRevenchon.setPosicion(new PosicionDto());
		paradaSgtoRevenchon.getPosicion().setLatitud(-27.344185592805495);
		paradaSgtoRevenchon.getPosicion().setLongitud(-55.85817132115115);
		paradaSgtoRevenchon.setImage(null);
		
		map.put("Parada Terminal", paradaService.saveParada(paradaSgtoRevenchon));
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
