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

import cu.edu.cujae.backend.core.dto.AlmacenDto;
import cu.edu.cujae.backend.core.service.AlmacenService;


@RestController
@RequestMapping("/api/v1/almacenes")
public class AlmacenController {
	
	@Autowired
	private AlmacenService almacenService;
	
	@GetMapping("")
    public ResponseEntity<List<AlmacenDto>> getAlmacenes() throws SQLException {
		List<AlmacenDto> almacenesList = almacenService.listAlmacenes();
		return ResponseEntity.ok(almacenesList);
	}
	
	@GetMapping("/{idalmacen}")
    public ResponseEntity<AlmacenDto> getAlmacenById(@PathVariable String idalmacen) throws SQLException {
		AlmacenDto almacen = almacenService.getAlmacenById(idalmacen);
		return ResponseEntity.ok(almacen);
	}
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody AlmacenDto almacen) throws SQLException {
		almacenService.createAlmacen(almacen);
		return ResponseEntity.ok("Almacen Created");
	}
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody AlmacenDto almacen) throws SQLException {
		almacenService.updateAlmacen(almacen);
        return ResponseEntity.ok("Almacen Updated");
    }
	
	@DeleteMapping("/{idalmacen}/{cod_empresa}")
    public ResponseEntity<String> delete(@PathVariable String idalmacen, @PathVariable String cod_empresa) throws SQLException {
		almacenService.deleteAlmacen(idalmacen, cod_empresa);
        return ResponseEntity.ok("Almacen deleted");
    }
}
