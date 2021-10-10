package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.CasillaDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class CasillaServiceImpl implements CasillaService{
	
	@Autowired
	private RestService restService;

	@Override
	public List<CasillaDto> getCasillas() {
		List<CasillaDto> casillas = new ArrayList<CasillaDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CasillaDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/casillas", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    casillas = apiRestMapper.mapList(response, CasillaDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return casillas;
	}

	@Override
	public CasillaDto getCasillaById(String idCasilla) {
		CasillaDto casilla = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CasillaDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/casillas/{idcasilla}");
		    String uri = template.expand(idCasilla).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    casilla = apiRestMapper.mapOne(response, CasillaDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return casilla;
	}

	@Override
	public void createCasilla(CasillaDto casilla) {
		restService.POST("/api/v1/casillas", casilla, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void updateCasilla(CasillaDto casilla) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/casillas", params, casilla, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deleteCasilla(String cod_almacen, String idcasilla, String cod_emp, String cod_estante, 
			String num_piso) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/casillas/{cod_almacen}/{idcasilla}/{cod_empresa}/{cod_estante}/{numpiso}");
	    String uri = template.expand(cod_almacen, idcasilla, cod_emp, cod_estante, num_piso).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public List<CasillaDto> getCasillasByPisoId(String numpiso) {
		List<CasillaDto> casillas = new ArrayList<CasillaDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CasillaDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/casillas/pisos/{num_piso}");
		    String uri = template.expand(numpiso).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    casillas = apiRestMapper.mapList(response, CasillaDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return casillas;
	}

}
