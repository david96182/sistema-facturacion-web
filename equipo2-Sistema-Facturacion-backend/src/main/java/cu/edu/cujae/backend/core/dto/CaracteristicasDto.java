package cu.edu.cujae.backend.core.dto;

public class CaracteristicasDto {

	private Long id;
	private String roleName;
	private String description;
	
	public CaracteristicasDto() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public CaracteristicasDto(Long id, String roleName, String description) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
