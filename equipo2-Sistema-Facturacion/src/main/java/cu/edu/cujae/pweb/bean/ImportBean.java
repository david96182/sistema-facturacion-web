package cu.edu.cujae.pweb.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
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
public class ImportBean {

	private ClienteDto clientDto;
	private ClienteDto selectedClient;
	private List<ClienteDto> clientes;

	private CompanyDto companydto;
	private CompanyDto selectedCompany;
	private List<CompanyDto> companys;

	private CargaDto cargadto;

	private ServicioDto serviciodto;

	private String nombreCarga;
	private String tipoProducto;
	private boolean cond_ref;
	private int cant_paq;
	private float peso_unidad_emp;
	private String tipo_empaquetado;
	private Date fecha_vencimiento;
	private float combustible_req;
	private Date fecha_hora_ingreso;
	private Date fecha_hora_salida_plan;
	private float importe_total = 0;
	public float pesoform;

	private boolean si = false;

	private Date today = new Date();

	@Autowired
	private ClienteService clienteservice;

	@Autowired
	private CargaService cargaservice;

	@Autowired
	private CompanyService companyservice;

	@Autowired
	private ServicioService servicioservice;

	public ImportBean() {

	}


	public void savePeso() {
		setPesoform(peso_unidad_emp);

	}

	public void calcImporte() {

		if(combustible_req > 0) {
			
			importe_total = 0;

			importe_total += 3*combustible_req;
			importe_total += pesoform + 25.25;
			setImporte_total(importe_total);
			 
		}
		

	}

	public void registrarImport() {
		boolean correct = true;
		if(this.selectedClient == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado un cliente");
			correct = false;
		}
		if(this.selectedCompany == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado una compañia");
			correct = false;
		}
		if(correct) {
			try {
				int cod = cargaservice.getCargas().size();
				String codc = "0"+cod;
				CargaDto carg = new CargaDto(codc, tipo_empaquetado, fecha_hora_ingreso, cant_paq, nombreCarga, tipoProducto, fecha_vencimiento, null, peso_unidad_emp, cond_ref, fecha_hora_salida_plan);
				cargaservice.createCarga(carg);

				int co = servicioservice.getServicios().size();
				String cods = "0"+co;
				ServicioDto serv = new ServicioDto(codc, selectedClient.getEmail(), "importacion", combustible_req, importe_total, "1", selectedCompany.getNameCompany(), 1, cods);
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
		nombreCarga = null;
		tipoProducto = null;
		cond_ref = false;
		cant_paq = 0;
		peso_unidad_emp = 0;
		tipo_empaquetado = null;
		fecha_vencimiento = null;
		combustible_req = 0;
		fecha_hora_ingreso = null;
		fecha_hora_salida_plan = null;
		importe_total = 0;
		selectedClient = null;
		PrimeFaces.current().ajax().update(":form:dtcliente");
		PrimeFaces.current().ajax().update(":form:compañia");
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

	public CargaDto getCargadto() {
		return cargadto;
	}

	public void setCargadto(CargaDto cargadto) {
		this.cargadto = cargadto;
	}

	public ServicioDto getServiciodto() {
		return serviciodto;
	}

	public void setServiciodto(ServicioDto serviciodto) {
		this.serviciodto = serviciodto;
	}

	public ClienteService getClienteservice() {
		return clienteservice;
	}

	public void setClienteservice(ClienteService clienteservice) {
		this.clienteservice = clienteservice;
	}

	public CargaService getCargaservice() {
		return cargaservice;
	}

	public void setCargaservice(CargaService cargaservice) {
		this.cargaservice = cargaservice;
	}

	public CompanyService getCompanyservice() {
		return companyservice;
	}

	public void setCompanyservice(CompanyService companyservice) {
		this.companyservice = companyservice;
	}

	public ServicioService getServicioservice() {
		return servicioservice;
	}

	public void setServicioservice(ServicioService servicioservice) {
		this.servicioservice = servicioservice;
	}

	public String getNombreCarga() {
		return nombreCarga;
	}

	public void setNombreCarga(String nombreCarga) {
		this.nombreCarga = nombreCarga;
	}

	public String gettipoProducto() {
		return tipoProducto;
	}

	public void settipoProducto(String nombreProducto) {
		this.tipoProducto = nombreProducto;
	}

	public boolean isCond_ref() {
		return cond_ref;
	}

	public void setCond_ref(boolean cond_ref) {
		this.cond_ref = cond_ref;
	}

	public int getCant_paq() {
		return cant_paq;
	}

	public void setCant_paq(int cant_paq) {
		this.cant_paq = cant_paq;
	}

	public float getPeso_unidad_emp() {
		return peso_unidad_emp;
	}

	public void setPeso_unidad_emp(float peso_unidad_emp) {
		this.peso_unidad_emp = peso_unidad_emp;
	}

	public String getTipo_empaquetado() {
		return tipo_empaquetado;
	}

	public void setTipo_empaquetado(String tipo_empaquetado) {
		this.tipo_empaquetado = tipo_empaquetado;
	}

	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public float getCombustible_req() {
		return combustible_req;
	}

	public void setCombustible_req(float combustible_req) {
		this.combustible_req = combustible_req;
	}

	public Date getFecha_hora_ingreso() {
		return fecha_hora_ingreso;
	}

	public void setFecha_hora_ingreso(Date fecha_hora_ingreso) {
		this.fecha_hora_ingreso = fecha_hora_ingreso;
	}

	public Date getFecha_hora_salida_plan() {
		return fecha_hora_salida_plan;
	}

	public void setFecha_hora_salida_plan(Date fecha_hora_salida_plan) {
		this.fecha_hora_salida_plan = fecha_hora_salida_plan;
	}

	public float getImporte_total() {
		
		return importe_total;
	}

	public void setImporte_total(float importe_total) {
		this.importe_total = importe_total;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}



	public Date getToday() {
		return today;
	}



	public void setToday(Date today) {
		this.today = today;
	}

	public float getPesoform() {
		return pesoform;
	}

	public void setPesoform(float pesoform) {
		this.pesoform = pesoform;
	}


	public boolean isSi() {
		return si;
	}


	public void setSi(boolean si) {
		this.si = si;
	}







}
