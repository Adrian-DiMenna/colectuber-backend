package com.is.cole.services.colectuber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.cole.daos.IColectivoDao;
import com.is.cole.daos.IColectivoUbicacionDao;
import com.is.cole.daos.IRecorridoDao;
import com.is.cole.dtos.PosicionDto;
import com.is.cole.dtos.Result;
import com.is.cole.dtos.colectuber.ColectivoUbicacionDto;
import com.is.cole.dtos.colectuber.InitialDataDto;
import com.is.cole.dtos.recorridos.PuntoDeRecorridoDto;
import com.is.cole.dtos.recorridos.RecorridoDto;
import com.is.cole.entities.ColectivoUbicacion;
import com.is.cole.entities.Recorrido;
import com.is.cole.services.ayuda.Deg2UTM;
import com.is.cole.services.colectivos.IColectivoService;
import com.is.cole.services.paradas.IParadaService;
import com.is.cole.services.recorridos.IRecorridoService;

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
	
	@Override
	public InitialDataDto getInitialData() {
		
		InitialDataDto dto = new InitialDataDto();
		
		dto.setColectivos(colectivoService.getAllColectivo().getResult());
		dto.setParadas(paradaService.getAllParadas().getResult());
		dto.setRecorridos(recorridoService.getAllRecorrido().getResult());
		dto.setColectivoUbicacion(getColectivosUbicacion().getResult());
		
		return dto;
	}
	
	
	@Override
	public void postColectivoUbicacion(ColectivoUbicacionDto dto) {
		ColectivoUbicacion ubi = parseDtoToBeanColectivoUbicacion(dto);
		colectivoUbicacionDao.save(ubi);
	}
	
	@Override
	public Result<ColectivoUbicacionDto> getColectivosUbicacion() {
		Result<ColectivoUbicacionDto> dtos = new Result<>();

		List<ColectivoUbicacionDto> listaDtos= colectivoUbicacionDao.findAll().stream()
		.map(colectivoUbicacion->parseBeanToDtoColectivoUbicacion(colectivoUbicacion))
		.collect(Collectors.toList());
		
		dtos.setResult(listaDtos);
		
		return dtos;
	}
	
	/******************* Special Functions *******************/
	
	@Override
	public void getDistanciasPrueba(ColectivoUbicacionDto dto) {
		setIndiceAndPorcentaje(dto);
	}
	
	private ColectivoUbicacionDto setIndiceAndPorcentaje(ColectivoUbicacionDto dto) {
		
		List<Double> distancias = getDistancias(dto);
		
		for(int i =0; i< distancias.size(); i++) {
			System.out.println("Al indice"+i+" distancia:"+distancias.get(i));
		}
		
		
		return null;
	}
	
	private List<Double> getDistancias(ColectivoUbicacionDto dto){
		RecorridoDto recorridoDto = recorridoService.getRecorrido(dto.getRecorrido_id());
		List<PuntoDeRecorridoDto> lista = recorridoDto.getPuntos();
		List<Double> distancias = new ArrayList<>();
		
		//Obtenemos el punto
		
		double puntoX= dto.getPosicionColectivo().getLongitud();
		double puntoY= dto.getPosicionColectivo().getLatitud();
		
		
		System.out.println("principal x:"+puntoX + "principal y:"+puntoY);
		
		for(int i=0; i< lista.size(); i++) {
			
			if((i+1) < lista.size() ) {
				
				//Obtenemos los dos puntos de la recta
				
				//Primer punto
				double  x1= lista.get(i).getPuntoPosicion().getLongitud();
				double  y1= lista.get(i).getPuntoPosicion().getLatitud(); 				
				
				//Segundo punto
				double  x2= lista.get(i+1).getPuntoPosicion().getLongitud();
				double  y2= lista.get(i+1).getPuntoPosicion().getLatitud(); 
				
				//Calculamos la pendiente
				double m= (y2 - y1)/(x2 - x1);
				
				//Calculamos A B y C de la ecuacion y - y1 = m (x - x1)
				double A= -m;
				double B=1;
				double C= -(y1 - m*x1);
				
				//Se calcula la distancia con el punto
				double distancia = (Math.abs(A*puntoX + B*puntoY + C))/(Math.sqrt((A*A) + (B*B)))
				distancias.add(distancia);
			}
		}
		return distancias;
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
		return ubi;
	}
	
	private ColectivoUbicacionDto parseBeanToDtoColectivoUbicacion(ColectivoUbicacion bean) {
		
		ColectivoUbicacionDto dto = new ColectivoUbicacionDto();
		
		PosicionDto posDto= new PosicionDto();
		posDto.setLatitud(bean.getLat());
		posDto.setLongitud(bean.getLng());
		
		dto.setColectivoId(bean.getColectivo().getId());
		dto.setPosicionColectivo(posDto);
		
		dto.setIndice(bean.getIndice());
		dto.setPorcentaje(bean.getPorcentaje());
		dto.setRecorrido_id(bean.getRecorrido().getId());
		return dto;
	}


	
	

	

	

}
