package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.CargaDto;

public interface CargaService {
	
	List<CargaDto> getCargas();
	CargaDto getCargaById(String cargaId);
	List<CargaDto> getcargaByClient(String email_cliente);
	void createCarga(CargaDto carga);
	void updateCarga(CargaDto carga);
	void deleteCarga(String id);
    
}
