package com.project.vehicle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.project.vehicle.model.requestVehicle;

@Component
public class requestDao {

	@Autowired
	private DataSource ds;
	@Autowired
	private JdbcTemplate template;

	public List<requestVehicle> getAllRequest(){
		
		String sql="SELECT * FROM requestvehicle where status='requested' ";
		return  template.query(sql, new requestMapper());
		
	}
	
	public List<requestVehicle> getAllRequestByUsername(String name){
		
	String sql="SELECT * FROM requestvehicle WHERE username=?";
	return  template.query(sql, new Object[] {name}, new requestMapper());
	}
	public int totalRequest() {
		String sql="SELECT COUNT(*) FROM requestvehicle";
		return template.queryForObject(sql, Integer.class);
		
	}
public int insertRequest(requestVehicle request) {
String sql="INSERT INTO requestvehicle(username,vehicleid,date,status) values(?,?,?,?)";	
return template.update(sql, new Object[] {request.getUsername(),request.getVehicleid(),request.getDate(),request.getStatus()});
}
public int updateStatus(int id) {
	
String sql="update requestvehicle set status='accepted' where rid=?";
return template.update(sql, new Object[] {id});

}
	
public static final class requestMapper implements RowMapper<requestVehicle>{

	@Override
	public requestVehicle mapRow(ResultSet rs, int rownum) throws SQLException {
		
		requestVehicle rv=new requestVehicle();
		rv.setId(rs.getInt("rid"));
		rv.setUsername(rs.getString("username"));
		rv.setVehicleid(rs.getInt("vehicleid"));
		rv.setDate(rs.getDate("date"));
		rv.setStatus(rs.getString("status"));
				// TODO Auto-generated method stub
		return rv;
	}
	
}


}
