package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.CaracteristicasDto;
import cu.edu.cujae.backend.core.service.CaracteristicasService;

@RestController
@RequestMapping("/api/v1/caracteristicas")
public class CaracteristicasController {

	@Autowired
	private CaracteristicasService caractService;
	
	@GetMapping("/alternativas")
    public ResponseEntity<List<CaracteristicasDto>> getAlternativas() throws SQLException {
		List<CaracteristicasDto> alternList = caractService.getAlternativas();
        return ResponseEntity.ok(alternList);
    }
	
	@GetMapping("/alternativa/{id}")
    public ResponseEntity<CaracteristicasDto> getAlternativasById(@PathVariable Long id) throws SQLException {
		CaracteristicasDto alternativa = caractService.getAlternativasById(id);
        return ResponseEntity.ok(alternativa);
    }
	
	@GetMapping("/company/alternativa/{id}")
    public ResponseEntity<List<CaracteristicasDto>> getAlternativasByCompany(@PathVariable String comp_id) throws SQLException {
		List<CaracteristicasDto> alternList = caractService.getAlternativasByCompany(comp_id);
        return ResponseEntity.ok(alternList);
    }
	
	@GetMapping("/niveles")
    public ResponseEntity<List<CaracteristicasDto>> getNiveles() throws SQLException {
		List<CaracteristicasDto> roleList = caractService.getNiveles();
        return ResponseEntity.ok(roleList);
    }
	
	@GetMapping("/nivel/{id}")
    public ResponseEntity<CaracteristicasDto> geNivelesById(@PathVariable Long id) throws SQLException {
		CaracteristicasDto role = caractService.getNivelesById(id);
        return ResponseEntity.ok(role);
    }
	
	@GetMapping("/company/nivel/{id}")
    public ResponseEntity<List<CaracteristicasDto>> getNivelesByCompany(@PathVariable String comp_id) throws SQLException {
		List<CaracteristicasDto> roleList = caractService.getNivelesByCompany(comp_id);
        return ResponseEntity.ok(roleList);
    }
	
	@GetMapping("/criterios")
    public ResponseEntity<List<CaracteristicasDto>> getCriterios() throws SQLException {
		List<CaracteristicasDto> roleList = caractService.getCriterios();
        return ResponseEntity.ok(roleList);
    }
	
	@GetMapping("/criterio/{id}")
    public ResponseEntity<CaracteristicasDto> getCriteriosById(@PathVariable Long id) throws SQLException {
		CaracteristicasDto role = caractService.getCriteriosById(id);
        return ResponseEntity.ok(role);
    }
	
	@GetMapping("/company/criterio/{id}")
    public ResponseEntity<List<CaracteristicasDto>> getCriteriosByCompany(@PathVariable String id) throws SQLException {
		List<CaracteristicasDto> roleList = caractService.getCriteriosByCompany(id);
        return ResponseEntity.ok(roleList);
    }
}
