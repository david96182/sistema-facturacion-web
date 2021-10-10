package cu.edu.cujae.backend.core.dto;

import java.sql.Date;
import java.util.ArrayList;




public class PisoDto {

	private String cod_almacen;
	private String cod_estante;
	private String cod_emp;
	private String numPiso;
	private boolean mantActivo;
	private Date fecha_inicio_mant;
	private Date fecha_final_mant;
	private ArrayList<CasillaDto> casillas;
	
	
	
	public PisoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PisoDto(String cod_almacen, String cod_emp , String cod_estante, String num_piso, boolean mantActivo,
			Date fecha_inicio_mant, Date fecha_final_mant) 
	{
			this.cod_almacen = cod_almacen;
			this.cod_emp = cod_emp;
			this.cod_estante = cod_estante;
			this.numPiso = num_piso;
			this.mantActivo = mantActivo;
			this.fecha_inicio_mant = fecha_inicio_mant;
			this.fecha_final_mant = fecha_final_mant;
			this.casillas = new ArrayList<CasillaDto>();
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

	public String getNumPiso() {
		return numPiso;
	}

	public void setNumPiso(String numPiso) {
		this.numPiso = numPiso;
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

	public ArrayList<CasillaDto> getCasillas() {
		return casillas;
	}

	public void setCasillas(ArrayList<CasillaDto> casillas) {
		this.casillas = casillas;
	}
	
}
