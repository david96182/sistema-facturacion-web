package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.EstanteDto;


public interface EstanteService {
	
	void createEstante(EstanteDto estante) throws SQLException;
	
	void updateEstante(EstanteDto estante) throws SQLException;
	
	List<EstanteDto> listEstantes() throws SQLException;
	
	EstanteDto getEstanteById(String cod_estante) throws SQLException;
	
	void deleteEstante(String cod_almacen, String cod_estante , String cod_emp) throws SQLException;
	
	List<EstanteDto> getEstantesByAlmacenId(String id) throws SQLException;
	

}
