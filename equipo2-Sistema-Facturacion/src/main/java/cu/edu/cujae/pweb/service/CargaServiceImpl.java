package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.CargaDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class CargaServiceImpl implements CargaService{

	@Autowired
	private RestService restService;
	
	@Override
	public List<CargaDto> getCargas() {
		List<CargaDto> cargas = new ArrayList<CargaDto>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<CargaDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/api/v1/cargas", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			cargas = apiRestMapper.mapList(response, CargaDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cargas;
	}

	@Override
	public CargaDto getCargaById(String cargaId) {
		CargaDto carga = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<CargaDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/cargas/{cargaId}");
			String uri = template.expand(cargaId).toString();
			String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			carga = apiRestMapper.mapOne(response, CargaDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return carga;
	}

	@Override
	public void createCarga(CargaDto carga) {
		restService.POST("/api/v1/cargas", carga, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}

	@Override
	public void updateCarga(CargaDto carga) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/cargas", params, carga, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deleteCarga(String id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/cargas/{id}");
	    String uri = template.expand(id).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public List<CargaDto> getcargaByClient(String email_cliente) {
		List<CargaDto> cargas = new ArrayList<CargaDto>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<CargaDto> apiRestMapper = new ApiRestMapper<>();
			UriTemplate template = new UriTemplate("/api/v1/cargas/client/{email_cliente}");
			String uri = template.expand(email_cliente).toString();
			String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			cargas = apiRestMapper.mapList(response, CargaDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cargas;
	}

}
