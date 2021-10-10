package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.PaqueteDto;

public interface PaqueteService {

	List<PaqueteDto> getPaquetes();
	List<PaqueteDto> getPaquetesById(String cargaId);
	void createPaquete(PaqueteDto paq);
	void updatePaquete(PaqueteDto paq);
	void deletePaquete(String cargaId,String idpaq);
}
