package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.CompanyDto;


public interface CompanyService {

	void company_create(CompanyDto company)throws SQLException;
	
	void company_update(CompanyDto company) throws SQLException;
	
	void company_delete(String id)throws SQLException;
	
	List<CompanyDto> listCompany() throws SQLException;
	
	CompanyDto getCompanyById(String id) throws SQLException;
}
