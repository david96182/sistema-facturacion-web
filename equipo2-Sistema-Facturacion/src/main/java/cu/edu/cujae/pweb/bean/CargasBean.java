package cu.edu.cujae.pweb.bean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Iterator;
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
public class CargasBean {

	private CargaDto selectedcarga;
	private List<CargaDto> cargas;

	private PaqueteDto selectedpaq;
	private List<PaqueteDto> paquetes;

	private String codnewpaq;
	private String newptipo_paquete;
	private String selectmenu = "-1";
	private List<LocalDate> range;


	@Autowired
	private CargaService cargaservice;

	@Autowired
	private PaqueteService paqservice;

	public CargasBean() {
		super();
	}



	public void onDateSelect() {
		selectmenu = "-2";
		/*
		LocalDate limite1 = range.get(0);
		LocalDate limite2 = range.get(1);
        System.out.println(limite1);
        System.out.println(limite2);
        System.out.println(cargas.get(0).getFechaIngreso());
        Instant in = cargas.get(0).getFechaIngreso().toInstant();
        ZonedDateTime zft = in.atZone(ZoneId.systemDefault());
        LocalDate asdf = zft.toLocalDate();
        System.out.println(asdf);
        if(asdf.isAfter(limite1) && asdf.isBefore(limite2)) {
        	System.out.println("loes");
        }*/

	}

	public void onDateSelectt() {
		selectmenu = "-3";
	}

	public void onItemSelect() {

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
		if(selectmenu.equals("-1")) {
			cargas = cargaservice.getCargas();
		}
		else if(selectmenu.equals("exportadas")) {
			
			PrimeFaces.current().ajax().update("form:dtpaquetes");
			cargas.clear();
			List<CargaDto> li = cargaservice.getCargas();
			Iterator<CargaDto> it = li.iterator();
			while(it.hasNext()) {
				CargaDto car = it.next();
				if(car.getFechaSalida() != null) {
					cargas.add(car);
				}
			}
		}
		else if(selectmenu.equals("-2")) {
			cargas.clear();
			List<CargaDto> li = cargaservice.getCargas();
			LocalDate limite1 = range.get(0);
			LocalDate limite2 = range.get(1);

			Iterator<CargaDto> it = li.iterator();
			while(it.hasNext()) {
				CargaDto car = it.next();
				if(car.getFechaSalida() != null) {
					Instant in = car.getFechaSalida().toInstant();
					ZonedDateTime zft = in.atZone(ZoneId.systemDefault());
					LocalDate asdf = zft.toLocalDate();
					if(asdf.isAfter(limite1) && asdf.isBefore(limite2)) {
						cargas.add(car);
					}

				}
			}

		}
		else if(selectmenu.equals("-3")) {
			cargas.clear();
			List<CargaDto> li = cargaservice.getCargas();
			LocalDate limite1 = range.get(0);
			LocalDate limite2 = range.get(1);

			Iterator<CargaDto> it = li.iterator();
			while(it.hasNext()) {
				CargaDto car = it.next();
				Instant in = car.getFechaIngreso().toInstant();
				ZonedDateTime zft = in.atZone(ZoneId.systemDefault());
				LocalDate asdf = zft.toLocalDate();
				
				if(asdf.isAfter(limite1) && asdf.isBefore(limite2)) {
					cargas.add(car);


				}
			}

		}

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



	public String getSelectmenu() {
		return selectmenu;
	}



	public void setSelectmenu(String selectmenu) {
		this.selectmenu = selectmenu;
	}


	public List<LocalDate> getRange() {
		return range;
	}


	public void setRange(List<LocalDate> range) {
		this.range = range;
	}





}
