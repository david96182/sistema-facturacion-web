package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.PaqueteDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class PaqueteServiceImpl implements PaqueteService{

	
	@Autowired
	private RestService restService;
	
	@Override
	public List<PaqueteDto> getPaquetes() {
		List<PaqueteDto> paquetes = new ArrayList<PaqueteDto>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<PaqueteDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/api/v1/paquetes", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			paquetes = apiRestMapper.mapList(response, PaqueteDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paquetes;
	}

	@Override
	public List<PaqueteDto> getPaquetesById(String cargaid) {
		List<PaqueteDto> paqs = new ArrayList<PaqueteDto>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<PaqueteDto> apiRestMapper = new ApiRestMapper<>();
			
			UriTemplate template = new UriTemplate("/api/v1/paquetes/{cargaid}");
			String uri = template.expand(cargaid).toString();
			String response = (String)restService.GET(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			paqs = apiRestMapper.mapList(response, PaqueteDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paqs;
	}

	@Override
	public void createPaquete(PaqueteDto paq) {
		restService.POST("/api/v1/paquetes", paq, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void updatePaquete(PaqueteDto paq) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/paquetes", params, paq, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void deletePaquete(String cargaId,String idpaq) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/paquetes/{cargaId}/{idpaq}");
	    String uri = template.expand(cargaId,idpaq).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

}
