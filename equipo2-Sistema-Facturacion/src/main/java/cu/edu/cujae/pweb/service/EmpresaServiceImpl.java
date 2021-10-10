package cu.edu.cujae.pweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.ClienteDto;
import cu.edu.cujae.pweb.dto.EmpresaDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private RestService restService;
	
	@Override
	public EmpresaDto getEmpresa() {
		EmpresaDto emp = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<EmpresaDto> apiRestMapper = new ApiRestMapper<>();

			String response = (String)restService.GET("/api/v1/empresa", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			emp = apiRestMapper.mapOne(response, EmpresaDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return emp;
	}

}
