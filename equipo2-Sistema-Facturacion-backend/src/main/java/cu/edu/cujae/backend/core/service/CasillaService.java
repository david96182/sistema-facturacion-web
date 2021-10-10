package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.CasillaDto;


public interface CasillaService {
	
	void createCasilla(CasillaDto casilla) throws SQLException;
	
	void updateCasilla(CasillaDto casilla) throws SQLException; //same
	
	List<CasillaDto> listCasillas() throws SQLException;
	
	CasillaDto getCasillaById(String cod_casilla) throws SQLException;
	
	void deleteCasilla(String cod_almacen, String cod_casilla, String cod_emp, String cod_estante, String num_piso) throws SQLException;
	
	List<CasillaDto> getCasillasByPisoId(String num_piso) throws SQLException;

}
