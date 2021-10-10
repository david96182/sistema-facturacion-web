package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.ServicioDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class ServicioServiceImpl implements ServicioService {

	@Autowired
	private RestService restService;
	
	@Override
	public List<ServicioDto> getServicios() {
        List<ServicioDto> servicios = new ArrayList<ServicioDto>();
		
		try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<ServicioDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/servicios", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		    servicios = apiRestMapper.mapList(response, ServicioDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return servicios;
		
	}

	@Override
	public ServicioDto getServicioById(String servicioid) {
		ServicioDto servicio = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<ServicioDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/servicios/{servicioid}");
			String uri = template.expand(servicioid).toString();
			String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			servicio = apiRestMapper.mapOne(response, ServicioDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return servicio;
	}

	@Override
	public void createServicio(ServicioDto serv) {
		restService.POST("/api/v1/servicios", serv, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void updateServicio(ServicioDto serv) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/servicios", params, serv, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void deleteServicio(String cod_carga, String email_cliente, String nomb_comp, String servicioId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/servicios/{cod_carga}/{email_cliente}/{nomb_comp}/{servicioId}");
	    String uri = template.expand(cod_carga,email_cliente,nomb_comp, servicioId).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public List<ServicioDto> getImportaciones() {
		 List<ServicioDto> servicios = new ArrayList<ServicioDto>();
			
			try {
		    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			    ApiRestMapper<ServicioDto> apiRestMapper = new ApiRestMapper<>();
			    String response = (String)restService.GET("/api/v1/servicios/importaciones", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			    servicios = apiRestMapper.mapList(response, ServicioDto.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return servicios;
	}

	@Override
	public List<ServicioDto> getExportaciones() {
		List<ServicioDto> servicios = new ArrayList<ServicioDto>();
		
		try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<ServicioDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/servicios/exportaciones", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		    servicios = apiRestMapper.mapList(response, ServicioDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return servicios;
	}

	

}
