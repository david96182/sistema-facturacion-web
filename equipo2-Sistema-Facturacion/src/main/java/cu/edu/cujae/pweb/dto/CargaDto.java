package cu.edu.cujae.pweb.dto;

import java.util.Date;

public class CargaDto {

	private String idCarga;
	private String nombCarga;
	private String tipoProducto;
	private boolean condRefrigeracion;
	private Date fechaVencimiento;
	private String tipoEmpaquetado;
	private double pesoUnidad_Empaquetado;
	private Date fechaIngreso;
	private Date fechaSalida;
	private Date horaSalida_Plan;
	private int cantPaquetes;
	
	public CargaDto() {
		
	}
	
	public CargaDto(String idCarga, String tipoEmpaquetado, Date fechaingreso2, int cantPaquetes,String nombCarga, 
			String tipoProducto, Date fechaVencimiento2, Date fechaSalida2, double pesoUnidad_Empaquetado, 
			boolean condRefrigeracion, Date fechasalidaplan) 
	{
		this.idCarga = idCarga;
		this.nombCarga = nombCarga;
		this.tipoProducto = tipoProducto;
		this.condRefrigeracion = condRefrigeracion;
		this.fechaVencimiento = fechaVencimiento2;
		this.tipoEmpaquetado = tipoEmpaquetado;
		this.pesoUnidad_Empaquetado = pesoUnidad_Empaquetado;
		this.fechaIngreso = fechaingreso2;
		this.fechaSalida = fechaSalida2;
		this.horaSalida_Plan = fechasalidaplan;
		this.cantPaquetes = cantPaquetes;
	}

	public String getIdCarga() {
		return idCarga;
	}

	public void setIdCarga(String idCarga) {
		this.idCarga = idCarga;
	}

	public String getNombCarga() {
		return nombCarga;
	}

	public void setNombCarga(String nombCarga) {
		this.nombCarga = nombCarga;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public boolean isCondRefrigeracion() {
		return condRefrigeracion;
	}

	public void setCondRefrigeracion(boolean condRefrigeracion) {
		this.condRefrigeracion = condRefrigeracion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTipoEmpaquetado() {
		return tipoEmpaquetado;
	}

	public void setTipoEmpaquetado(String tipoEmpaquetado) {
		this.tipoEmpaquetado = tipoEmpaquetado;
	}

	public double getPesoUnidad_Empaquetado() {
		return pesoUnidad_Empaquetado;
	}

	public void setPesoUnidad_Empaquetado(double pesoUnidad_Empaquetado) {
		this.pesoUnidad_Empaquetado = pesoUnidad_Empaquetado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Date getHoraSalida_Plan() {
		return horaSalida_Plan;
	}

	public void setHoraSalida_Plan(Date horaSalida_Plan) {
		this.horaSalida_Plan = horaSalida_Plan;
	}

	public int getCantPaquetes() {
		return cantPaquetes;
	}

	public void setCantPaquetes(int cantPaquetes) {
		this.cantPaquetes = cantPaquetes;
	}
	
}