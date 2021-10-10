package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CargaDto;
import cu.edu.cujae.pweb.dto.PaqueteDto;
import cu.edu.cujae.pweb.service.CargaService;
import cu.edu.cujae.pweb.service.PaqueteService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class reportCargasIngresadas {

	private CargaDto selectedcarga;
	private List<CargaDto> cargas;
	
	private PaqueteDto selectedpaq;
	private List<PaqueteDto> paquetes;
	
	private String codnewpaq;
	private String newptipo_paquete;
	
	@Autowired
	private CargaService cargaservice;
	
	@Autowired
	private PaqueteService paqservice;
	
	public reportCargasIngresadas() {
		super();
	}
	
	
	
	public void onRowSelect(SelectEvent<CargaDto> event) {
        paquetes = paqservice.getPaquetesById(String.valueOf(event.getObject().getIdCarga()));
		
        
    }
	public void openForEditPaquete() {
		
	}

	public void deletePaquete() {
		try {
    		//delete user
    		paqservice.deletePaquete(this.selectedcarga.getIdCarga() ,selectedpaq.getCod_paquete());
            selectedcarga.setCantPaquetes(selectedcarga.getCantPaquetes()-1);
    		cargaservice.updateCarga(selectedcarga);
            
            this.selectedpaq = null;
            
            //load datatable again with new values
            paquetes = paqservice.getPaquetesById(selectedcarga.getIdCarga());
            cargas = cargaservice.getCargas();
            
            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Paquete Eliminado");
            PrimeFaces.current().ajax().update("form:dtpaquetes");
            PrimeFaces.current().ajax().update("form:dtcargas");
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
	}
	
	public void savePaquete() {
		try {
		paqservice.updatePaquete(selectedpaq);
		
		JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Paquete Actualizado");
		PrimeFaces.current().executeScript("PF('managePaqueteDialog').hide()");
		PrimeFaces.current().ajax().update("form:dtpaquetes");
		}
		catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
	}
	public void createPaquete() {
		setSelectedpaq(null);
		int ca = getPaquetes().size()+1;
		String co = "00"+ca;
		setCodnewpaq(co);
		paqservice.createPaquete(new PaqueteDto(selectedcarga.getIdCarga(),co,newptipo_paquete));
		paquetes = paqservice.getPaquetesById(selectedcarga.getIdCarga());
		JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Paquete Añadido");
		PrimeFaces.current().executeScript("PF('manageNewPaqueteDialog').hide()");
		PrimeFaces.current().ajax().update("form:dtpaquetes");
	}
	
	public void openForEditCarga() {
		
	}
	public void openNew() {
		if(selectedcarga == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado una carga a la que agregar el paquete");
			PrimeFaces.current().executeScript("PF('manageNewPaqueteDialog').hide()");
		}
		
	}
	
	public void deleteCarga() {
		try {
    		//delete user
    		cargaservice.deleteCarga(this.selectedcarga.getIdCarga());
            this.selectedcarga = null;
            
            //load datatable again with new values
            cargas = cargaservice.getCargas();
            
            
            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Carga Eliminada");
            PrimeFaces.current().ajax().update("form:dtcargas");
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
	}
	
	public void saveCarga() {
		try {
			cargaservice.updateCarga(selectedcarga);
			
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Carga Actualizada");
			PrimeFaces.current().executeScript("PF('manageCargaDialog').hide()");
			PrimeFaces.current().ajax().update("form:dtcargas");
			}
			catch (Exception e) {
				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
			}
	}
	public void openForView() {
		
	}
	
	
	
	
	public CargaDto getSelectedcarga() {
		return selectedcarga;
	}

	public void setSelectedcarga(CargaDto selectedcarga) {
		this.selectedcarga = selectedcarga;
	}

	public List<CargaDto> getCargas() {
		cargas = cargaservice.getCargas();
		return cargas;
	}

	public void setCargas(List<CargaDto> cargas) {
		this.cargas = cargas;
	}

	public PaqueteDto getSelectedpaq() {
		return selectedpaq;
	}

	public void setSelectedpaq(PaqueteDto selectedpaq) {
		this.selectedpaq = selectedpaq;
	}

	public List<PaqueteDto> getPaquetes() {
		if(selectedcarga != null) {
			paquetes = paqservice.getPaquetesById(selectedcarga.getIdCarga());
		}
		return paquetes;
	}

	public void setPaquetes(List<PaqueteDto> paquetes) {
		this.paquetes = paquetes;
	}

	public CargaService getCargaservice() {
		return cargaservice;
	}

	public void setCargaservice(CargaService cargaservice) {
		this.cargaservice = cargaservice;
	}

	public PaqueteService getPaqservice() {
		return paqservice;
	}

	public void setPaqservice(PaqueteService paqservice) {
		this.paqservice = paqservice;
	}



	public String getCodnewpaq() {
		return codnewpaq;
	}



	public void setCodnewpaq(String codnewpaq) {
		this.codnewpaq = codnewpaq;
	}



	public String getNewptipo_paquete() {
		return newptipo_paquete;
	}



	public void setNewptipo_paquete(String newptipo_paquete) {
		this.newptipo_paquete = newptipo_paquete;
	}

	
	
	
}
