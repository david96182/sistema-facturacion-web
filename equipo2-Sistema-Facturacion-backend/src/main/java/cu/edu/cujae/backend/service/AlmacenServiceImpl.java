package cu.edu.cujae.backend.service;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.AlmacenDto;

import cu.edu.cujae.backend.core.service.AlmacenService;

@Service
public class AlmacenServiceImpl implements AlmacenService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
		@Override
		public void createAlmacen(AlmacenDto almacen) throws SQLException
		{
			
			try(Connection conn = jdbcTemplate.getDataSource().getConnection()){	
				CallableStatement cs = conn.prepareCall("{call \"almacen_insert\"(?,?,?,?,?,?)}");
				cs.setString(1, almacen.getIdAlmacen());
				cs.setString(2,almacen.getCod_emp());
				cs.setBoolean(3, almacen.isMantActivo());
				cs.setDate(4, almacen.getFecha_inicio_mant());
				cs.setDate(5, almacen.getFecha_final_mant());
				cs.setBoolean(6, almacen.isCond_refrigeracion());
				cs.executeUpdate();
			}
			
		}


		@Override
		public void updateAlmacen(AlmacenDto almacen) throws SQLException { 
			
			try(Connection conn = jdbcTemplate.getDataSource().getConnection()){

			CallableStatement cs = conn.prepareCall("{call \"almacen_update\"(?,?,?,?,?,?)}");
			
			cs.setString(1, almacen.getIdAlmacen());
			cs.setString(2,almacen.getCod_emp());
			cs.setBoolean(3, almacen.isMantActivo());
			cs.setDate(4, almacen.getFecha_inicio_mant());
			cs.setDate(5, almacen.getFecha_final_mant());
			cs.setBoolean(6, almacen.isCond_refrigeracion());
			cs.executeUpdate();
					
			}	
		}
	


		@Override
		public List<AlmacenDto> listAlmacenes() throws SQLException {

			List<AlmacenDto> almacenes = new ArrayList<AlmacenDto>();	
			
			try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			
				ResultSet result = conn.createStatement().executeQuery("SELECT * FROM \"Almacen\"");
				while(result.next())
				{
					almacenes.add(new AlmacenDto(
							result.getString("cod_almacen"),
							result.getString("cod_emp"),
							result.getBoolean("mant_activo"),
							result.getDate("fecha_mant_i"),
							result.getDate("fecha_mant_f"),
							result.getBoolean("condicoiones_refrigeracion_alm")));
				}
			}
				return almacenes;
		}


		@Override
		public AlmacenDto getAlmacenById(String idAlmacen) throws SQLException {
			
			try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"Almacen\" WHERE \"cod_almacen\" = ?");
			statement.setString(1, idAlmacen);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next() ? new AlmacenDto(
					resultSet.getString("cod_almacen"),
					resultSet.getString("cod_emp"),
					resultSet.getBoolean("mant_activo"),
					resultSet.getDate("fecha_mant_i"),
					resultSet.getDate("fecha_mant_f"),
					resultSet.getBoolean("condicoiones_refrigeracion_alm"))
			:null;
			}
		}


		@Override
		public void deleteAlmacen(String idAlmacen, String cod_emp) throws SQLException {
			
			try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

			CallableStatement cs = conn.prepareCall("{call \"almacen_delete\"(?,?)}");
			cs.setString(1, idAlmacen);
			cs.setString(2, cod_emp);
			cs.executeUpdate();
			}
		}


	

}
