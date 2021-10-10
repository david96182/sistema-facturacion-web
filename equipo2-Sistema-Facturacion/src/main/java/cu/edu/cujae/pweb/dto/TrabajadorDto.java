package cu.edu.cujae.pweb.dto;

import java.util.List;

public class TrabajadorDto {

	private String id;
	private String username;
	private String nombre;
	private String password;
	private String email;
	private String identification;
	private List<RoleDto> roles;
	
	public TrabajadorDto(String id, String username, String nombre, String password, String email, String identification,
			List<RoleDto> roles) {
		super();
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.password = password;
		this.email = email;
		this.identification = identification;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
	
}
