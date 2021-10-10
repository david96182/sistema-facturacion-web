package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.CaracteristicasDto;
import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class CaracteristicasServiceImpl implements CaracteristicasService{

	@Autowired
	private RestService restService;
	
	@Override
	public List<CaracteristicasDto> getAlternativas() {
		
		List<CaracteristicasDto> alternList = new ArrayList<CaracteristicasDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/caracteristicas/alternativas", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    alternList = apiRestMapper.mapList(response, CaracteristicasDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return alternList;
	}
	
	@Override
	public List<CaracteristicasDto> getCriterios() {
		
		List<CaracteristicasDto> criteriosList = new ArrayList<CaracteristicasDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/caracteristicas/criterios", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    criteriosList = apiRestMapper.mapList(response, CaracteristicasDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return criteriosList;
	}
	
	@Override
	public List<CaracteristicasDto> getNiveles() {
		
		List<CaracteristicasDto> nivelesList = new ArrayList<CaracteristicasDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/caracteristicas/niveles", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    nivelesList = apiRestMapper.mapList(response, CaracteristicasDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nivelesList;
	}
	/**
	@Override
	public List<CaracteristicasDto> getTipo() {
		
		List<CaracteristicasDto> tipoList = new ArrayList<CaracteristicasDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/caracteristicas", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    tipoList = apiRestMapper.mapList(response, CaracteristicasDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tipoList;
	}
	*/
	@Override
	public List<RoleDto> getRoles() {
		
		List<RoleDto> roleList = new ArrayList<RoleDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/roles", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    roleList = apiRestMapper.mapList(response, RoleDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roleList;
	}
	
	@Override
	public List<RoleDto> getRolesByUser(Long userId) {
		List<RoleDto> roleList = new ArrayList<RoleDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/roles/users/{userId}");
		    String uri = template.expand(userId).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    roleList = apiRestMapper.mapList(response, RoleDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roleList;
	}
	/*
	@Override
	public List<RoleDto> getRolesByName(String name) {
		return getRoles().stream().filter(r -> r.getRoleName().equals(name)).collect(Collectors.toList());
	}
	*/
	@Override
	public RoleDto getRolesById(Long roleId) {

		RoleDto role = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/roles/{id}");
		    String uri = template.expand(roleId).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    role = apiRestMapper.mapOne(response, RoleDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return role;
	}
	
	@Override
	public List<CaracteristicasDto> getAlternativaByCompany(String compId) {
		List<CaracteristicasDto> caractList = new ArrayList<CaracteristicasDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/caracteristicas/company/alternativa/{id}");
		    String uri = template.expand(compId).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    caractList = apiRestMapper.mapList(response, CaracteristicasDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caractList;
	}
	/*
	@Override
	public List<CaracteristicasDto> getCaracteristicasByName(String name) {
		return getAlternativas().stream().filter(r -> r.getRoleName().equals(name)).collect(Collectors.toList());
	}
	*/
	@Override
	public CaracteristicasDto getAlternativaById(Long roleId) {
		
		CaracteristicasDto caract = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/caracteristicas/alternativa/{id}");
		    String uri = template.expand(roleId).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    caract = apiRestMapper.mapOne(response, CaracteristicasDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return caract;
	}

	@Override
	public List<CaracteristicasDto> getNivelByCompany(String compId) {
		
		List<CaracteristicasDto> caractList = new ArrayList<CaracteristicasDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/caracteristicas/company/nivel/{id}");
		    String uri = template.expand(compId).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    caractList = apiRestMapper.mapList(response, CaracteristicasDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caractList;
	}

	@Override
	public List<CaracteristicasDto> getCriterioByCompany(String compId) {
		
		List<CaracteristicasDto> caractList = new ArrayList<CaracteristicasDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/caracteristicas/company/criterio/{id}");
		    String uri = template.expand(compId).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    caractList = apiRestMapper.mapList(response, CaracteristicasDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caractList;
	}

	@Override
	public CaracteristicasDto getNivelById(Long roleId) {
		
		CaracteristicasDto caract = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/caracteristicas/nivel/{id}");
		    String uri = template.expand(roleId).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    caract = apiRestMapper.mapOne(response, CaracteristicasDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return caract;
	}

	@Override
	public CaracteristicasDto getCriterioById(Long roleId) {

		CaracteristicasDto caract = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CaracteristicasDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/caracteristicas/criterio/{id}");
		    String uri = template.expand(roleId).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    caract = apiRestMapper.mapOne(response, CaracteristicasDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return caract;
	}

}
