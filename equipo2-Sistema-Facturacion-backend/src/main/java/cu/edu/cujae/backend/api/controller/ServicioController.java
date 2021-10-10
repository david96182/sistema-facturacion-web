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

import cu.edu.cujae.backend.core.dto.ServicioDto;
import cu.edu.cujae.backend.core.service.ServicioService;

@RestController
@RequestMapping("/api/v1/servicios")
public class ServicioController {
	
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping("")
    public ResponseEntity<List<ServicioDto>> getServicios() throws SQLException {
		List<ServicioDto> servicioList = servicioService.listServicios();
        return ResponseEntity.ok(servicioList);
    }
	
	@GetMapping("/{servicioid}")
    public ResponseEntity<ServicioDto> getServicioById(@PathVariable String servicioid) throws SQLException {
		ServicioDto servicioList = servicioService.getServicioById(servicioid);
        return ResponseEntity.ok(servicioList);
    }
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody ServicioDto servicio) throws SQLException {
		servicioService.createServicio(servicio);
        return ResponseEntity.ok("Servicio Created");
    }
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody ServicioDto servicio) throws SQLException {
		servicioService.updateServicio(servicio);
        return ResponseEntity.ok("Servicio Updated");
    }
	
	@DeleteMapping("/{cod_carga}/{email_cliente}/{nomb_comp}/{servicioId}")
    public ResponseEntity<String> delete(@PathVariable String cod_carga,@PathVariable String email_cliente,@PathVariable String nomb_comp,@PathVariable String servicioId) throws SQLException {
		servicioService.deleteServicio(cod_carga,email_cliente,nomb_comp,servicioId);
        return ResponseEntity.ok("Servicio deleted");
    }
	
	@GetMapping("/importaciones")
    public ResponseEntity<List<ServicioDto>> getImportaciones() throws SQLException {
		List<ServicioDto> servicioList = servicioService.getImportaciones();
        return ResponseEntity.ok(servicioList);
    }
	
	@GetMapping("/exportaciones")
    public ResponseEntity<List<ServicioDto>> getExportaciones() throws SQLException {
		List<ServicioDto> servicioList = servicioService.getExportaciones();
        return ResponseEntity.ok(servicioList);
    }
}
