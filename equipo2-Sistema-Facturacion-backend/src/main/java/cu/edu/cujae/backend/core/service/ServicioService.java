package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.ServicioDto;

public interface ServicioService {

	void createServicio(ServicioDto servicio) throws SQLException;
	
	void updateServicio(ServicioDto servicio) throws SQLException;
	
	List<ServicioDto> listServicios() throws SQLException;
	
	ServicioDto getServicioById(String servicioId) throws SQLException;
	
	void deleteServicio(String cod_carga,String email_cliente,String nomb_comp,String servicioId) throws SQLException;

	List<ServicioDto> getImportaciones() throws SQLException;
	
	List<ServicioDto> getExportaciones() throws SQLException;
}
