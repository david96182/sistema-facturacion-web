package cu.edu.cujae.pweb.dto;

public class ClienteDto {

	private String email;
	private String nombCliente;
	private String paisCliente;
	private String telefCliente;
	private String faxCliente;
	private String direccionCliente;
	private boolean clientePriorizado;
	private double tarifaAlmacenamiento;
	private double tarifaRefrigeracion;
	private double tarifaTransporte;
	private int antiguedad;
	private int annosServicio;
	private int annos_suplidos;
	private String tipo_cliente;
	
	public ClienteDto() {
		super();
	}
	public ClienteDto(String email, double tarifaTransporte, double tarifaRefrigeracion, double tarifaAlmacenamiento, 
			int antiguedad, String faxCliente, String paisCliente, String direccionCliente, String nombCliente, int annosServicio,
			String telefCliente, int annos_suplidos, boolean clientePriorizado, String tipo_cliente)
	{
		this.email = email;
		this.nombCliente = nombCliente;
		this.paisCliente = paisCliente;
		this.telefCliente = telefCliente;
		this.faxCliente = faxCliente;
		this.direccionCliente = direccionCliente;
		this.clientePriorizado = clientePriorizado;
		this.tarifaAlmacenamiento = tarifaAlmacenamiento;
		this.tarifaRefrigeracion = tarifaRefrigeracion;
		this.tarifaTransporte = tarifaTransporte;
		this.antiguedad = antiguedad;
		this.annos_suplidos = annos_suplidos;
		this.annosServicio = annosServicio;
		this.tipo_cliente = tipo_cliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombCliente() {
		return nombCliente;
	}

	public void setNombCliente(String nombCliente) {
		this.nombCliente = nombCliente;
	}

	public String getPaisCliente() {
		return paisCliente;
	}

	public void setPaisCliente(String paisCliente) {
		this.paisCliente = paisCliente;
	}

	public String getTelefCliente() {
		return telefCliente;
	}

	public void setTelefCliente(String telefCliente) {
		this.telefCliente = telefCliente;
	}

	public String getFaxCliente() {
		return faxCliente;
	}

	public void setFaxCliente(String faxCliente) {
		this.faxCliente = faxCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public boolean isClientePriorizado() {
		return clientePriorizado;
	}

	public void setClientePriorizado(boolean clientePriorizado) {
		this.clientePriorizado = clientePriorizado;
	}

	public double getTarifaAlmacenamiento() {
		return tarifaAlmacenamiento;
	}

	public void setTarifaAlmacenamiento(double tarifaAlmacenamiento) {
		this.tarifaAlmacenamiento = tarifaAlmacenamiento;
	}

	public double getTarifaRefrigeracion() {
		return tarifaRefrigeracion;
	}

	public void setTarifaRefrigeracion(double tarifaRefrigeracion) {
		this.tarifaRefrigeracion = tarifaRefrigeracion;
	}

	public int getAnnos_suplidos() {
		return annos_suplidos;
	}

	public void setAnnos_suplidos(int annos_suplidos) {
		this.annos_suplidos = annos_suplidos;
	}

	public double getTarifaTransporte() {
		return tarifaTransporte;
	}

	public void setTarifaTransporte(double tarifaTransporte) {
		this.tarifaTransporte = tarifaTransporte;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public int getAnnosServicio() {
		return annosServicio;
	}

	public void setAnnosServicio(int annosServicio) {
		this.annosServicio = annosServicio;
	}

	public String getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}
	
}
