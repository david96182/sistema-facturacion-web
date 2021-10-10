package cu.edu.cujae.backend.service;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.EstanteDto;
import cu.edu.cujae.backend.core.service.EstanteService;

@Service
public class EstanteServiceImpl implements EstanteService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createEstante(EstanteDto estante) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		CallableStatement cs = conn.prepareCall("{call \"estante_insert\"(?,?,?,?,?,?)}");
		cs.setString(1,estante.getCod_almacen());
		cs.setString(2, estante.getCod_estante());
		cs.setString(3, estante.getCod_emp());
		cs.setBoolean(4, estante.isMantActivo());
		cs.setDate(5, estante.getFecha_inicio_mant());
		cs.setDate(6, estante.getFecha_final_mant());
		cs.executeUpdate();
	}	
	}

	@Override
	public void updateEstante(EstanteDto estante) throws SQLException { 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		CallableStatement cs = conn.prepareCall("{call \"estante_update\"(?,?,?,?,?,?)}");
		cs.setString(1,estante.getCod_almacen());
		cs.setString(2, estante.getCod_estante());
		cs.setString(3, estante.getCod_emp());
		cs.setBoolean(4, estante.isMantActivo());
		cs.setDate(5, estante.getFecha_inicio_mant());
		cs.setDate(6, estante.getFecha_final_mant());
		cs.executeUpdate();
		}
		
	}

	@Override
	public List<EstanteDto> listEstantes() throws SQLException {

		List<EstanteDto> estantes = new ArrayList<EstanteDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Estante\"");
		while(result.next())
		{
			estantes.add(new EstanteDto(
					result.getString("cod_almacen"),
					result.getString("cod_estante"),
					result.getString("cod_emp"),
					result.getBoolean("mant_activo"),
					result.getDate("fecha_mant_i"),
					result.getDate("fecha_mant_f")));
		}
		}
		return estantes;
	}

	@Override
	public EstanteDto getEstanteById(String cod_estante) throws SQLException {
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"Estante\" WHERE \"cod_estante\" = ?");
		statement.setString(1, cod_estante);
		ResultSet resultSet = statement.executeQuery();
		return resultSet.next() ? new EstanteDto(
				resultSet.getString("cod_almacen"),
				resultSet.getString("cod_estante"),
				resultSet.getString("cod_emp"),
				resultSet.getBoolean("mant_activo"),
				resultSet.getDate("fecha_mant_i"),
				resultSet.getDate("fecha_mant_f"))
		:null;
		}
	}

	
	@Override
	public void deleteEstante(String cod_almacen, String cod_estante , String cod_emp) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		CallableStatement cs = conn.prepareCall("{call \"estante_delete\"(?,?,?)}");
		cs.setString(1, cod_almacen);
		cs.setString(2, cod_estante);
		cs.setString(3, cod_emp);
		cs.executeUpdate();
	}		
	}

	@Override
	public List<EstanteDto> getEstantesByAlmacenId(String cod_almacen) throws SQLException {
		List<EstanteDto> estantes = new ArrayList<EstanteDto>();
		/*
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Estante\" WHERE \"cod_almacen\" = ?");
			while(result.next())
			{
				estantes.add(new EstanteDto(
						result.getString("cod_almacen"),
						result.getString("cod_estante"),
						result.getString("cod_emp"),
						result.getBoolean("mant_activo"),
						result.getDate("fecha_mant_i"),
						result.getDate("fecha_mant_f")));
			}
			}
		return estantes;*/
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
		 "SELECT cod_almacen, cod_estante, cod_emp, mant_activo, fecha_mant_i, fecha_mant_f FROM \"Estante\" where \"Estante\".cod_almacen = ?");
	
			pstmt.setString(1, cod_almacen);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				estantes.add(new EstanteDto(rs.getString("cod_almacen")
						,rs.getString("cod_estante")
						,rs.getString("cod_emp")
						,rs.getBoolean("mant_activo")
						,rs.getDate("fecha_mant_i")
						,rs.getDate("fecha_mant_f")));
			}
		}
		return estantes;
	}
	
	

}
