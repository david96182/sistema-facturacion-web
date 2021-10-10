package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.PaqueteDto;

public interface PaqueteService {
	
	void createPaquete(PaqueteDto paq) throws SQLException;
	
	void updatePaquete(PaqueteDto paq) throws SQLException;
	
	List<PaqueteDto> listPaquetes() throws SQLException;
	
	List<PaqueteDto> getPaquetesById(String cargaId) throws SQLException;
	
	void deletePaquete(String cargaId,String idpaq) throws SQLException;
}
