package cu.edu.cujae.pweb.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.AlmacenDto;
import cu.edu.cujae.pweb.dto.CasillaDto;
import cu.edu.cujae.pweb.dto.EstanteDto;
import cu.edu.cujae.pweb.dto.PisoDto;
import cu.edu.cujae.pweb.service.AlmacenService;
import cu.edu.cujae.pweb.service.CasillaService;
import cu.edu.cujae.pweb.service.EstanteService;
import cu.edu.cujae.pweb.service.PisoService;
import cu.edu.cujae.pweb.utils.JsfUtils;



//import org.springframework.stereotype.Component;

@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped 
public class AlmacenBean {

	private AlmacenDto almacen;
	private AlmacenDto selectedAlmacen;
	private List<AlmacenDto> almacenes;
	private boolean creado;
	private boolean creadoEstante;
	private boolean creadoPiso;
	
	@Autowired
	private AlmacenService almacenservice;
	@Autowired
	private EstanteService estanteservice;
	@Autowired
	private PisoService pisoservice;
	@Autowired
	private CasillaService casillaservice;
	
	
	private EstanteDto selectedEstante;
	private List<EstanteDto> estantes;
	private PisoDto selectedPiso;
	private List<PisoDto> pisos;
	private CasillaDto selectedCasilla;
	private List<CasillaDto> casillas;
	
	
	
	
	public AlmacenBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean isCreado() {
		return creado;
	}

	public void setCreado(boolean creado) {
		this.creado = creado;
	}

	public boolean isCreadoEstante() {
		return creadoEstante;
	}

	public void setCreadoEstante(boolean creadoEstante) {
		this.creadoEstante = creadoEstante;
	}

	public boolean isCreadoPiso() {
		return creadoPiso;
	}

	public void setCreadoPiso(boolean creadoPiso) {
		this.creadoPiso = creadoPiso;
	}

	public void newCasilla() {
		if(selectedPiso == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado un piso para agregarle casillas");
			PrimeFaces.current().executeScript("PF('casillaDialog').hide()");
		}
		else {
		this.selectedCasilla = new CasillaDto();
		}
	}
	public void newEstante() {
		
		if(selectedAlmacen == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado un almac?n para agregarle estantes");
			PrimeFaces.current().executeScript("PF('estanteDialog').hide()");
		}
		else {
			this.selectedEstante = new EstanteDto();
			this.creadoEstante = true;
		}		
	}
	
	public void newPiso() {
		
		if(selectedEstante == null) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "No ha seleccionado un estante para agregarle pisos");
			PrimeFaces.current().executeScript("PF('pisoDialog').hide()");
		}
		else {
		
		this.selectedPiso = new PisoDto();
		this.creadoPiso = true;
		}
	}
	public void newAlmacen() {
		this.selectedAlmacen = new AlmacenDto();
		this.creado = true;
	}
	
	public void openForEdit() {
		
	}
	
	public void saveAlmacen() {
		
			 
		 if (creado) {
			 	//this.selectedAlmacen = new AlmacenDto();
	            this.selectedAlmacen.setIdAlmacen(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
	            this.selectedAlmacen.setCod_emp("1");
	           
	              //register almacen
	           almacenservice.createAlmacen(this.selectedAlmacen);
	           creado = false;
	            
	            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Almac?n Creado"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
	        }
	        else {
	        	//register almacen
	            almacenservice.updateAlmacen(this.selectedAlmacen);
	            
	            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Almac?n Editado");
	        }
	
	        //load datatable again with new values
	        almacenes = almacenservice.getAlmacenes();
	        
	        PrimeFaces.current().executeScript("PF('almacenDialog').hide()");
	        PrimeFaces.current().ajax().update("formAlm:dt-almacenes");
		
	}
	
	public void deleteAlmacen() {
		
		try {
    		//delete almacen
    		almacenservice.deleteAlmacen(this.selectedAlmacen.getIdAlmacen(), this.selectedAlmacen.getCod_emp());
            this.selectedAlmacen = null;
            
            //load datatable again with new values
            almacenes = almacenservice.getAlmacenes();
            
           JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Almac?n Eliminado");
            PrimeFaces.current().ajax().update("formAlm:dt-almacenes");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
		
	}
	
	public void deleteEstante() {
		
		try {
    		//delete almacen
    		estanteservice.deleteEstante(this.selectedEstante.getCod_almacen(), this.selectedEstante.getCod_estante(),this.selectedEstante.getCod_emp());
            this.selectedEstante = null;
            
            //load datatable again with new values
            estantes = estanteservice.getEstantesByAlmacenId(this.selectedAlmacen.getIdAlmacen());
            
           JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Estante Eliminado");
            PrimeFaces.current().ajax().update("formEst:dt-estantes");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
		
	}
	
	public void saveEstante() {
		
		if (creadoEstante) {
            this.selectedEstante.setCod_estante(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.selectedEstante.setCod_emp("1");
            this.selectedEstante.setCod_almacen(this.selectedAlmacen.getIdAlmacen());
           
              //register almacen
           estanteservice.createEstante(this.selectedEstante);
           creadoEstante = false;
            
            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Estante Creado"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
        	//register almacen
            estanteservice.updateEstante(this.selectedEstante);
            
            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Estante Editado");
        }

        //load datatable again with new values
		estantes = estanteservice.getEstantesByAlmacenId(this.selectedAlmacen.getIdAlmacen());
        
        
        PrimeFaces.current().executeScript("PF('estanteDialog').hide()");
        PrimeFaces.current().ajax().update("formEst:dt-estantes");
		
	}
	public void deletePiso(){
		
		try {
    		//delete almacen
    		pisoservice.deletePiso(this.selectedPiso.getCod_almacen(), this.selectedPiso.getCod_emp(), this.selectedPiso.getCod_estante(),this.selectedPiso.getNumPiso());
            this.selectedPiso = null;
            
            //load datatable again with new values
            pisos = pisoservice.getPisosByEstanteId(this.selectedAlmacen.getIdAlmacen());
            
           JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Piso Eliminado");
            PrimeFaces.current().ajax().update("formPiso:dt-pisos");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
		
		
	}
	public void savePiso() {
		
		 if (creadoPiso) {
			 	//this.selectedAlmacen = new AlmacenDto();
	            this.selectedPiso.setNumPiso(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
	            this.selectedPiso.setCod_emp("1");
	            this.selectedPiso.setCod_almacen(selectedEstante.getCod_almacen());
	            this.selectedPiso.setCod_estante(selectedEstante.getCod_estante());
	           
	              //register almacen
	           pisoservice.createPiso(this.selectedPiso);
	           creadoPiso = false;
	            
	            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Piso Creado"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
	        }
	        else {
	        	//register almacen
	            pisoservice.updatePiso(this.selectedPiso);
	            
	            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Piso Editado");
	        }
	
	        //load datatable again with new values
	        pisos = pisoservice.getPisosByEstanteId(selectedEstante.getCod_estante());
	        
	        PrimeFaces.current().executeScript("PF('pisoDialog').hide()");
	        PrimeFaces.current().ajax().update("formPiso:dt-pisos");
		
	}
	
	public void deleteCasilla() {
		
		try {
    		//delete almacen
    		casillaservice.deleteCasilla(this.selectedCasilla.getIdAlmacen(), this.selectedCasilla.getIdCasilla(), this.selectedCasilla.getCod_empresa(), this.selectedCasilla.getCod_estante(), this.selectedCasilla.getIdPiso());
            this.selectedCasilla = null;
            
            //load datatable again with new values
            casillas = casillaservice.getCasillasByPisoId(this.selectedPiso.getNumPiso());
            
           JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Casilla Eliminado");
            PrimeFaces.current().ajax().update("formCasilla:dt-casillas");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessage(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
	}
	
	public void saveCasilla() {
		
		if (this.selectedCasilla.getIdCasilla() == null) {
            this.selectedCasilla.setIdCasilla(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            
            this.selectedCasilla.setCod_empresa("1");
            this.selectedCasilla.setIdAlmacen(selectedPiso.getCod_almacen());
            this.selectedCasilla.setCod_estante(selectedPiso.getCod_estante());
            this.selectedCasilla.setIdPiso(selectedPiso.getNumPiso());
            
            
            //register user
            casillaservice.createCasilla(this.selectedCasilla);
            
            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Casilla Creada"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
        	//register user
            casillaservice.updateCasilla(this.selectedCasilla);
            JsfUtils.addMessage(null, FacesMessage.SEVERITY_INFO, "Casilla Editada");
        }

        //load datatable again with new values
		casillas = casillaservice.getCasillasByPisoId(selectedPiso.getNumPiso());
        
        
        PrimeFaces.current().executeScript("PF('casillaDialog').hide()");
        PrimeFaces.current().ajax().update("formCasilla:dt-casillas");
		
	}
	
	
	public void onRowSelectAlmacenTable(SelectEvent<AlmacenDto> event){
		estantes = estanteservice.getEstantesByAlmacenId(String.valueOf(event.getObject().getIdAlmacen()));
		
		//this.selectedAlmacen = null;
		/*
		if(pisos.size() > 0 ) {
			pisos.clear();
		}
		if(casillas.size() > 0) {
			casillas.clear();
		}
		*/
	}
	
	public void onRowSelectEstanteTable(SelectEvent<EstanteDto> event){
		pisos = pisoservice.getPisosByEstanteId(String.valueOf(event.getObject().getCod_estante()));
		
		//casillas.clear();
	}
	
	public void onRowSelectPisoTable(SelectEvent<PisoDto> event){
		casillas = casillaservice.getCasillasByPisoId(String.valueOf(event.getObject().getNumPiso()));
	}
	
	public AlmacenDto getSelectedAlmacen() {
		return selectedAlmacen;
	}
	public void setSelectedAlmacen(AlmacenDto selectedAlmacen) {
		this.selectedAlmacen = selectedAlmacen;
	}
	
	public List<AlmacenDto> getAlmacenes() {
		almacenes = almacenservice.getAlmacenes();
		return almacenes;
	}
	public void setAlmacenes(List<AlmacenDto> almacenes) {
		this.almacenes = almacenes;
	}

	public EstanteDto getSelectedEstante() {
		return selectedEstante;
	}

	public void setSelectedEstante(EstanteDto selectedEstante) {
		this.selectedEstante = selectedEstante;
	}

	public List<EstanteDto> getEstantes() {
		
		return estantes;
	}

	public void setEstantes(List<EstanteDto> estantes) {
		this.estantes = estantes;
	}

	public PisoDto getSelectedPiso() {
		return selectedPiso;
	}

	public void setSelectedPiso(PisoDto selectedPiso) {
		this.selectedPiso = selectedPiso;
	}

	public List<PisoDto> getPisos() {
	
		return pisos;
	}

	public void setPisos(List<PisoDto> pisos) {
		this.pisos = pisos;
	}

	public CasillaDto getSelectedCasilla() {
		return selectedCasilla;
	}

	public void setSelectedCasilla(CasillaDto selectedCasilla) {
		this.selectedCasilla = selectedCasilla;
	}

	public List<CasillaDto> getCasillas() {
	
		return casillas;
	}

	public void setCasillas(List<CasillaDto> casillas) {
		this.casillas = casillas;
	}

	public AlmacenService getAlmacenservice() {
		return almacenservice;
	}

	public void setAlmacenservice(AlmacenService almacenservice) {
		this.almacenservice = almacenservice;
	}

	public EstanteService getEstanteservice() {
		return estanteservice;
	}

	public void setEstanteservice(EstanteService estanteservice) {
		this.estanteservice = estanteservice;
	}

	public PisoService getPisoservice() {
		return pisoservice;
	}

	public void setPisoservice(PisoService pisoservice) {
		this.pisoservice = pisoservice;
	}

	public CasillaService getCasillaservice() {
		return casillaservice;
	}

	public void setCasillaservice(CasillaService casillaservice) {
		this.casillaservice = casillaservice;
	}

	
}

