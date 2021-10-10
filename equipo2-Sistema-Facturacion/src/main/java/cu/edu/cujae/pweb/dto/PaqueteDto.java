package cu.edu.cujae.pweb.dto;

public class PaqueteDto {
	
	private String idCarga;
	private String cod_paquete;
	private String tipo_paquete;
	
	public PaqueteDto() {
		
	}
	
	public PaqueteDto(String idCarga, String cod_paquete, String tipo_paquete) {
		
		this.idCarga = idCarga;
		this.cod_paquete = cod_paquete;
		this.tipo_paquete = tipo_paquete;
	}

	public String getIdCarga() {
		return idCarga;
	}

	public void setIdCarga(String idCarga) {
		this.idCarga = idCarga;
	}

	public String getCod_paquete() {
		return cod_paquete;
	}

	public void setCod_paquete(String cod_paquete) {
		this.cod_paquete = cod_paquete;
	}

	public String getTipo_paquete() {
		return tipo_paquete;
	}

	public void setTipo_paquete(String tipo_paquete) {
		this.tipo_paquete = tipo_paquete;
	}
	
}
