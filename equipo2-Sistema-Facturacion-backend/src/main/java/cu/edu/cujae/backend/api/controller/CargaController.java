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

import cu.edu.cujae.backend.core.dto.CargaDto;
import cu.edu.cujae.backend.core.service.CargaService;

@RestController
@RequestMapping("/api/v1/cargas")
public class CargaController {

	@Autowired
	private CargaService cargaService;
	
	@GetMapping("")
    public ResponseEntity<List<CargaDto>> getCargas() throws SQLException {
		List<CargaDto> cargaList = cargaService.listCargas();
        return ResponseEntity.ok(cargaList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CargaDto> getCargaById(@PathVariable String id) throws SQLException {
		CargaDto carga = cargaService.getCargaById(id);
        return ResponseEntity.ok(carga);
    }
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody CargaDto carga) throws SQLException {
		cargaService.createCarga(carga);
        return ResponseEntity.ok("Carga Created");
    }
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody CargaDto carga) throws SQLException {
		cargaService.updateCarga(carga);
        return ResponseEntity.ok("Carga Updated");
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
		cargaService.deleteCarga(id);
        return ResponseEntity.ok("Carga deleted");
    }
	
	@GetMapping("/client/{email_cliente}")
	public ResponseEntity<List<CargaDto>> getCargasByClient(@PathVariable String email_cliente) throws SQLException{
		List<CargaDto> cargaList = cargaService.getCargasByClient(email_cliente);
		return ResponseEntity.ok(cargaList);
	}
}
