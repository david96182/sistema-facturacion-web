package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.EmpresaDto;
import cu.edu.cujae.backend.core.service.EmpresaService;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

	@Autowired EmpresaService empresaservice;
	
	@GetMapping("")
    public ResponseEntity<EmpresaDto> getEmpresa() throws SQLException {
		EmpresaDto empre = empresaservice.getEmpresa();
        return ResponseEntity.ok(empre);
    }
}
