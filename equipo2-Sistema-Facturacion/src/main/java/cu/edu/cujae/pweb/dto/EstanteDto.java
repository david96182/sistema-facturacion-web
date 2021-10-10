package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.Date;

public class EstanteDto {
	private String cod_almacen;
	private String cod_estante;
	private String cod_emp;
	private boolean mantActivo;
	private Date fecha_inicio_mant;
	private Date fecha_final_mant;
	private ArrayList<PisoDto> pisos;
	
	public EstanteDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public EstanteDto(String cod_almacen, String cod_estante, String cod_emp,
			boolean mant_activo, Date fecha_mant_i, Date fecha_mant_f) 
	{
		this.cod_almacen = cod_almacen;
		this.cod_emp = cod_emp;
		this.cod_estante = cod_estante;
		this.mantActivo = mant_activo;
		this.fecha_inicio_mant = fecha_mant_i;
		this.fecha_final_mant = fecha_mant_f;
		pisos = new ArrayList<PisoDto>();
	}

	public String getCod_almacen() {
		return cod_almacen;
	}

	public void setCod_almacen(String cod_almacen) {
		this.cod_almacen = cod_almacen;
	}

	public String getCod_estante() {
		return cod_estante;
	}

	public void setCod_estante(String cod_estante) {
		this.cod_estante = cod_estante;
	}

	public String getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(String cod_emp) {
		this.cod_emp = cod_emp;
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

	public ArrayList<PisoDto> getPisos() {
		return pisos;
	}

	public void setPisos(ArrayList<PisoDto> pisos) {
		this.pisos = pisos;
	}
}
