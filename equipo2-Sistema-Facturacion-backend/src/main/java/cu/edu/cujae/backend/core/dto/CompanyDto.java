package cu.edu.cujae.backend.core.dto;

import java.util.List;

public class CompanyDto {

	private String nameCompany;
	private String id;
	private String tipo;
	private List<CaracteristicasDto> alternativa;
	private List<CaracteristicasDto> niveles;
	private List<CaracteristicasDto> criterios;
	private boolean newRecord;
	
	public CompanyDto() {
		super();
	}

	public CompanyDto(String nameCompany, String tipo, String id, List<CaracteristicasDto> alternativa, 
					List<CaracteristicasDto> niveles, List<CaracteristicasDto> criterios, boolean newRecord) {
		super();
		this.id = id;
		this.nameCompany = nameCompany;
		this.tipo = tipo;
		this.alternativa = alternativa;
		this.niveles = niveles;
		this.criterios = criterios;
		this.newRecord = newRecord;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<CaracteristicasDto> getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(List<CaracteristicasDto> alternativa) {
		this.alternativa = alternativa;
	}

	public List<CaracteristicasDto> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<CaracteristicasDto> niveles) {
		this.niveles = niveles;
	}

	public List<CaracteristicasDto> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<CaracteristicasDto> criterios) {
		this.criterios = criterios;
	}

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

}
