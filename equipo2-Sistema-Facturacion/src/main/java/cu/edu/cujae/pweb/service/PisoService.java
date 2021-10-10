package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.PisoDto;

public interface PisoService {
	
	List<PisoDto> getPisos();
	PisoDto getPisoById(String numPiso);
	void createPiso(PisoDto piso);
	void updatePiso(PisoDto piso);
	void deletePiso(String cod_almacen, String cod_emp,String cod_estante, String numpiso);
	List<PisoDto> getPisosByEstanteId(String cod_estante);

}
