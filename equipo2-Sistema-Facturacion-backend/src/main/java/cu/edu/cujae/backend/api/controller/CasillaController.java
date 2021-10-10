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

import cu.edu.cujae.backend.core.dto.CasillaDto;
import cu.edu.cujae.backend.core.service.CasillaService;

@RestController
@RequestMapping("/api/v1/casillas")
public class CasillaController {
	
	@Autowired
	private CasillaService casillaService;
	
	@GetMapping("")
    public ResponseEntity<List<CasillaDto>> getCasillas() throws SQLException {
		List<CasillaDto> casillas = casillaService.listCasillas();
		 return ResponseEntity.ok(casillas);
	}
	
	@GetMapping("/{idcasilla}")
    public ResponseEntity<CasillaDto> getCasillaById(@PathVariable String idcasilla) throws SQLException {
		CasillaDto casilla = casillaService.getCasillaById(idcasilla);
		 return ResponseEntity.ok(casilla);
	}
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody CasillaDto casilla) throws SQLException {
		casillaService.createCasilla(casilla);
		return ResponseEntity.ok("Casilla Created");
	}
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody CasillaDto casilla) throws SQLException {
		casillaService.updateCasilla(casilla);
        return ResponseEntity.ok("Casilla Updated");
    }
	
	@DeleteMapping("/{cod_almacen}/{idcasilla}/{cod_empresa}/{cod_estante}/{numpiso}")
    public ResponseEntity<String> delete(@PathVariable String cod_almacen, @PathVariable String idcasilla, 
    		@PathVariable String cod_empresa, @PathVariable String cod_estante, @PathVariable String numpiso) throws SQLException {
		
		casillaService.deleteCasilla(cod_almacen, idcasilla, cod_empresa, cod_estante, numpiso);
        return ResponseEntity.ok("Casilla deleted");
    }
	
	@GetMapping("/pisos/{num_piso}")
    public ResponseEntity<List<CasillaDto>> getCasillasByPisoId(@PathVariable String num_piso) throws SQLException {
		List<CasillaDto> casillas = casillaService.getCasillasByPisoId(num_piso);
        return ResponseEntity.ok(casillas);
    }

}
