package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.CaracteristicasDto;
import cu.edu.cujae.pweb.dto.RoleDto;

public interface CaracteristicasService {
	
	List<CaracteristicasDto> getAlternativas();
	List<CaracteristicasDto> getCriterios();
	List<CaracteristicasDto> getNiveles();
	//List<CaracteristicasDto> getTipo();
	
	List<CaracteristicasDto> getAlternativaByCompany(String id);
	List<CaracteristicasDto> getNivelByCompany(String name);
	List<CaracteristicasDto> getCriterioByCompany(String id);
	
	CaracteristicasDto getAlternativaById(Long roleId);
	CaracteristicasDto getNivelById(Long roleId);
	CaracteristicasDto getCriterioById(Long roleId);
	
	//----------------------
	
	List<RoleDto> getRoles();
	List<RoleDto> getRolesByUser(Long trabajadorId);
	//List<RoleDto> getRolesByName(String name);
	RoleDto getRolesById(Long roleId);
}
