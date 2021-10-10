package cu.edu.cujae.pweb.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CargaDto;
import cu.edu.cujae.pweb.dto.ClienteDto;
import cu.edu.cujae.pweb.dto.CompanyDto;
import cu.edu.cujae.pweb.dto.ServicioDto;
import cu.edu.cujae.pweb.service.CargaService;
import cu.edu.cujae.pweb.service.ClienteService;
import cu.edu.cujae.pweb.service.CompanyService;
import cu.edu.cujae.pweb.service.ServicioService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ExportBean {
	
	private ClienteDto clientDto;
	private ClienteDto selectedClient;
	private List<ClienteDto> clientes;
	
	private CompanyDto companydto;
	private CompanyDto selectedCompany;
	private List<CompanyDto> companys;
	
	private CargaDto cargaDto;
	private CargaDto selectedCarga;
	private List<CargaDto> cargas;
 
	
	private float combustible_req;
	private Date fecha_salida;
	private double importe_total = 0;
	
	private Date today = new Date();
	
	@Autowired
	private ClienteService clienteservice;
	@Autowired
	private CompanyService companyservice;
	@Autowired
	private CargaService cargaservice;
	@Autowired
	private ServicioService servicioservice;
	
	public ExportBean() {
		
	}
	
	
	public void calcImporte() {
		if(combustible_req > 0) {
			importe_total = 0;
			
			importe_total += selectedClient.getTarifaTransporte()*combustible_req;
			importe_total += selectedCarga.getPesoUnidad_Empaquetado() * selectedClient.getTarifaAlmacenamiento();
			setImporte_total(importe_total);
			
		}
	}
	
	public void onRowSelect(SelectEvent<ClienteDto> event) {
        cargas = cargaservice.getcargaByClient(String.valueOf(event.getObject().getEmail()));
		if(cargas.isEmpty()) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_WARN, "El cliente seleccionado no tiene cargas para exportar");
		}
        
    }
	public void registrarExport() {
		boolean correct = true;
		if(this.selectedClient == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado un cliente");
			correct = false;
		}
		if(this.selectedCarga == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado una carga");
			correct = false;
		}
		if(this.selectedCompany == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado una compañia");
			correct = false;
		}
		if(correct) {
			try {
				selectedCarga.setFechaSalida(fecha_salida);
				cargaservice.updateCarga(selectedCarga);
				
				int co = servicioservice.getServicios().size();
				String cods = "0"+co;
				ServicioDto serv = new ServicioDto(selectedCarga.getIdCarga(), selectedClient.getEmail(), "exportacion", combustible_req, importe_total, "1", selectedCompany.getNameCompany(), 1, cods);
				servicioservice.createServicio(serv);
				
				JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Servicio registrado");
				cleanForm();
				PrimeFaces.current().ajax().update(":form:manage-user-content");
			}catch (Exception e) {
				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
			}
		}
		
	}
	public void cleanForm() {
		selectedClient = null;
		selectedCarga = null;
		selectedCompany = null;
		combustible_req = 0;
		fecha_salida = null;
		importe_total = 0;
		PrimeFaces.current().ajax().update(":form:dtcliente");
		PrimeFaces.current().ajax().update(":form:dtcompañia");
		PrimeFaces.current().ajax().update(":form:dtcarga");
	}
	
	
	
	
	public ClienteDto getClientDto() {
		return clientDto;
	}
	public void setClientDto(ClienteDto clientDto) {
		this.clientDto = clientDto;
	}
	public ClienteDto getSelectedClient() {
		return selectedClient;
	}
	public void setSelectedClient(ClienteDto selectedClient) {
		this.selectedClient = selectedClient;
	}
	public List<ClienteDto> getClientes() {
		clientes = clienteservice.getClientes();
		return clientes;
	}
	public void setClientes(List<ClienteDto> clientes) {
		this.clientes = clientes;
	}
	public CompanyDto getCompanydto() {
		return companydto;
	}
	public void setCompanydto(CompanyDto companydto) {
		this.companydto = companydto;
	}
	public CompanyDto getSelectedCompany() {
		return selectedCompany;
	}
	public void setSelectedCompany(CompanyDto selectedCompany) {
		this.selectedCompany = selectedCompany;
	}
	public List<CompanyDto> getCompanys() {
		companys = companyservice.getCompanys();
		return companys;
	}
	public void setCompanys(List<CompanyDto> companys) {
		this.companys = companys;
	}
	public ClienteService getClienteservice() {
		return clienteservice;
	}
	public void setClienteservice(ClienteService clienteservice) {
		this.clienteservice = clienteservice;
	}
	public CompanyService getCompanyservice() {
		return companyservice;
	}
	public void setCompanyservice(CompanyService companyservice) {
		this.companyservice = companyservice;
	}

	public CargaDto getCargaDto() {
		return cargaDto;
	}

	public void setCargaDto(CargaDto cargaDto) {
		this.cargaDto = cargaDto;
	}

	public CargaDto getSelectedCarga() {
		return selectedCarga;
	}

	public void setSelectedCarga(CargaDto selectedCarga) {
		this.selectedCarga = selectedCarga;
	}

	public List<CargaDto> getCargas() {
		return cargas;
	}

	public void setCargas(List<CargaDto> cargas) {
		this.cargas = cargas;
	}

	public CargaService getCargaservice() {
		return cargaservice;
	}

	public void setCargaservice(CargaService cargaservice) {
		this.cargaservice = cargaservice;
	}

	public float getCombustible_req() {
		return combustible_req;
	}

	public void setCombustible_req(float combustible_req) {
		this.combustible_req = combustible_req;
	}
	

	public double getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(double importe_total) {
		this.importe_total = importe_total;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}


	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}
	
	
}
