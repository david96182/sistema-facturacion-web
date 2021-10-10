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

import cu.edu.cujae.backend.core.dto.PisoDto;
import cu.edu.cujae.backend.core.service.PisoService;


@RestController
@RequestMapping("/api/v1/pisos")
public class PisoController {
	
	@Autowired
	private PisoService pisoService;
	
	@GetMapping("")
    public ResponseEntity<List<PisoDto>> getPisos() throws SQLException {
		List<PisoDto> pisos = pisoService.listPisos();
		return ResponseEntity.ok(pisos);
	}
	
	@GetMapping("/{numpiso}")
    public ResponseEntity<PisoDto> getPisoById(@PathVariable String numpiso) throws SQLException {
		PisoDto piso = pisoService.getPisoById(numpiso);
		 return ResponseEntity.ok(piso);		
	}
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody PisoDto piso) throws SQLException {
		
		pisoService.createPiso(piso);		
		 return ResponseEntity.ok("Piso Created");
	}
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody PisoDto piso) throws SQLException {
		pisoService.updatePiso(piso);
        return ResponseEntity.ok("Piso Updated");
    }
	
	@DeleteMapping("/{cod_almacen}/{cod_empresa}/{cod_estante}/{/numpiso}")
    public ResponseEntity<String> delete(@PathVariable String cod_almacen, @PathVariable String cod_empresa,
    		@PathVariable String cod_estante, @PathVariable String numpiso) throws SQLException {
		pisoService.deletePiso(cod_almacen, cod_empresa, cod_estante, numpiso);
		return ResponseEntity.ok("Piso deleted");
	}
	
	@GetMapping("/estantes/{cod_estante}")
    public ResponseEntity<List<PisoDto>> getEsantesByAlmacenId(@PathVariable String cod_estante) throws SQLException {
		List<PisoDto> pisos = pisoService.getPisosByEstanteId(cod_estante);
        return ResponseEntity.ok(pisos);
    }
}
