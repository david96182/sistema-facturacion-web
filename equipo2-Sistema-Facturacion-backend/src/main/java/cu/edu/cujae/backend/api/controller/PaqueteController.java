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

import cu.edu.cujae.backend.core.dto.PaqueteDto;
import cu.edu.cujae.backend.core.service.PaqueteService;

@RestController
@RequestMapping("/api/v1/paquetes")
public class PaqueteController {

	@Autowired
	private PaqueteService paqService;
	
	@GetMapping("")
    public ResponseEntity<List<PaqueteDto>> getPaquetes() throws SQLException {
		List<PaqueteDto> paqueteList = paqService.listPaquetes();
        return ResponseEntity.ok(paqueteList);
    }
	
	@GetMapping("/{cargaid}")
    public ResponseEntity<List<PaqueteDto>> getPaquetesById(@PathVariable String cargaid) throws SQLException {
		List<PaqueteDto> paqueteList = paqService.getPaquetesById(cargaid);
        return ResponseEntity.ok(paqueteList);
    }
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody PaqueteDto paquete) throws SQLException {
		paqService.createPaquete(paquete);
        return ResponseEntity.ok("Paquete Created");
    }
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody PaqueteDto paquete) throws SQLException {
		paqService.updatePaquete(paquete);
        return ResponseEntity.ok("Paquete Updated");
    }
	
	@DeleteMapping("/{cargaId}/{idpaq}")
    public ResponseEntity<String> delete(@PathVariable String cargaId,@PathVariable String idpaq) throws SQLException {
		paqService.deletePaquete(cargaId,idpaq);
        return ResponseEntity.ok("Paquete deleted");
    }

}
