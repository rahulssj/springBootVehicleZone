package com.project.vehicle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.project.vehicle.model.User;


@Component
public class userDao {
	@Autowired
	private DataSource ds;
	@Autowired
	private JdbcTemplate template;
@Autowired
private NamedParameterJdbcTemplate namedTemplate;
	
public int insertUser(User user) {
	
	String sql="INSERT INTO user_registered(username,name,email,password,gender,created_At,isActive,superuser) values(?,?,?,?,?,?,?,?)";
	
	return template.update(sql, new Object[] {user.getUsername(),user.getName(),user.getEmail(),user.getPassword(),user.getGender(),user.getDate(),user.isActive(),user.isSuperuser()});
	
	
}

public User getusers(String name){
		
		String sql="SELECT * FROM user_registered WHERE username=?";
		
		return template.queryForObject(sql, new Object[] {name},new userMapper());
		
		
	}

public int getUserLogin(String name,String pass) {
	
String sql="SELECT COUNT(*) FROM user_registered WHERE username=? and password=?";	

return template.queryForObject(sql, new Object[] {name,pass},Integer.class);
}
public int getUserCountByName(String name) {
	String sql="SELECT COUNT(*) FROM user_registered WHERE username=?";	

	return template.queryForObject(sql, new Object[] {name},Integer.class);
	
}
public int updateUserLogin(String name) {
	String sql="update user_registered set isactive=true where username=?";
	return template.update(sql, new Object[] {name});
}
public int updateUserLogout(String name) {
	String sql="update user_registered set isactive=false where username=?";
	return template.update(sql, new Object[] {name});
}

public int updatePassword(int id,String pass) {
System.out.println("inside dao");
System.out.println(id);
System.out.println(pass);
	String sql="UPDATE user_registered SET password=? WHERE user_id=?";
	
	return template.update(sql, new Object[] {pass,id});
}
public int getUserid(String name) {
	System.out.println("inside getuserid dao");
	String sql="select user_id from user_registered where username=?";
	return template.queryForObject(sql, new Object[] {name},Integer.class);
}
public int getEmailid(String email) {
	System.out.println("inside getemailid dao");
	String sql="select count(*) from user_registered where email=?";
	return template.queryForObject(sql, new Object[] {email},Integer.class);
}

public int getSuperUser(String name,String password){
	
	String sql="SELECT COUNT(*) FROM user_registered WHERE username=? and password=? and superuser=true";
	
	return template.queryForObject(sql, new Object[] {name,password},Integer.class);
	
	
}


public static final class userMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user =new User();
		
		user.setUsername(rs.getString("username"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setGender(rs.getString("gender"));
		user.setName(rs.getString("name"));
		user.setDate(rs.getDate("created_At"));
		user.setActive(rs.getBoolean("isactive"));
		user.setSuperuser(rs.getBoolean("superuser"));
		return user;
		}
		
		
	}
}
