package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.CargaDto;

public interface CargaService {

	void createCarga(CargaDto carga) throws SQLException;
	
	void updateCarga(CargaDto carga) throws SQLException;
	
	List<CargaDto> listCargas() throws SQLException;
	
	CargaDto getCargaById(String cargaId) throws SQLException;
	
	List<CargaDto> getCargasByClient(String email_cliente) throws SQLException;
	
	void deleteCarga(String id) throws SQLException;

}
