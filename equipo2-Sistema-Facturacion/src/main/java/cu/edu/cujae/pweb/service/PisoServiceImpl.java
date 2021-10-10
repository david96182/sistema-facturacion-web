package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.PisoDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class PisoServiceImpl implements PisoService{
	
	@Autowired
	private RestService restService;

	@Override
	public List<PisoDto> getPisos() {
		List<PisoDto> pisoList = new ArrayList<PisoDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<PisoDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/pisos", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    pisoList = apiRestMapper.mapList(response, PisoDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pisoList;
	}

	@Override
	public PisoDto getPisoById(String numPiso) {
		PisoDto piso = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<PisoDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/pisos/{numpiso}");
		    String uri = template.expand(numPiso).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    piso = apiRestMapper.mapOne(response, PisoDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return piso;
	}

	@Override
	public void createPiso(PisoDto piso) {
		restService.POST("/api/v1/pisos", piso, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void updatePiso(PisoDto piso) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/pisos", params, piso, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deletePiso(String cod_almacen, String cod_emp, String cod_estante, String numpiso) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/pisos/{cod_almacen}/{cod_empresa}/{cod_estante}/{/numpiso}");
	    String uri = template.expand(cod_almacen, cod_emp, cod_estante, numpiso).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public List<PisoDto> getPisosByEstanteId(String cod_estante) {
		List<PisoDto> pisos = new ArrayList<PisoDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<PisoDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/pisos/estantes/{cod_estante}");
		    String uri = template.expand(cod_estante).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    pisos = apiRestMapper.mapList(response, PisoDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pisos;
	}
}
