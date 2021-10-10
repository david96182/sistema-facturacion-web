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

import cu.edu.cujae.backend.core.dto.EstanteDto;
import cu.edu.cujae.backend.core.service.EstanteService;

@RestController
@RequestMapping("/api/v1/estantes")
public class EstanteController {
	
	@Autowired
	private EstanteService estanteService;
	
	@GetMapping("")
    public ResponseEntity<List<EstanteDto>> getEstantes() throws SQLException {
		List<EstanteDto> estantes = estanteService.listEstantes();
		return ResponseEntity.ok(estantes);
	}
	
	@GetMapping("/{cod_estante}")
    public ResponseEntity<EstanteDto> getEstanteById(@PathVariable String cod_estante) throws SQLException {
		 EstanteDto estante = estanteService.getEstanteById(cod_estante);
		 return ResponseEntity.ok(estante);
	}

	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody EstanteDto estante) throws SQLException {
		estanteService.createEstante(estante);
		 return ResponseEntity.ok("Estante Created");		
	}
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody EstanteDto estante) throws SQLException {
		estanteService.updateEstante(estante);
        return ResponseEntity.ok("Estante Updated");
    }
	
	@DeleteMapping("/{cod_almacen}/{idestante}/{cod_empresa}")
    public ResponseEntity<String> delete(@PathVariable String cod_almacen, @PathVariable String idestante ,
    		@PathVariable String cod_empresa) throws SQLException {
		
		estanteService.deleteEstante(cod_almacen, idestante, cod_empresa);
		return ResponseEntity.ok("Estante deleted");		
		
	}
	
	@GetMapping("/almacenes/{idalmacen}")
    public ResponseEntity<List<EstanteDto>> getEsantesByAlmacenId(@PathVariable String idalmacen) throws SQLException {
		List<EstanteDto> estantes = estanteService.getEstantesByAlmacenId(idalmacen);
        return ResponseEntity.ok(estantes);
    }
}
