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

import cu.edu.cujae.backend.core.dto.CasillaDto;
import cu.edu.cujae.backend.core.service.CasillaService;


@Service
public class CasillaServiceImpl implements CasillaService{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public void createCasilla(CasillaDto casilla) throws SQLException {
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {	
		CallableStatement cs = conn.prepareCall("{call \"casilla_insert\"(?,?,?,?,?,?,?,?,?,?,?)}");
		cs.setString(1, casilla.getIdAlmacen());
		cs.setString(2, casilla.getIdCasilla()); 
		cs.setString(3, casilla.getCod_empresa());
		cs.setString(4, casilla.getCod_estante());
		cs.setString(5, casilla.getIdPiso());
		cs.setBoolean(6, casilla.isMantActivo());
		cs.setDate(7, casilla.getFecha_final_mant());
		cs.setDate(8, casilla.getFecha_inicio_mant());
		cs.setBoolean(9, casilla.isDisponibilidad());
		cs.setString(10, casilla.getIdCarga());
		cs.setString(11, casilla.getCod_paquete());	
		cs.executeUpdate();
		}
	}

	@Override
	public void updateCasilla(CasillaDto casilla) throws SQLException { 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		CallableStatement cs = conn.prepareCall("{call \"casilla_update\"(?,?,?,?,?,?,?,?,?,?,?)}");
		cs.setString(1, casilla.getIdAlmacen());
		cs.setString(2, casilla.getIdCasilla()); 
		cs.setString(3, casilla.getCod_empresa());
		cs.setString(4, casilla.getCod_estante());
		cs.setString(5, casilla.getIdPiso());
		cs.setBoolean(6, casilla.isMantActivo());
		cs.setDate(7, casilla.getFecha_final_mant());
		cs.setDate(8, casilla.getFecha_inicio_mant());
		cs.setBoolean(9, casilla.isDisponibilidad());
		cs.setString(10, casilla.getIdCarga());
		cs.setString(11, casilla.getCod_paquete());	
		cs.executeUpdate();
		}	
	}

	@Override
	public List<CasillaDto> listCasillas() throws SQLException {

		List<CasillaDto> casillas = new ArrayList<CasillaDto>();
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Casilla\"");
			while(result.next())
			{
				casillas.add(new CasillaDto(
						result.getString("cod_almacen"),
						result.getString("cod_casilla"),
						result.getString("cod_emp"),
						result.getString("cod_estante"),
						result.getString("num_piso"),
						result.getBoolean("mant_activo"),
						result.getDate("fecha_mant_f"),
						result.getDate("fecha_mant_i"),
						result.getBoolean("disponibilidad"),
						result.getString("cod_carga"),
						result.getString("cod_paquete")));
			}
		}	
			return casillas;
			
	}

	@Override
	public CasillaDto getCasillaById(String idcasilla) throws SQLException {
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"Casilla\" WHERE \"cod_casilla\" = ?");
		statement.setString(1, idcasilla);
		ResultSet resultSet = statement.executeQuery();
		return resultSet.next() ? new CasillaDto(
				resultSet.getString("cod_almacen"),
				resultSet.getString("cod_casilla"),
				resultSet.getString("cod_emp"),
				resultSet.getString("cod_estante"),
				resultSet.getString("num_piso"),
				resultSet.getBoolean("mant_activo"),
				resultSet.getDate("fecha_mant_f"),
				resultSet.getDate("fecha_mant_i"),
				resultSet.getBoolean("disponibilidad"),
				resultSet.getString("cod_carga"),
				resultSet.getString("cod_paquete"))
		: null;
		}
	}

	@Override
	public void deleteCasilla(String cod_almacen, String idcasilla, String cod_emp, String cod_estante, String num_piso)
			throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		CallableStatement cs = conn.prepareCall("{call \"casilla_delete\"(?,?,?,?,?)}");
		cs.setString(1, cod_almacen);
		cs.setString(2, idcasilla);
		cs.setString(3, cod_emp);
		cs.setString(4, cod_estante);
		cs.setString(5, num_piso);
		cs.executeUpdate();
		}	
	}

	@Override
	public List<CasillaDto> getCasillasByPisoId(String num_piso) throws SQLException {
		List<CasillaDto> casillas = new ArrayList<CasillaDto>();
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT cod_almacen, cod_casilla, cod_emp, cod_estante, num_piso, mant_activo, fecha_mant_f, fecha_mant_i, disponibilidad, cod_carga, cod_paquete FROM \"Casilla\" where \"Casilla\".num_piso = ?");
			 
				pstmt.setString(1, num_piso);
				ResultSet result = pstmt.executeQuery();
			while(result.next())
			{
				casillas.add(new CasillaDto(
						result.getString("cod_almacen"),
						result.getString("cod_casilla"),
						result.getString("cod_emp"),
						result.getString("cod_estante"),
						result.getString("num_piso"),
						result.getBoolean("mant_activo"),
						result.getDate("fecha_mant_f"),
						result.getDate("fecha_mant_i"),
						result.getBoolean("disponibilidad"),
						result.getString("cod_carga"),
						result.getString("cod_paquete")));
			}
		}	
			return casillas;
	}

}
