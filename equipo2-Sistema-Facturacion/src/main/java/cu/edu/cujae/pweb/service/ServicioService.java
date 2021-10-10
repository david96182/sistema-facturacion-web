package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.ServicioDto;

public interface ServicioService {

	List<ServicioDto> getServicios();
	List<ServicioDto> getImportaciones();
	List<ServicioDto> getExportaciones();
	ServicioDto getServicioById(String servicioId);
	void createServicio(ServicioDto serv);
	void updateServicio(ServicioDto serv);
	void deleteServicio(String cod_carga,String email_cliente,String nomb_comp,String servicioId);
}
