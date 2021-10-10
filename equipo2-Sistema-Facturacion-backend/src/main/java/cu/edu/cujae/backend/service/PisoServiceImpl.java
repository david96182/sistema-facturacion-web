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

import cu.edu.cujae.backend.core.dto.PisoDto;
import cu.edu.cujae.backend.core.service.PisoService;

@Service
public class PisoServiceImpl implements PisoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createPiso(PisoDto piso) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		CallableStatement cs = conn.prepareCall("{call \"piso_insert\"(?,?,?,?,?,?,?)}");
		cs.setString(1,piso.getCod_almacen());
		cs.setString(2, piso.getCod_emp());
		cs.setString(3, piso.getCod_estante());   
		cs.setString(4, piso.getNumPiso()); 
		cs.setBoolean(5, piso.isMantActivo());
		cs.setDate(6, piso.getFecha_inicio_mant());
		cs.setDate(7, piso.getFecha_final_mant());
		cs.executeUpdate();
		}
	}

	@Override
	public void updatePiso(PisoDto piso) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		CallableStatement cs = conn.prepareCall("{call \"piso_update\"(?,?,?,?,?,?,?)}");
		cs.setString(1,piso.getCod_almacen());
		cs.setString(2, piso.getCod_estante());
		cs.setString(3, piso.getCod_emp());
		cs.setString(4, piso.getNumPiso());
		cs.setBoolean(5, piso.isMantActivo());
		cs.setDate(6, piso.getFecha_inicio_mant());
		cs.setDate(7, piso.getFecha_final_mant());
		cs.executeUpdate();
		}
	}

	@Override
	public List<PisoDto> listPisos() throws SQLException {

		List<PisoDto> pisos = new ArrayList<PisoDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Piso\"");
		while(result.next())
		{
			pisos.add(new PisoDto(
					result.getString("cod_almacen"),
					result.getString("cod_estante"),
					result.getString("cod_emp"),
					result.getString("num_piso"),
					result.getBoolean("mant_activo"),
					result.getDate("fecha_mant_i"),
					result.getDate("fecha_mant_f")));
		}
		}
		return pisos;
		
	}

	@Override
	public PisoDto getPisoById(String numpiso) throws SQLException {
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"Piso\" WHERE \"num_piso\" = ?");
		statement.setString(1, numpiso);
		ResultSet resultSet = statement.executeQuery();
		return resultSet.next() ? new PisoDto(
				resultSet.getString("cod_almacen"),
				resultSet.getString("cod_estante"),
				resultSet.getString("cod_emp"),
				resultSet.getString("num_piso"),
				resultSet.getBoolean("mant_activo"),
				resultSet.getDate("fecha_mant_i"),
				resultSet.getDate("fecha_mant_f"))
		:null;
		}
	}

	
	@Override
	public void deletePiso(String cod_almacen, String cod_emp, String cod_estante, String numpiso) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
		CallableStatement cs = conn.prepareCall("{call \"piso_delete\"(?,?,?,?)}");
		cs.setString(1, cod_almacen);
		cs.setString(2, cod_emp);
		cs.setString(3, cod_estante);
		cs.setString(4, numpiso);
		cs.executeUpdate();
		}
	}

	@Override
	public List<PisoDto> getPisosByEstanteId(String cod_estante) throws SQLException {
		List<PisoDto> pisos = new ArrayList<PisoDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT cod_almacen, cod_emp, cod_estante, num_piso, mant_activo, fecha_mant_i, fecha_mant_f FROM \"Piso\" where \"Piso\".cod_estante = ?");
					
			        pstmt.setString(1, cod_estante);
					ResultSet result = pstmt.executeQuery();
		
		while(result.next())
		{
			pisos.add(new PisoDto(
					result.getString("cod_almacen"),
					result.getString("cod_emp"),
					result.getString("cod_estante"),
					result.getString("num_piso"),
					result.getBoolean("mant_activo"),
					result.getDate("fecha_mant_i"),
					result.getDate("fecha_mant_f")));
		}
		}
		return pisos;
	}


}
