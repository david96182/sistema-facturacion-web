package cu.edu.cujae.pweb.dto;

public class EmpresaDto {
	private String email_emp;
	private String nomb_sec_gen;
	private String nomb_jefe_dep_recursos_hum;
	private String nomb_resp_dep_cont;
	private String nomb_dir_gen;
	private String direccion_postal;
	private String telefono_emp;
	private String nomb_emp;
	
	
	public EmpresaDto() {
		
	}
	
	public EmpresaDto(String email_emp, String nom_sec_gen, String nomb_jefe_dep_recursos_hum,
			String nomb_resp_dep_cont, String nomb_dir_gen, String direccion_postal, String telefono_emp,
			String nomb_emp) {
		super();
		this.email_emp = email_emp;
		this.nomb_sec_gen = nom_sec_gen;
		this.nomb_jefe_dep_recursos_hum = nomb_jefe_dep_recursos_hum;
		this.nomb_resp_dep_cont = nomb_resp_dep_cont;
		this.nomb_dir_gen = nomb_dir_gen;
		this.direccion_postal = direccion_postal;
		this.telefono_emp = telefono_emp;
		this.nomb_emp = nomb_emp;
	}
	
	public String getEmail_emp() {
		return email_emp;
	}
	public void setEmail_emp(String email_emp) {
		this.email_emp = email_emp;
	}
	public String getNom_sec_gen() {
		return nomb_sec_gen;
	}
	public void setNom_sec_gen(String nom_sec_gen) {
		this.nomb_sec_gen = nom_sec_gen;
	}
	public String getNomb_jefe_dep_recursos_hum() {
		return nomb_jefe_dep_recursos_hum;
	}
	public void setNomb_jefe_dep_recursos_hum(String nomb_jefe_dep_recursos_hum) {
		this.nomb_jefe_dep_recursos_hum = nomb_jefe_dep_recursos_hum;
	}
	public String getNomb_resp_dep_cont() {
		return nomb_resp_dep_cont;
	}
	public void setNomb_resp_dep_cont(String nomb_resp_dep_cont) {
		this.nomb_resp_dep_cont = nomb_resp_dep_cont;
	}
	public String getNomb_dir_gen() {
		return nomb_dir_gen;
	}
	public void setNomb_dir_gen(String nomb_dir_gen) {
		this.nomb_dir_gen = nomb_dir_gen;
	}
	public String getDireccion_postal() {
		return direccion_postal;
	}
	public void setDireccion_postal(String direccion_postal) {
		this.direccion_postal = direccion_postal;
	}
	public String getTelefono_emp() {
		return telefono_emp;
	}
	public void setTelefono_emp(String telefono_emp) {
		this.telefono_emp = telefono_emp;
	}
	public String getNomb_emp() {
		return nomb_emp;
	}
	public void setNomb_emp(String nomb_emp) {
		this.nomb_emp = nomb_emp;
	}
	
	
}
