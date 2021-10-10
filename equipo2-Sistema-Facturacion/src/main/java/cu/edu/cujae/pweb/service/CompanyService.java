package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.CompanyDto;

public interface CompanyService {
	
	List<CompanyDto> getCompanys();
	CompanyDto getCompanyByCod(String cod);
	void createCompany(CompanyDto comp);
	void updateCompany(CompanyDto comp);
	void deleteCompany(String cod);
}
