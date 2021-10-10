package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.CompanyDto;
import cu.edu.cujae.backend.core.service.CompanyService;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping("")
	public ResponseEntity<List<CompanyDto>> getCompanys() throws SQLException
	{
		List<CompanyDto> company = companyService.listCompany();
		return ResponseEntity.ok(company);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable String id) throws SQLException
	{
		CompanyDto company = companyService.getCompanyById(id);
		return ResponseEntity.ok(company);
	}
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody CompanyDto company) throws SQLException {
		companyService.company_create(company);
        return ResponseEntity.ok("Company Created");
    }
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody CompanyDto company) throws SQLException {
		companyService.company_update(company);
        return ResponseEntity.ok("Company Updated");
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
		companyService.company_delete(id);
        return ResponseEntity.ok("Company deleted");
    } 
}
