package cu.edu.cujae.backend.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.CompanyDto;
import cu.edu.cujae.backend.core.service.CaracteristicasService;
import cu.edu.cujae.backend.core.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CaracteristicasService caractService;

	@Override
	public void company_create(CompanyDto company) throws SQLException {
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement CS = conn.prepareCall("{call company_create(?, ?, ?, ?, ?, ?, ?)}");
		
			CS.setString(1, company.getNameCompany());
			CS.setString(2, company.getTipo());
			CS.setString(3, company.getId());
			
			
			//roles separados por coma, ej: "1, 2, 4"
			String criterios = company.getCriterios().stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
			CS.setString(4, criterios);
			
			String niveles = company.getNiveles().stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
			CS.setString(5, niveles);
			
			String alternativas = company.getAlternativa().stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
			CS.setString(6, alternativas);
			CS.setBoolean(7, false);
			
			CS.executeUpdate();
		}
	}

	@Override
	public void company_update(CompanyDto company) throws SQLException {
		
		CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
				"{call update_company(?, ?, ?, ?, ?, ?, ?)}");
		
		CS.setString(1, company.getNameCompany());
		CS.setString(2, company.getTipo());
		CS.setString(3, company.getId());
		
		//roles separados por coma, ej: "1, 2, 4"
		String criterios = company.getCriterios().stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
		CS.setString(4, criterios);
		
		String niveles = company.getNiveles().stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
		CS.setString(5, niveles);
		
		String alternativas = company.getAlternativa().stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
		CS.setString(6, alternativas);
		CS.setBoolean(7, false);
		
		CS.executeUpdate();
	}

	@Override
	public void company_delete(String id) throws SQLException {
		
		CallableStatement CS = jdbcTemplate.getDataSource().getConnection().prepareCall(
				"{call company_delete(?)}");
		
		CS.setString(1, id);
		CS.executeUpdate();
	}

	@Override
	public List<CompanyDto> listCompany() throws SQLException {
		
		List<CompanyDto> companyList = new ArrayList<CompanyDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM \"Company\"");
			
			while(rs.next()){
				companyList.add(new CompanyDto(rs.getString("nomb_comp")
						,rs.getString("tipo_comp")
						,rs.getString("cod_emp")
						,caractService.getAlternativasByCompany(rs.getString("nomb_comp"))
						,caractService.getNivelesByCompany(rs.getString("nomb_comp"))
						,caractService.getCriteriosByCompany(rs.getString("nomb_comp"))
						, false));
			}
		}
		return companyList;
	}

	@Override
	public CompanyDto getCompanyById(String id) throws SQLException {
		
		CompanyDto company = null; 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT * FROM \"Company\" where nomb_comp = ?");
	
			pstmt.setString(1, id);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				company = new CompanyDto(rs.getString("nomb_comp")
						,rs.getString("tipo_comp")
						,rs.getString("cod_emp")
						,caractService.getCriteriosByCompany(rs.getString("nomb_comp"))
						,caractService.getNivelesByCompany(rs.getString("nomb_comp"))
						,caractService.getAlternativasByCompany(rs.getString("nomb_comp"))
						, false);
			}
		}
		return company;
	}
}
