package com.project.vehicle.dao;
import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.project.vehicle.model.User;
import com.project.vehicle.model.vehicleDetails;

@Component
public class vehicleDao {
	@Autowired
	private DataSource ds;
	@Autowired
	private JdbcTemplate template;

	public int insertVehicle(vehicleDetails v) {
		
		String sql="INSERT INTO vehicle(brand,model,mileage,engine,price,transmission_type,quantity,image) values(?,?,?,?,?,?,?,?)";
		
		return template.update(sql, new Object[] {v.getBrand(),v.getModel(),v.getMileage(),v.getEngine(),v.getPrice(),v.getTransmission_type(),v.getQuantity(),v.getImage()});
		
		
	}
	
	public List<vehicleDetails> getAllVehicles(){
		
		String sql="SELECT * FROM vehicle";
		
		return template.query(sql, new vehicleMapper());
		
		
	}
	
public List<vehicleDetails> getVehicleName(String name){
		
		String sql="SELECT * FROM vehicle WHERE brand LIKE '%" +name+ "%' or model LIKE '%"+name+"%'";
		
		return template.query(sql, new vehicleMapper());
		
		
	}
public vehicleDetails getVehicles(int id){
		
		String sql="SELECT * FROM vehicle WHERE vehicle_id=?";
		
		return template.queryForObject(sql, new Object[] {id},new vehicleMapper());
		
		
	}
public int getQuantityUpdate(int vehicleid,int quantity) {
	
String sql="update vehicle set quantity=? where vehicle_id=?";
return template.update(sql,new Object[] {quantity,vehicleid});
}
	public static final class vehicleMapper implements RowMapper<vehicleDetails>{

		@Override
		public vehicleDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			String b=rs.getString("brand");
			String m=rs.getString("model");
			
			vehicleDetails vehicle=new vehicleDetails();
			int q=rs.getInt("quantity");
			vehicle.setQuantity(rs.getInt("quantity"));
			vehicle.setId(rs.getInt("vehicle_id"));
			vehicle.setBrand(rs.getString("brand"));
			vehicle.setModel(rs.getString("model"));
			vehicle.setMileage(rs.getFloat("mileage"));
			vehicle.setEngine(rs.getFloat("engine"));
			vehicle.setPrice(rs.getFloat("price"));
			vehicle.setTransmission_type(rs.getString("transmission_type"));
			
			vehicle.setImage(rs.getString("image"));
			if(q<=0) {
				vehicle.setDisabled("disabled");
				
			}
			else {
				vehicle.setDisabled("false");
			}
			
			return vehicle;
		}
		
		
	}
	
}
