package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.AlmacenDto;


public interface AlmacenService {
	
	void createAlmacen(AlmacenDto almacen) throws SQLException;
	
	void updateAlmacen(AlmacenDto almacen) throws SQLException; //exception
	
	List<AlmacenDto> listAlmacenes() throws SQLException;
	
	AlmacenDto getAlmacenById(String idAlmacen) throws SQLException;
	
	void deleteAlmacen(String idAlmacen, String cod_emp) throws SQLException;
	

}
