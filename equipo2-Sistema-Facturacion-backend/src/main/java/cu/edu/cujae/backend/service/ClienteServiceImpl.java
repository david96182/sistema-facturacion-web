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

import cu.edu.cujae.backend.core.dto.ClienteDto;
import cu.edu.cujae.backend.core.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public void createCliente(ClienteDto cliente) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"cliente_insert\"(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, cliente.getEmail());
			cs.setDouble(2, cliente.getTarifaTransporte());
			cs.setDouble(3, cliente.getTarifaRefrigeracion());
			cs.setDouble(4, cliente.getTarifaAlmacenamiento());
			cs.setInt(5, cliente.getAntiguedad());
			cs.setString(6, cliente.getFaxCliente());
			cs.setString(7, cliente.getPaisCliente());
			cs.setString(8, cliente.getDireccionCliente());
			cs.setString(9, cliente.getNombCliente());
			cs.setInt(10, cliente.getAnnosServicio());
			cs.setString(11, cliente.getTelefCliente());
			cs.setInt(12, cliente.getAnnos_suplidos());
			cs.setBoolean(13, cliente.isClientePriorizado());
			cs.setString(14, cliente.getTipo_cliente());
			cs.executeUpdate();
		}

	}

	@Override
	public void updateCliente(ClienteDto cliente) throws SQLException{
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement cs = conn.prepareCall("{call \"cliente_update\"(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, cliente.getEmail());
			cs.setDouble(2, cliente.getTarifaTransporte());
			cs.setDouble(3, cliente.getTarifaRefrigeracion());
			cs.setDouble(4, cliente.getTarifaAlmacenamiento());
			cs.setInt(5, cliente.getAntiguedad());
			cs.setString(6, cliente.getFaxCliente());
			cs.setString(7, cliente.getPaisCliente());
			cs.setString(8, cliente.getDireccionCliente());
			cs.setString(9, cliente.getNombCliente());
			cs.setInt(10, cliente.getAnnosServicio());
			cs.setString(11, cliente.getTelefCliente());
			cs.setInt(12, cliente.getAnnos_suplidos());
			cs.setBoolean(13, cliente.isClientePriorizado());
			cs.setString(14, cliente.getTipo_cliente());
			cs.executeUpdate();
		}
	}

	@Override
	public List<ClienteDto> listClientes() throws SQLException {
		List<ClienteDto> clientes = new ArrayList<ClienteDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Cliente\"");
			while(result.next())
			{
				clientes.add(new ClienteDto(
						result.getString("email_cliente"),
						result.getDouble("tarifa_base_transporte"),
						result.getDouble("tarifa_base_refrigeracion"),
						result.getDouble("tarifa_carga_almacenada"),
						result.getInt("antiguedad"),
						result.getString("fax"),
						result.getString("pais"),
						result.getString("direccion"),
						result.getString("nomb_cliente"),
						result.getInt("annos_servicio"),
						result.getString("telefono_cliente"),
						result.getInt("annos_suplidos"),
						result.getBoolean("cliente_preferencial"),
						result.getString("tipo_cliente")));
			}
		}
		return clientes;
	}

	@Override
	public ClienteDto getClienteById(String clienteId) throws SQLException {
		ClienteDto cliente = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM \"Cliente\" WHERE \"email_cliente\" = ?");

			pstmt.setString(1, clienteId);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				cliente = new ClienteDto(
						rs.getString("email_cliente"),
						rs.getDouble("tarifa_base_transporte"),
						rs.getDouble("tarifa_base_refrigeracion"),
						rs.getDouble("tarifa_carga_almacenada"),
						rs.getInt("antiguedad"),
						rs.getString("fax"),
						rs.getString("pais"),
						rs.getString("direccion"),
						rs.getString("nomb_cliente"),
						rs.getInt("annos_servicio"),
						rs.getString("telefono_cliente"),
						rs.getInt("annos_suplidos"),
						rs.getBoolean("cliente_preferencial"),
						rs.getString("tipo_cliente"));
			}
		}

		return cliente;
	}

	@Override
	public void deleteCliente(String id) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			CallableStatement CS = conn.prepareCall(
					"{call cliente_delete(?)}");

			CS.setString(1, id);
			CS.executeUpdate();	
		}
	}
}
