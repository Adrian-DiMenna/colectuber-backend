package com.is.cole.services.colectuber;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.is.cole.daos.IColectivoDao;
import com.is.cole.daos.IColectivoUbicacionDao;
import com.is.cole.daos.IRecorridoDao;
import com.is.cole.dtos.PosicionDto;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.Viajes.ViajeDto;
import com.is.cole.dtos.colectivos.ColectivoDto;
import com.is.cole.dtos.colectuber.ColectivoUbicacionDto;
import com.is.cole.dtos.colectuber.ColectuberColectivoDto;
import com.is.cole.dtos.colectuber.InitialDataDto;
import com.is.cole.dtos.recorridos.RecorridoDto;
import com.is.cole.entities.ColectivoUbicacion;
import com.is.cole.services.colectivos.IColectivoService;
import com.is.cole.services.empresaColectivos.IEmpresaColectivosService;
import com.is.cole.services.lineas.ILineaColectivosService;
import com.is.cole.services.paradas.IParadaService;
import com.is.cole.services.recorridos.IRecorridoService;
import com.is.cole.services.viajes.IViajesService;

@Service
public class ColectuberServiceImpl implements IColectuberService{
	
	@Autowired
	private IColectivoUbicacionDao colectivoUbicacionDao;
	@Autowired
	private IColectivoDao colectivoDao;
	@Autowired
	private IColectivoService colectivoService;
	@Autowired
	private IParadaService paradaService;
	@Autowired
	private IRecorridoService recorridoService;
	@Autowired
	private IRecorridoDao recorridoDao;
	@Autowired
	private IViajesService viajeService;
	@Autowired
	private IEmpresaColectivosService empresaService;
	@Autowired
	private ILineaColectivosService lineaService;
	
	
	@Override
	@Transactional
	public InitialDataDto getInitialData() {

		InitialDataDto dto = new InitialDataDto();

		dto.setColectivos(colectivoService.getAllColectivo().getResult().stream()
				.map(c -> parseColectivoDtoToDtoColectuberDto(c)).collect(Collectors.toList()));

		dto.setParadas(paradaService.getAllParadas().getResult());

		dto.setRecorridos(recorridoService.getAllRecorrido().getResult());

		dto.setColectivoUbicacion(getColectivosUbicacion().getResult());

		return dto;
	}

	
	
	@Override
	@Transactional
	public void postColectivoUbicacion(ColectivoUbicacionDto dto) {
		
		ViajeDto viaje = viajeService.getByChoferIdViaje(dto.getChofer_id());
		double indicePorcentaje = getIndicePorcentajeFromPoint(recorridoService.getRecorrido(viaje.getRecorrido_id()), dto.getPosicionColectivo());
		
		ColectivoUbicacionDto dtoNuevo= new ColectivoUbicacionDto();
		dtoNuevo.setColectivoId(viaje.getColectivo_id());
		dtoNuevo.setChofer_id(viaje.getChofer_id());
		dtoNuevo.setIndicePorcentaje(indicePorcentaje);
		dtoNuevo.setPosicionColectivo(dto.getPosicionColectivo());
		dtoNuevo.setRecorrido_id(viaje.getRecorrido_id());
		
		ColectivoUbicacion ubi = parseDtoToBeanColectivoUbicacion(dtoNuevo);
		colectivoUbicacionDao.save(ubi);
	}
	
	@Override
	@Transactional
	public Result<ColectivoUbicacionDto> getColectivosUbicacion() {
		Result<ColectivoUbicacionDto> dtos = new Result<>();

		List<ColectivoUbicacionDto> listaDtos= colectivoUbicacionDao.findAll().stream()
		.map(colectivoUbicacion->parseBeanToDtoColectivoUbicacion(colectivoUbicacion))
		.collect(Collectors.toList());
		
		dtos.setResult(listaDtos);
		
		return dtos;
	}
	
	/******************* Special Functions *******************/
	
	private Double getIndicePorcentajeFromPoint(RecorridoDto recorrido, PosicionDto punto) {
		
		//Calcular la linea mas cercana al punto
		Integer indice = null;
		PosicionDto puntoR = null;
		
		for(int i=0; i< recorrido.getPuntos().size() - 1; i++) {
			PosicionDto posActual = recorrido.getPuntos().get(i).getPuntoPosicion();
			PosicionDto posSig= recorrido.getPuntos().get(i+1).getPuntoPosicion();
			
			PosicionDto posInter= intersectionPointToLine(punto, posActual, posSig);
			
			if( indice == null) {
				indice = i;
				puntoR= posInter;
			}else {
				double distanceRP= distancePointToPoint(punto, puntoR);
				double distanceIP= distancePointToPoint(punto, posInter);
				
				if(distanceIP < distanceRP) {
					indice = i;
					puntoR = posInter;
				}	
			}
		}
		
		//Pasar a indece porcentaje
		
		PosicionDto posIndice= recorrido.getPuntos().get(indice).getPuntoPosicion();
		PosicionDto posIndiceSig= recorrido.getPuntos().get(indice+1).getPuntoPosicion();
		
		double distanceIR= distancePointToPoint(posIndice, puntoR);
		double distanceIS= distancePointToPoint(posIndice, posIndiceSig);
		
		double porcentaje = distanceIR / distanceIS;
		
		double indicePorcentaje = indice + porcentaje;
		
		return indicePorcentaje;
	}

	
	private Double distancePointToPoint(PosicionDto punto1, PosicionDto punto2) {
		double resultado;
		double x1,y1,x2,y2;
		
		x1= punto1.getLongitud();
		y1= punto1.getLatitud();
		
		x2= punto2.getLongitud();
		y2= punto2.getLatitud();
		
		double dx = x2 - x1;
		double dy = y2 - y1;
		
		resultado = Math.sqrt(dx*dx + dy*dy);
		return resultado;
	}
	
	
	//Quitamos la formula de este post https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
	private PosicionDto intersectionPointToLine(PosicionDto punto, PosicionDto rectaPunto1, PosicionDto rectaPunto2) {
		
		double x,y,x1,y1,x2,y2;
		x= punto.getLongitud();
		y= punto.getLatitud();
		x1= rectaPunto1.getLongitud();
		y1= rectaPunto1.getLatitud();
		x2= rectaPunto2.getLongitud();
		y2= rectaPunto2.getLatitud();
		
		double A= x - x1;
		double B= y - y1;
		double C= x2 - x1;
		double D= y2 - y1;
		
		double dot= A * C + B * D;
		double len_sq= C * C + D * D;
		double param = -1;
		
		if(len_sq != 0) {
			param = dot / len_sq;
		}
		
		double xx,yy;
		
		if(param < 0) {
			xx= x1;
			yy= y1;
		}else if( param > 1) {
			xx = x2;
			yy = y2;
		}else {
			xx = x1 + param * C;
			yy = y1 + param * D;
		}
		
		PosicionDto resultado = new PosicionDto();
		resultado.setLongitud(xx);
		resultado.setLatitud(yy);
		return resultado;
	}
	
	
	
	/*Parses*/
	
	private ColectivoUbicacion parseDtoToBeanColectivoUbicacion(ColectivoUbicacionDto dto) {
		ColectivoUbicacion ubi= new ColectivoUbicacion();
		ubi.setId(dto.getColectivoId());
		ubi.setColectivo(colectivoDao.getById(dto.getColectivoId()));
		ubi.setLat(dto.getPosicionColectivo().getLatitud());
		ubi.setLng(dto.getPosicionColectivo().getLongitud());
		ubi.setTime(System.currentTimeMillis());
	
		ubi.setRecorrido(recorridoDao.getById(dto.getRecorrido_id()));
		ubi.setIndicePorcentaje(dto.getIndicePorcentaje());
		return ubi;
	}
	
	private ColectivoUbicacionDto parseBeanToDtoColectivoUbicacion(ColectivoUbicacion bean) {
		
		ColectivoUbicacionDto dto = new ColectivoUbicacionDto();
		
		PosicionDto posDto= new PosicionDto();
		posDto.setLatitud(bean.getLat());
		posDto.setLongitud(bean.getLng());
		
		dto.setColectivoId(bean.getColectivo().getId());
		dto.setPosicionColectivo(posDto);
		
		dto.setIndicePorcentaje(bean.getIndicePorcentaje());
		dto.setRecorrido_id(bean.getRecorrido().getId());
		return dto;
	}

	private ColectuberColectivoDto parseColectivoDtoToDtoColectuberDto(ColectivoDto dto) {

		ColectuberColectivoDto newDto = new ColectuberColectivoDto();

		newDto.setId(dto.getId());
		newDto.setEmpresa(empresaService.getEmpresaColectivo(dto.getEmpresaId()).getNombre());
		newDto.setLinea(lineaService.getLineaColectivo(dto.getLineaId()).getNumero());
		newDto.setNumero(dto.getNumero());

		return newDto;

	}
	
	

	

	

}
