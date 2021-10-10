package cu.edu.cujae.backend.core.dto;

import java.sql.Date;
import java.util.ArrayList;




public class AlmacenDto {
	
	private String idAlmacen;
	private String cod_emp;
	private boolean mantActivo;
	private Date fecha_inicio_mant;
	private Date fecha_final_mant;
	private boolean cond_refrigeracion;
	private ArrayList<EstanteDto> estantes;
	
	public AlmacenDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public AlmacenDto(String idAlmacen, String cod_emp, boolean mantActivo,
					Date fecha_inicio_mant, Date fecha_final_mant, boolean cond_refrigeracion)
	{
		this.idAlmacen = idAlmacen;
		this.cod_emp = cod_emp;
		this.mantActivo = mantActivo;
		this.fecha_inicio_mant = fecha_inicio_mant;
		this.fecha_final_mant = fecha_final_mant;
		this.cond_refrigeracion = cond_refrigeracion;
		this.estantes = new ArrayList<EstanteDto>();
	}
	

	public String getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(String idAlmacen) {
		this.idAlmacen = idAlmacen;
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

	public boolean isCond_refrigeracion() {
		return cond_refrigeracion;
	}

	public void setCond_refrigeracion(boolean cond_refrigeracion) {
		this.cond_refrigeracion = cond_refrigeracion;
	}

	public ArrayList<EstanteDto> getEstantes() {
		return estantes;
	}

	public void setEstantes(ArrayList<EstanteDto> estantes) {
		this.estantes = estantes;
}

}
