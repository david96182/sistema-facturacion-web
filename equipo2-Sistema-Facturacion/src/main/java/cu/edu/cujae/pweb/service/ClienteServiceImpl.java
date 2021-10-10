package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.ClienteDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;


@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private RestService restService;

	@Override
	public List<ClienteDto> getClientes() {
		List<ClienteDto> clientes = new ArrayList<ClienteDto>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<ClienteDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/api/v1/clientes", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			clientes = apiRestMapper.mapList(response, ClienteDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public ClienteDto getClienteById(String id) {
		ClienteDto cliente = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<ClienteDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/clientes/{id}");
			String uri = template.expand(id).toString();
			String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			cliente = apiRestMapper.mapOne(response, ClienteDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cliente;
	}

	@Override
	public void createCliente(ClienteDto user) {
		restService.POST("/api/v1/clientes", user, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void updateCliente(ClienteDto user) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/clientes", params, user, String.class, CurrentUserUtils.getTokenBearer()).getBody();

	}

	@Override
	public void deleteCliente(String id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/clientes/{id}");
	    String uri = template.expand(id).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

}
