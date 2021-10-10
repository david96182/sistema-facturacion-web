package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.EmpresaDto;
import cu.edu.cujae.pweb.service.EmpresaService;

@Component
@ManagedBean
@ViewScoped
public class EmpresaBean {

	private EmpresaDto empresa;
	
	@Autowired EmpresaService empresaservice;
	
	public EmpresaBean() {
		
	}

	public EmpresaDto getEmpresa() {
		empresa = empresaservice.getEmpresa();
		return empresa;
	}

	public void setEmpresa(EmpresaDto empresa) {
		this.empresa = empresa;
	}

	public EmpresaService getEmpresaservice() {
		return empresaservice;
	}

	public void setEmpresaservice(EmpresaService empresaservice) {
		this.empresaservice = empresaservice;
	}
	
	
}
