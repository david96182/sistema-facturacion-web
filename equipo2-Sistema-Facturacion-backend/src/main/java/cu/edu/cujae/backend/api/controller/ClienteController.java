package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.ClienteDto;
import cu.edu.cujae.backend.core.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("")
    public ResponseEntity<List<ClienteDto>> getClientes() throws SQLException {
		List<ClienteDto> clienteList = clienteService.listClientes();
        return ResponseEntity.ok(clienteList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getUserById(@PathVariable String id) throws SQLException {
		ClienteDto cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody ClienteDto cliente) throws SQLException {
		clienteService.createCliente(cliente);
        return ResponseEntity.ok("Client Created");
    }
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody ClienteDto cliente) throws SQLException {
		clienteService.updateCliente(cliente);
        return ResponseEntity.ok("Cliente Updated");
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
		clienteService.deleteCliente(id);
        return ResponseEntity.ok("Client deleted");
    }

}
