package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.EstanteDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class EstanteServiceImpl implements EstanteService{
	
	@Autowired
	private RestService restService;

	@Override
	public List<EstanteDto> getEstantes() {
		List<EstanteDto> estantesList = new ArrayList<EstanteDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<EstanteDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/estantes", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    estantesList = apiRestMapper.mapList(response, EstanteDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return estantesList;
	}

	@Override
	public EstanteDto getEstanteById(String cod_estante) {
		EstanteDto estante = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<EstanteDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/estantes/{cod_estante}");
		    String uri = template.expand(cod_estante).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    estante = apiRestMapper.mapOne(response, EstanteDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return estante;
	}

	@Override
	public void createEstante(EstanteDto estante) {
		restService.POST("/api/v1/estantes", estante, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void updateEstante(EstanteDto estante) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/estantes", params, estante, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void deleteEstante(String cod_almacen, String cod_estante, String cod_emp ) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/estantes/{cod_almacen}/{idestante}/{cod_empresa}");
	    String uri = template.expand(cod_almacen, cod_estante, cod_emp).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public List<EstanteDto> getEstantesByAlmacenId(String idalmacen) {
		List<EstanteDto> estantes = new ArrayList<EstanteDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<EstanteDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/estantes/almacenes/{idalmacen}");
		    String uri = template.expand(idalmacen).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    estantes = apiRestMapper.mapList(response, EstanteDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return estantes;
	}
}
