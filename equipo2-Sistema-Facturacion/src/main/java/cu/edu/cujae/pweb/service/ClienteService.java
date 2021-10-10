package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.ClienteDto;



public interface ClienteService {

	List<ClienteDto> getClientes();
	ClienteDto getClienteById(String clienteId);
	void createCliente(ClienteDto user);
	void updateCliente(ClienteDto user);
	void deleteCliente(String id);
}
