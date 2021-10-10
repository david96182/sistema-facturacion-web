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

import cu.edu.cujae.backend.core.dto.CargaDto;
import cu.edu.cujae.backend.core.dto.PaqueteDto;
import cu.edu.cujae.backend.core.service.CargaService;
import cu.edu.cujae.backend.core.service.PaqueteService;

@Service
public class CargaServiceImpl implements CargaService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PaqueteService paqServ;

	@Override
	public void createCarga(CargaDto carga) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"carga_insert\"(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, carga.getIdCarga());
			cs.setString(2, carga.getTipoEmpaquetado());
			cs.setDate(3,  carga.getFechaIngreso());
			cs.setInt(4, carga.getCantPaquetes());
			cs.setString(5, carga.getNombCarga());
			cs.setString(6, carga.getTipoProducto());
			cs.setDate(7,  carga.getFechaVencimiento());
			cs.setDate(8,  carga.getFechaSalida());
			cs.setDouble(9, carga.getPesoUnidad_Empaquetado());
			cs.setBoolean(10, carga.isCondRefrigeracion());
			cs.setDate(11, carga.getHoraSalida_Plan());
			cs.executeUpdate();
			int cant = carga.getCantPaquetes();
			for (int i = 0; i < cant;i++) {
				paqServ.createPaquete(new PaqueteDto(carga.getIdCarga(), "00".concat(String.valueOf(i)), "caja"));
			}
		}
	}

	@Override
	public void updateCarga(CargaDto carga) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"carga_update\"(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, carga.getIdCarga());
			cs.setString(2, carga.getTipoEmpaquetado());
			cs.setDate(3, (java.sql.Date) carga.getFechaIngreso());
			cs.setInt(4, carga.getCantPaquetes());
			cs.setString(5, carga.getNombCarga());
			cs.setString(6, carga.getTipoProducto());
			cs.setDate(7, (java.sql.Date) carga.getFechaVencimiento());
			cs.setDate(8, (java.sql.Date) carga.getFechaSalida());
			cs.setDouble(9, carga.getPesoUnidad_Empaquetado());
			cs.setBoolean(10, carga.isCondRefrigeracion());
			cs.setDate(11, (java.sql.Date) carga.getHoraSalida_Plan());
			cs.executeUpdate();
		}
	}

	@Override
	public List<CargaDto> listCargas() throws SQLException {
		List<CargaDto> cargas = new ArrayList<CargaDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Carga\"");
			while(result.next()) {
				cargas.add(new CargaDto(
						result.getString("cod_carga"),
						result.getString("tipo_empaquetado"),
						result.getDate("fecha_hora_ingreso"),
						result.getInt("cant_paquetes"),
						result.getString("nomb_carga"),
						result.getString("tipo_producto"),
						result.getDate("fecha_vencimiento"),
						result.getDate("fecha_hora_salida"),
						result.getDouble("peso_unidad_empaquetado"),
						result.getBoolean("condiciones_refrigeracion"),
						result.getDate("fecha_hora_salida_plan")));
			}
		}
		return cargas;
	}

	@Override
	public CargaDto getCargaById(String cargaId) throws SQLException {
		CargaDto carga = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM \"Carga\" WHERE \"cod_carga\" = ?");

			pstmt.setString(1, cargaId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				carga = new CargaDto(rs.getString("cod_carga"),
						rs.getString("tipo_empaquetado"),
						rs.getDate("fecha_hora_ingreso"),
						rs.getInt("cant_paquetes"),
						rs.getString("nomb_carga"),
						rs.getString("tipo_producto"),
						rs.getDate("fecha_vencimiento"),
						rs.getDate("fecha_hora_salida"),
						rs.getDouble("peso_unidad_empaquetado"),
						rs.getBoolean("condiciones_refrigeracion"),
						rs.getDate("fecha_hora_salida_plan"));
			}
		}
		return carga;
	}

	@Override
	public void deleteCarga(String id) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement CS = conn.prepareCall(
					"{call carga_delete(?)}");

			CS.setString(1, id);
			CS.executeUpdate();	
		}
	}

	@Override
	public List<CargaDto> getCargasByClient(String email_cliente) throws SQLException {
		List<CargaDto> cargas = new ArrayList<CargaDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT\r\n"
					+ "  \"Carga\".tipo_empaquetado, \r\n"
					+ "  \"Carga\".fecha_hora_ingreso, \r\n"
					+ "  \"Carga\".cant_paquetes, \r\n"
					+ "  \"Carga\".nomb_carga, \r\n"
					+ "  \"Carga\".tipo_producto, \r\n"
					+ "  \"Carga\".fecha_vencimiento, \r\n"
					+ "  \"Carga\".fecha_hora_salida, \r\n"
					+ "  \"Carga\".peso_unidad_empaquetado, \r\n"
					+ "  \"Carga\".condiciones_refrigeracion, \r\n"
					+ "  \"Carga\".fecha_hora_salida_plan, \r\n"
					+ "  \"Carga\".cod_carga\r\n"
					+ "FROM \r\n"
					+ "  public.\"Carga\", \r\n"
					+ "  public.\"Servicio\"\r\n"
					+ "WHERE \r\n"
					+ "  \"Carga\".cod_carga = \"Servicio\".cod_carga AND \"Servicio\".email_cliente = ? and \"Carga\".fecha_hora_salida is null");
			pstmt.setString(1, email_cliente);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				cargas.add(new CargaDto(
						result.getString("cod_carga"),
						result.getString("tipo_empaquetado"),
						result.getDate("fecha_hora_ingreso"),
						result.getInt("cant_paquetes"),
						result.getString("nomb_carga"),
						result.getString("tipo_producto"),
						result.getDate("fecha_vencimiento"),
						result.getDate("fecha_hora_salida"),
						result.getDouble("peso_unidad_empaquetado"),
						result.getBoolean("condiciones_refrigeracion"),
						result.getDate("fecha_hora_salida_plan")));
			}
		}
		return cargas;
		
	}

}
