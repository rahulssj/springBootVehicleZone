package com.project.vehicle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.project.vehicle.model.User;
import com.project.vehicle.model.buyDetails;

@Component
public class productDao {

	
		@Autowired
		private DataSource ds;
		@Autowired
		private JdbcTemplate template;


		
	public int insertSale(buyDetails product) {
		
		String sql="INSERT INTO productsales(mobile,pincode,quantity,state,username,vehicleid,date,address,totalamount) values(?,?,?,?,?,?,?,?,?)";
		
		return template.update(sql, new Object[] {product.getMobile(),product.getPincode(),product.getQuantity(),product.getState(),product.getUsername(),product.getVehicleid(),product.getDate(),product.getAddress(),product.getTotalamount() });
		
		
	}

	public buyDetails getSales(String name){
			
			String sql="SELECT * FROM productsales WHERE username=?";
			
			return template.queryForObject(sql, new Object[] {name},new productMapper());
			
			
		}

	public int getProductCount(String name) {
		
	String sql="SELECT COUNT(*) FROM productsales WHERE username=?";	

	return template.queryForObject(sql, new Object[] {name},Integer.class);
	}
	
	public int getProductCountById(int id) {
		
	String sql="SELECT COUNT(*) FROM productsales WHERE vehicleid=?";	

	return template.queryForObject(sql, new Object[] {id},Integer.class);
	}
	
	public List<buyDetails> getAllSales(String name){
		String sql="SELECT * FROM productsales WHERE username=?";	
		return template.query(sql, new Object[] {name},new productMapper() );
			
		
	}
	
	
	public int totalSale() {
		String sql="SELECT sum(quantity) FROM productsales";	
		
		return template.queryForObject(sql, Integer.class);
		
		
	}
	public long totalSaledAmount() {
		String sql="SELECT sum(totalamount) FROM productsales";	
		return template.queryForObject(sql, Integer.class);
		
		
	}
	
	public static final class productMapper implements RowMapper<buyDetails>{

		@Override
		public buyDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			buyDetails product =new buyDetails();
			product.setUsername(rs.getString("username"));
			product.setMobile(rs.getString("mobile"));
			product.setAddress(rs.getString("address"));
			product.setDate(rs.getDate("date"));
			product.setPincode(rs.getString("pincode"));
			product.setQuantity(rs.getInt("quantity"));
			product.setState(rs.getString("state"));
			product.setVehicleid(rs.getInt("vehicleid"));
			product.setTotalamount(rs.getFloat("totalamount"));
			return product;
		}

					}


}


