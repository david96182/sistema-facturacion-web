package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.ClienteDto;
import cu.edu.cujae.pweb.service.ClienteService;
import cu.edu.cujae.pweb.utils.JsfUtils;



@Component
@ManagedBean
@ViewScoped
public class ListaClienteBean {

	private ClienteDto clientDto;
	private ClienteDto selectedUser;
	private List<ClienteDto> clientes;

	@Autowired 
	private ClienteService clientservice;

	public ListaClienteBean() {

	}


	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedUser = new ClienteDto();

	}

	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {

	}
	public void openForView() {
		
	}

	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveUser() {
		ClienteDto cli = clientservice.getClienteById(selectedUser.getEmail());
		if (cli == null) {

			//register cliente
			clientservice.createCliente(this.selectedUser);

			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_cliente_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
		}
		else {
			//register user
			clientservice.updateCliente(this.selectedUser);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_cliente_edited");
		}

		//load datatable again with new values
		clientes = clientservice.getClientes();

		PrimeFaces.current().executeScript("PF('manageClientDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-clientes");
	}

	//Permite eliminar un usuario
	public void deleteUser() {
		try {
    		//delete user
    		clientservice.deleteCliente(this.selectedUser.getEmail());
            this.selectedUser = null;
            
            //load datatable again with new values
            clientes = clientservice.getClientes();
            
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_cliente_deleted");
            PrimeFaces.current().ajax().update("form:dt-clientes");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public ClienteDto getClientDto() {
		return clientDto;
	}

	public void setClientDto(ClienteDto clientDto) {
		this.clientDto = clientDto;
	}

	public ClienteDto getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(ClienteDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<ClienteDto> getClientes() {
		clientes = clientservice.getClientes();
		return clientes;
	}

	public void setClientes(List<ClienteDto> clientes) {
		this.clientes = clientes;
	}

	public ClienteService getClientservice() {
		return clientservice;
	}

	public void setClientservice(ClienteService clientservice) {
		this.clientservice = clientservice;
	}





}
