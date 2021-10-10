package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.CompanyDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private RestService restService;

	@Override
	public List<CompanyDto> getCompanys() {
		
		List<CompanyDto> companyList = new ArrayList<CompanyDto>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CompanyDto> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/company", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    companyList = apiRestMapper.mapList(response, CompanyDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return companyList;
	}

	@Override
	public CompanyDto getCompanyByCod(String cod) {
		
		CompanyDto company = null;
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<CompanyDto> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/company/{id}");
		    String uri = template.expand(cod).toString();
		    String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		    company = apiRestMapper.mapOne(response, CompanyDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return company;
	}

	@Override
	public void createCompany(CompanyDto comp) {
		// TODO Auto-generated method stub
		restService.POST("/api/v1/company", comp, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void updateCompany(CompanyDto comp) {
		// TODO Auto-generated method stub
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/company", params, comp, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deleteCompany(String id) {
		// TODO Auto-generated method stub
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/company/{id}");
	    String uri = template.expand(id).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}
}
