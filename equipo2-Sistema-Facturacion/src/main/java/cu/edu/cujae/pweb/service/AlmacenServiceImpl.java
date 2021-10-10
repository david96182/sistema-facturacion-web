package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.AlmacenDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;



@Service
public class AlmacenServiceImpl implements AlmacenService{
	
	@Autowired
	private RestService restService;

	@Override
	public List<AlmacenDto> getAlmacenes() {
		List<AlmacenDto> almacenes = new ArrayList<AlmacenDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<AlmacenDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/almacenes", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    almacenes = apiRestMapper.mapList(response, AlmacenDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return almacenes;
	}

	@Override
	public AlmacenDto getAlmacenById(String cod_almacen) {
		AlmacenDto almacen = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<AlmacenDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/almacenes/{idalmacen}");
		    String uri = template.expand(cod_almacen).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    almacen = apiRestMapper.mapOne(response, AlmacenDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return almacen;
	}

	
	@Override
	public void createAlmacen(AlmacenDto almacen) {
		restService.POST("/api/v1/almacenes", almacen, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void updateAlmacen(AlmacenDto almacen) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/almacenes", params, almacen, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void deleteAlmacen(String cod_almacen, String cod_empresa) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/almacenes/{idalmacen}/{cod_empresa}");
	    String uri = template.expand(cod_almacen, cod_empresa).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	
}
