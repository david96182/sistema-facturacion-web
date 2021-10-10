package cu.edu.cujae.backend.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.CaracteristicasDto;
import cu.edu.cujae.backend.core.service.CaracteristicasService;

@Service
public class CaracteristicasServiceImpl implements CaracteristicasService{

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CaracteristicasDto> getAlternativasByCompany(String company) throws SQLException {
		List<CaracteristicasDto> alternList = new ArrayList<CaracteristicasDto>();
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT codigo_alternativa, nomb_alternativa, description "
				      + "FROM \"Alternativas_Prioridad\" inner join \"Company_Alternativas\" on \"Company_Alternativas\".alt_id = \"Alternativas_Prioridad\".codigo_alternativa"
				      + " where \"Company_Alternativas\".comp_id = ?");
	
			pstmt.setString(1, company);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				alternList.add(new CaracteristicasDto(rs.getLong("codigo_alternativa")
						,rs.getString("nomb_alternativa")
						,rs.getString("description")));
			}
		}
		return alternList;
	}


	@Override
	public CaracteristicasDto getAlternativasById(Long roleId) throws SQLException {
		CaracteristicasDto alternativa = null; 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT * FROM \"Alternativas_Prioridad\" where codigo_alternativa = ?");
	
			pstmt.setLong(1, roleId);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				alternativa = new CaracteristicasDto(rs.getLong("codigo_alternativa")
						,rs.getString("nomb_alternativa")
						,rs.getString("description"));
			}
		}
		
		return alternativa;
	}

	@Override
	public List<CaracteristicasDto> getAlternativas() throws SQLException{
		
		List<CaracteristicasDto> alternList = new ArrayList<CaracteristicasDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM \"Alternativas_Prioridad\"");
			
			while(rs.next()){
				alternList.add(new CaracteristicasDto(rs.getLong("codigo_alternativa")
						,rs.getString("nomb_alternativa")
						,rs.getString("description")));
			}
		} 
		return alternList;
	}

	@Override
	public List<CaracteristicasDto> getCriterios() throws SQLException{
		
		List<CaracteristicasDto> criteriosList = new ArrayList<CaracteristicasDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM \"Criterio_Acondicionamiento\"");
			
			while(rs.next()){
				criteriosList.add(new CaracteristicasDto(rs.getLong("codigo_criterio")
						,rs.getString("nomb_criterio")
						,rs.getString("description")));
			}
		} 
		return criteriosList;
	}

	@Override
	public List<CaracteristicasDto> getNiveles() throws SQLException{

		List<CaracteristicasDto> nivelesList = new ArrayList<CaracteristicasDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM \"Niveles_Seguridad\"");
			
			while(rs.next()){
				nivelesList.add(new CaracteristicasDto(rs.getLong("cod_nivel")
						,rs.getString("nomb_nivel")
						,rs.getString("description")));
			}
		} 
		return nivelesList;
	}

	@Override
	public List<CaracteristicasDto> getTipo() throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CaracteristicasDto> getNivelesByCompany(String company) throws SQLException{
		
		List<CaracteristicasDto> nivelesList = new ArrayList<CaracteristicasDto>();
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT cod_nivel, nomb_nivel, description "
				      + "FROM \"Niveles_Seguridad\" inner join \"Company_Niveles\" on \"Company_Niveles\".niv_id = \"Niveles_Seguridad\".cod_nivel"
				      + " where \"Company_Niveles\".comp_id = ?");
	
			pstmt.setString(1, company);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				nivelesList.add(new CaracteristicasDto(rs.getLong("cod_nivel")
						,rs.getString("nomb_nivel")
						,rs.getString("description")));
			}
		}
		return nivelesList;
	}


	@Override
	public CaracteristicasDto getNivelesById(Long roleId) throws SQLException{

		CaracteristicasDto nivel = null; 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT * FROM \"Niveles_Seguridad\" where cod_nivel = ?");
	
			pstmt.setLong(1, roleId);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				nivel = new CaracteristicasDto(rs.getLong("cod_nivel")
						,rs.getString("nomb_nivel")
						,rs.getString("description"));
			}
		}
		
		return nivel;
	}


	@Override
	public List<CaracteristicasDto> getCriteriosByCompany(String company) throws SQLException {

		List<CaracteristicasDto> criteriosList = new ArrayList<CaracteristicasDto>();
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT codigo_criterio, nomb_criterio, description "
			 	      + "FROM \"Criterio_Acondicionamiento\" inner join \"Company_Criterios\" on \"Company_Criterios\".crit_id = \"Criterio_Acondicionamiento\".codigo_criterio"
			 	      + " where \"Company_Criterios\".comp_id = ?");
	
			pstmt.setString(1, company);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				criteriosList.add(new CaracteristicasDto(rs.getLong("codigo_criterio")
						,rs.getString("nomb_criterio")
						,rs.getString("description")));
			}
		}
		return criteriosList;
	}


	@Override
	public CaracteristicasDto getCriteriosById(Long roleId) throws SQLException {
		
		CaracteristicasDto criterio = null; 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT * FROM \"Criterio_Acondicionamiento\" where codigo_criterio = ?");
	
			pstmt.setLong(1, roleId);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				criterio = new CaracteristicasDto(rs.getLong("codigo_criterio")
						,rs.getString("nomb_criterio")
						,rs.getString("description"));
			}
		}
		
		return criterio;
	}
}
