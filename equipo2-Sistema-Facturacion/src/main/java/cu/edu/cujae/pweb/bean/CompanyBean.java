package cu.edu.cujae.pweb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CaracteristicasDto;
import cu.edu.cujae.pweb.dto.CompanyDto;
import cu.edu.cujae.pweb.service.CaracteristicasService;
import cu.edu.cujae.pweb.service.CompanyService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component 
@ManagedBean
@ViewScoped
public class CompanyBean {

	private CompanyDto companyDto;
	private CompanyDto selectedCompany;
	private List<CompanyDto> companys;
	private Long[] selectedAlternativas;
	private Long[] selectedCriterios;
	private Long[] selectedNiveles;
	
	private List<CaracteristicasDto> alternativas;
	private List<CaracteristicasDto> niveles;
	private List<CaracteristicasDto> criterios;
	private List<CaracteristicasDto> tipo;
	
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CaracteristicasService roleService;
	
	public CompanyBean() {
		super();
	} 
	
	public void openNew() {
        this.selectedCompany = new CompanyDto();
        this.selectedNiveles = null;
        this.selectedCriterios = null;
        this.selectedAlternativas = null;
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		List<CaracteristicasDto> niveles = this.selectedCompany.getNiveles();
		this.selectedNiveles = niveles.stream().map(r -> r.getId()).toArray(Long[]::new);
		
		List<CaracteristicasDto> crit = this.selectedCompany.getCriterios();
		this.selectedCriterios = crit.stream().map(r -> r.getId()).toArray(Long[]::new);
		
		List<CaracteristicasDto> alter = this.selectedCompany.getAlternativa();
		this.selectedAlternativas = alter.stream().map(r -> r.getId()).toArray(Long[]::new);
	}	
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveUser() {
		
		List<CaracteristicasDto> alternativasToAdd = new ArrayList<CaracteristicasDto>();
        List<CaracteristicasDto> nivelesToAdd = new ArrayList<CaracteristicasDto>();
        List<CaracteristicasDto> criteriosToAdd = new ArrayList<CaracteristicasDto>();
		
        if (this.selectedCompany.getAlternativa() == null && this.selectedCompany.getCriterios() == null && this.selectedCompany.getNiveles() == null) {
            //this.selectedCompany.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.selectedCompany.setNewRecord(true);
            
            for(int i = 0; i < this.selectedAlternativas.length; i++) {
            	alternativasToAdd.add(roleService.getAlternativaById(selectedAlternativas[i]));
            }
            this.selectedCompany.setAlternativa(alternativasToAdd);
            
            for(int i = 0; i < this.selectedCriterios.length; i++) {
            	criteriosToAdd.add(roleService.getCriterioById(selectedCriterios[i]));
            }
            this.selectedCompany.setNiveles(nivelesToAdd);
            
            for(int i = 0; i < this.selectedNiveles.length; i++) {
            	nivelesToAdd.add(roleService.getNivelById(selectedNiveles[i]));
            }
            this.selectedCompany.setCriterios(criteriosToAdd);
            
            //register user
            companyService.createCompany(this.selectedCompany);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_company_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
        	
        	for(int i = 0; i < this.selectedAlternativas.length; i++) {
            	alternativasToAdd.add(roleService.getAlternativaById(selectedAlternativas[i]));
            }
            this.selectedCompany.setAlternativa(alternativasToAdd);
            
            for(int i = 0; i < this.selectedCriterios.length; i++) {
            	criteriosToAdd.add(roleService.getCriterioById(selectedCriterios[i]));
            }
            this.selectedCompany.setNiveles(nivelesToAdd);
            
            for(int i = 0; i < this.selectedNiveles.length; i++) {
            	nivelesToAdd.add(roleService.getNivelById(selectedNiveles[i]));
            }
            this.selectedCompany.setCriterios(criteriosToAdd);
            
        	companyService.updateCompany(this.selectedCompany);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_company_edited");
        }
        
      //load datatable again with new values
        companys = companyService.getCompanys();
        
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
	}

	//Permite eliminar un usuario
    public void deleteUser() {
    	
    	try {
    		//delete user
    		companyService.deleteCompany(this.selectedCompany.getNameCompany());
            this.selectedCompany = null;
            
            //load datatable again with new values
            companys = companyService.getCompanys();
            
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_company_deleted");
            PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    	
    }

	public CompanyDto getCompanyDto() {
		return companyDto;
	}

	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}

	public CompanyDto getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(CompanyDto selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public List<CompanyDto> getCompanys() {
		companys = companyService.getCompanys();
		return companys;
	}

	public void setCompanys(List<CompanyDto> companys) {
		this.companys = companys;
	}

	public List<CaracteristicasDto> getAlternativas() {
		alternativas = roleService.getAlternativas();
		return alternativas;
	}

	public void setAlternativas(List<CaracteristicasDto> alternativas) {
		this.alternativas = alternativas;
	}

	public List<CaracteristicasDto> getNiveles() {
		niveles = roleService.getNiveles();
		return niveles;
	}

	public void setNiveles(List<CaracteristicasDto> niveles) {
		this.niveles = niveles;
	}

	public List<CaracteristicasDto> getCriterios() {
		criterios = roleService.getCriterios();
		return criterios;
	}

	public void setCriterios(List<CaracteristicasDto> criterios) {
		this.criterios = criterios;
	}

	public List<CaracteristicasDto> getTipo() {
		return tipo;
	}

	public void setTipo(List<CaracteristicasDto> tipo) {
		this.tipo = tipo;
	}

	public Long[] getSelectedAlternativas() {
		return selectedAlternativas;
	}

	public void setSelectedAlternativas(Long[] selectedAlternativas) {
		this.selectedAlternativas = selectedAlternativas;
	}

	public Long[] getSelectedCriterios() {
		return selectedCriterios;
	}

	public void setSelectedCriterios(Long[] selectedCriterios) {
		this.selectedCriterios = selectedCriterios;
	}

	public Long[] getSelectedNiveles() {
		return selectedNiveles;
	}

	public void setSelectedNiveles(Long[] selectedNiveles) {
		this.selectedNiveles = selectedNiveles;
	}
	
}
