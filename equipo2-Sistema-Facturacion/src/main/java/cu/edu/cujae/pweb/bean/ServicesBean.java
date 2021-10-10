package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.ServicioDto;
import cu.edu.cujae.pweb.service.ServicioService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ServicesBean {
	private ServicioDto servicioDto;
	private ServicioDto selectedServicio;
	private List<ServicioDto> servicios;
	private String selectmenu = "-1";
	
	@Autowired 
	private ServicioService serviceService;

	public ServicesBean() {

	}
	
	

	public void onItemSelect() {
		
		
	}


	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {

	}

	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveServicio() {
		try {
			serviceService.updateServicio(selectedServicio);

			JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Servicio Actualizado");
			PrimeFaces.current().executeScript("PF('manageServicioDialog').hide()");
			PrimeFaces.current().ajax().update("form:dt-servicios");
		}
		catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
	}

	//Permite eliminar un usuario
	public void deleteServicio() {
		try {
			serviceService.deleteServicio(selectedServicio.getIdCarga(), selectedServicio.getEmail_cliente(), selectedServicio.getNomb_comp(), selectedServicio.getCod_servicio());
			selectedServicio = null;

			servicios = serviceService.getServicios();
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Servicio Eliminado");
			PrimeFaces.current().ajax().update("form:dt-servicios");
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
	}

	public ServicioDto getServicioDto() {
		return servicioDto;
	}

	public void setServicioDto(ServicioDto servicioDto) {
		this.servicioDto = servicioDto;
	}

	public ServicioDto getSelectedServicio() {
		return selectedServicio;
	}

	public void setSelectedServicio(ServicioDto selectedServicio) {
		this.selectedServicio = selectedServicio;
	}

	public List<ServicioDto> getServicios() {
		if(selectmenu.equals("-1")) {
			servicios = serviceService.getServicios();
		}
		else if(selectmenu.equals("importacion")) {
			servicios = serviceService.getImportaciones();
		}
		else if(selectmenu.equals("exportacion")) {
			servicios = serviceService.getExportaciones();
		}
		return servicios;
	}

	public void setServicios(List<ServicioDto> servicios) {
		this.servicios = servicios;
	}

	public ServicioService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServicioService serviceService) {
		this.serviceService = serviceService;
	}



	public String getSelectmenu() {
		return selectmenu;
	}



	public void setSelectmenu(String selectmenu) {
		this.selectmenu = selectmenu;
	}

	


}
