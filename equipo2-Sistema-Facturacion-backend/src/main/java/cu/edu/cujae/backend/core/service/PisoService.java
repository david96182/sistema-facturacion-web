package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.PisoDto;



public interface PisoService {
	
	void createPiso(PisoDto piso) throws SQLException;
	
	void updatePiso(PisoDto piso) throws SQLException;
	
	List<PisoDto> listPisos() throws SQLException;
	
	PisoDto getPisoById(String numpiso) throws SQLException;
	
	void deletePiso(String numpiso, String cod_almacen, String cod_emp, String cod_estante) throws SQLException;
	
	List<PisoDto> getPisosByEstanteId(String cod_estante) throws SQLException;

}
