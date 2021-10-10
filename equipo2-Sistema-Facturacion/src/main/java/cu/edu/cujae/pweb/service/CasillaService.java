package cu.edu.cujae.pweb.service;

import java.util.List;


import cu.edu.cujae.pweb.dto.CasillaDto;

public interface CasillaService {

	List<CasillaDto> getCasillas();
	CasillaDto getCasillaById(String idCasilla);
	void createCasilla(CasillaDto casilla);
	void updateCasilla(CasillaDto casilla);
	void deleteCasilla(String cod_almacen, String idcasilla, String cod_emp, String cod_estante, String num_piso);
	List<CasillaDto> getCasillasByPisoId(String numpiso);
}
