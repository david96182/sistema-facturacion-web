package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.CaracteristicasDto;

public interface CaracteristicasService {
	
	List<CaracteristicasDto> getAlternativas() throws SQLException;
	List<CaracteristicasDto> getCriterios() throws SQLException;
	List<CaracteristicasDto> getNiveles() throws SQLException;
	List<CaracteristicasDto> getTipo() throws SQLException;
	
	List<CaracteristicasDto> getAlternativasByCompany(String company) throws SQLException;
	CaracteristicasDto getAlternativasById(Long roleId) throws SQLException;
	
	List<CaracteristicasDto> getNivelesByCompany(String company) throws SQLException;
	CaracteristicasDto getNivelesById(Long roleId) throws SQLException;
	
	List<CaracteristicasDto> getCriteriosByCompany(String company) throws SQLException;
	CaracteristicasDto getCriteriosById(Long roleId) throws SQLException;
}
