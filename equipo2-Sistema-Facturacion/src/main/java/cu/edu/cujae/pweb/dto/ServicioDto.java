package cu.edu.cujae.pweb.dto;

public class ServicioDto {
	
	private String idCarga;
	private String email_cliente;
	private String tipo_movimiento;
	private double combustible_requerido;
	private double importe_total;
	private String cod_empresa;
	private String nomb_comp;
	private double tarifa_aforo;
	private String cod_servicio;
	
	public ServicioDto() {
		
	}
	public ServicioDto(String idCarga, String email_cliente,
			String tipo_movimiento, double combustible_requerido,
			double importe_total, String cod_empresa, String nomb_comp,
			double tarifa_aforo, String cod_servicio) {
	
		this.idCarga = idCarga;
		this.email_cliente = email_cliente;
		this.tipo_movimiento = tipo_movimiento;
		this.combustible_requerido = combustible_requerido;
		this.importe_total = importe_total;
		this.cod_empresa = cod_empresa;
		this.nomb_comp = nomb_comp;
		this.tarifa_aforo = tarifa_aforo;
		this.cod_servicio = cod_servicio;
	}

	public String getIdCarga() {
		return idCarga;
	}

	public void setIdCarga(String idCarga) {
		this.idCarga = idCarga;
	}

	public String getEmail_cliente() {
		return email_cliente;
	}

	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}

	public String getTipo_movimiento() {
		return tipo_movimiento;
	}

	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}

	public double getCombustible_requerido() {
		return combustible_requerido;
	}

	public void setCombustible_requerido(double combustible_requerido) {
		this.combustible_requerido = combustible_requerido;
	}

	public double getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(double importe_total) {
		this.importe_total = importe_total;
	}

	public String getCod_empresa() {
		return cod_empresa;
	}

	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}

	public String getNomb_comp() {
		return nomb_comp;
	}

	public void setNomb_comp(String nomb_comp) {
		this.nomb_comp = nomb_comp;
	}

	public double getTarifa_aforo() {
		return tarifa_aforo;
	}

	public void setTarifa_aforo(double tarifa_aforo) {
		this.tarifa_aforo = tarifa_aforo;
	}

	public String getCod_servicio() {
		return cod_servicio;
	}

	public void setCod_servicio(String cod_servicio) {
		this.cod_servicio = cod_servicio;
	}

	
}
