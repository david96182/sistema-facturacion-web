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

import cu.edu.cujae.backend.core.dto.ServicioDto;
import cu.edu.cujae.backend.core.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createServicio(ServicioDto servicio) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"servicio_insert\"(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, servicio.getIdCarga());
			cs.setString(2, servicio.getEmail_cliente());
			cs.setString(3, servicio.getTipo_movimiento());
			cs.setDouble(4, servicio.getCombustible_requerido());
			cs.setDouble(5, servicio.getImporte_total());
			cs.setString(6, servicio.getCod_empresa());
			cs.setString(7, servicio.getNomb_comp());
			cs.setDouble(8, servicio.getTarifa_aforo());
			cs.setString(9, servicio.getCod_servicio());
			cs.executeUpdate();
		}
	}

	@Override
	public void updateServicio(ServicioDto servicio) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"servicio_update\"(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, servicio.getIdCarga());
			cs.setString(2, servicio.getEmail_cliente());
			cs.setString(3, servicio.getTipo_movimiento());
			cs.setDouble(4, servicio.getCombustible_requerido());
			cs.setDouble(5, servicio.getImporte_total());
			cs.setString(6, servicio.getCod_empresa());
			cs.setString(7, servicio.getNomb_comp());
			cs.setDouble(8, servicio.getTarifa_aforo());
			cs.setString(9, servicio.getCod_servicio());
			cs.executeUpdate();
		}
	}

	@Override
	public List<ServicioDto> listServicios() throws SQLException {
		List<ServicioDto> servicios = new ArrayList<ServicioDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Servicio\"");
			while(result.next())
			{
				servicios.add(new ServicioDto(
						result.getString("cod_carga"),
						result.getString("email_cliente"),
						result.getString("tipo_movimiento"),
						result.getDouble("combustible_requerido"),
						result.getDouble("importe_total"),
						result.getString("cod_emp"),
						result.getString("nomb_comp"),
						result.getDouble("tarifa_aforo"),
						result.getString("cod_servicio")));
			}
		}
		return servicios;
	}

	@Override
	public ServicioDto getServicioById(String servicioId) throws SQLException {
		ServicioDto servicio = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM \"Servicio\" WHERE \"cod_servicio\" = ?");
			pstmt.setString(1, servicioId);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				servicio = new ServicioDto(
						rs.getString("cod_carga"),
						rs.getString("email_cliente"),
						rs.getString("tipo_movimiento"),
						rs.getDouble("combustible_requerido"),
						rs.getDouble("importe_total"),
						rs.getString("cod_emp"),
						rs.getString("nomb_comp"),
						rs.getDouble("tarifa_aforo"),
						rs.getString("cod_servicio"));
			}
		}
		return servicio;
	}

	@Override
	public void deleteServicio(String cod_carga,String email_cliente,String nomb_comp,String servicioId) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement CS = conn.prepareCall(
					"{call servicio_delete(?,?,?,?)}");

			CS.setString(1, cod_carga);
			CS.setString(2, email_cliente);
			CS.setString(3, nomb_comp);
			CS.setString(4, servicioId);
			CS.executeUpdate();	
		}

	}

	@Override
	public List<ServicioDto> getImportaciones() throws SQLException {
		List<ServicioDto> servicios = new ArrayList<ServicioDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM \"Servicio\"\r\n"
					+ "WHERE tipo_movimiento = ?");
			pstmt.setString(1, "importacion");
			ResultSet result = pstmt.executeQuery();
			
			while(result.next())
			{
				servicios.add(new ServicioDto(
						result.getString("cod_carga"),
						result.getString("email_cliente"),
						result.getString("tipo_movimiento"),
						result.getDouble("combustible_requerido"),
						result.getDouble("importe_total"),
						result.getString("cod_emp"),
						result.getString("nomb_comp"),
						result.getDouble("tarifa_aforo"),
						result.getString("cod_servicio")));
			}
		}
		
		return servicios;
	}

	@Override
	public List<ServicioDto> getExportaciones() throws SQLException {
		List<ServicioDto> servicios = new ArrayList<ServicioDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM \"Servicio\"\r\n"
					+ "WHERE tipo_movimiento = ?");
			pstmt.setString(1, "exportacion");
			ResultSet result = pstmt.executeQuery();
			
			while(result.next())
			{
				servicios.add(new ServicioDto(
						result.getString("cod_carga"),
						result.getString("email_cliente"),
						result.getString("tipo_movimiento"),
						result.getDouble("combustible_requerido"),
						result.getDouble("importe_total"),
						result.getString("cod_emp"),
						result.getString("nomb_comp"),
						result.getDouble("tarifa_aforo"),
						result.getString("cod_servicio")));
			}
		}
		
		return servicios;
	}

}
