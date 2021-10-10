package cu.edu.cujae.backend.core.dto;

import java.sql.Date;

public class CasillaDto {
	
	private String idCasilla;
	private boolean disponibilidad;
	private String idCarga;
	private String cod_paquete;
	private String idAlmacen;
	private String cod_estante;
	private String idPiso;
	private String cod_empresa;
	private Date fecha_inicio_mant;
	private Date fecha_final_mant;
	private boolean mantActivo;
					

	
	
	public CasillaDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CasillaDto(String idAlmacen, String idCasilla, String cod_emp, String cod_estante, String idPiso, boolean mantActivo,
			Date fecha_final_mant, Date fecha_inicio_mant, boolean disponibilidad, String idCarga, String cod_paquete ){
		this.idAlmacen = idAlmacen;
		this.idCasilla = idCasilla;
		this.cod_empresa = cod_emp;
		this.cod_estante = cod_estante;
		this.idPiso = idPiso;
		this.mantActivo = mantActivo;
		this.fecha_final_mant = fecha_final_mant;
		this.fecha_inicio_mant = fecha_inicio_mant;
		this.disponibilidad = disponibilidad;
		this.idCarga = idCarga;
		this.cod_paquete = cod_paquete;
	}
	
	

	public String getIdAlmacen() {
		return idAlmacen;
	}



	public void setIdAlmacen(String idAlmacen) {
		this.idAlmacen = idAlmacen;
	}



	public String getCod_estante() {
		return cod_estante;
	}



	public void setCod_estante(String cod_estante) {
		this.cod_estante = cod_estante;
	}



	public String getIdPiso() {
		return idPiso;
	}



	public void setIdPiso(String idPiso) {
		this.idPiso = idPiso;
	}



	public String getCod_empresa() {
		return cod_empresa;
	}



	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}



	public boolean isMantActivo() {
		return mantActivo;
	}
	public void setMantActivo(boolean mantActivo) {
		this.mantActivo = mantActivo;
	}
	public Date getFecha_inicio_mant() {
		return fecha_inicio_mant;
	}
	public void setFecha_inicio_mant(Date fecha_inicio_mant) {
		this.fecha_inicio_mant = fecha_inicio_mant;
	}
	public Date getFecha_final_mant() {
		return fecha_final_mant;
	}
	public void setFecha_final_mant(Date fecha_final_mant) {
		this.fecha_final_mant = fecha_final_mant;
	}
	public String getIdCasilla() {
		return idCasilla;
	}
	public void setIdCasilla(String idCasilla) {
		this.idCasilla = idCasilla;
	}
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
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

}
