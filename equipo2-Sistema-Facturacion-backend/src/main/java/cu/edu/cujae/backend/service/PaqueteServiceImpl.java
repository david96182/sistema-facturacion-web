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

import cu.edu.cujae.backend.core.dto.PaqueteDto;
import cu.edu.cujae.backend.core.service.PaqueteService;

@Service
public class PaqueteServiceImpl implements PaqueteService{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createPaquete(PaqueteDto paq) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"paquete_insert\"(?,?,?)}");
			cs.setString(1, paq.getIdCarga());
			cs.setString(2, paq.getCod_paquete());
			cs.setString(3, paq.getTipo_paquete());
			cs.executeUpdate();
		}
	}

	@Override
	public void updatePaquete(PaqueteDto paq) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"paquete_update\"(?,?,?)}");
			cs.setString(1, paq.getIdCarga());
			cs.setString(2, paq.getCod_paquete());
			cs.setString(3, paq.getTipo_paquete());
			cs.executeUpdate();
		}
	}

	@Override
	public List<PaqueteDto> listPaquetes() throws SQLException {
		List<PaqueteDto> paquetes = new ArrayList<PaqueteDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Paquete\"");
			while(result.next())
			{
				paquetes.add(new PaqueteDto(
						result.getString("cod_carga"),
						result.getString("cod_paquete"),
						result.getString("tipo_paquete")));
			}
		}
		return paquetes;
	}

	@Override
	public List<PaqueteDto> getPaquetesById(String cargaId) throws SQLException {
		List<PaqueteDto> paquetes = new ArrayList<PaqueteDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"Paquete\" WHERE \"cod_carga\" = ?");
			statement.setString(1, cargaId);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				paquetes.add(new PaqueteDto(
						resultSet.getString("cod_carga"),
						resultSet.getString("cod_paquete"),
						resultSet.getString("tipo_paquete")));
			}
		}
		return paquetes;
	}

	@Override
	public void deletePaquete(String cargaId, String idpaq) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"paquete_delete\"(?,?)}");
			cs.setString(1, cargaId);
			cs.setString(2,idpaq);
			cs.executeUpdate();
		}
	}

}
