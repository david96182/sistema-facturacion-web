package cu.edu.cujae.pweb.service;
import java.util.List;

import cu.edu.cujae.pweb.dto.AlmacenDto;

public interface AlmacenService {
	
	List<AlmacenDto> getAlmacenes();
	AlmacenDto getAlmacenById(String cod_almacen);
	void createAlmacen(AlmacenDto almacen);
	void updateAlmacen(AlmacenDto almacen);
	void deleteAlmacen(String cod_almacen, String cod_empresa);

}
