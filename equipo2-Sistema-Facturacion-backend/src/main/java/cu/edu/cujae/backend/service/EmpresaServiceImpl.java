package cu.edu.cujae.backend.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.EmpresaDto;
import cu.edu.cujae.backend.core.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public EmpresaDto getEmpresa() throws SQLException {
		EmpresaDto emp = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM \"Empresa\"");
			while(rs.next())
			{
				emp = new EmpresaDto(
						rs.getString("email_emp"), 
						rs.getString("nomb_sec_gen"), 
						rs.getString("nomb_jefe_dep_recursos_hum"), 
						rs.getString("nomb_resp_dep_cont"), 
						rs.getString("nomb_dir_gen"), 
						rs.getString("direccion_postal"), 
						rs.getString("telefono_emp"), 
						rs.getString("nomb_emp"));
			}
			}
		return emp;
	}

}
