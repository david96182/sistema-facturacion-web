package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;

import cu.edu.cujae.backend.core.dto.EmpresaDto;

public interface EmpresaService {
	EmpresaDto getEmpresa() throws SQLException;

}
