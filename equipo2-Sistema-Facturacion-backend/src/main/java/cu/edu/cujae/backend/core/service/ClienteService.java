package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.ClienteDto;


public interface ClienteService {

    void createCliente(ClienteDto cliente) throws SQLException;
	
	void updateCliente(ClienteDto cliente) throws SQLException;
	
	List<ClienteDto> listClientes() throws SQLException;
	
	ClienteDto getClienteById(String clienteId) throws SQLException;
	
	void deleteCliente(String id) throws SQLException;
}
