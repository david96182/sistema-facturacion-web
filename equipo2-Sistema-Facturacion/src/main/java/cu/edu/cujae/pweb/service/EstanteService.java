package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.EstanteDto;


public interface EstanteService {
	
	List<EstanteDto> getEstantes();
	EstanteDto getEstanteById(String cod_estante);
	void createEstante(EstanteDto estante);
	void updateEstante(EstanteDto estante);
	void deleteEstante(String cod_almacen, String idestante , String cod_emp);
	List<EstanteDto> getEstantesByAlmacenId(String cod_almacen);

}
