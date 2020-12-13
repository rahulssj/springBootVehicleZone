package com.project.vehicle.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
@Configuration
public class databaseConfig {

	 @Bean  
	    public DataSource getDataSource() 
	    {
		  DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.url("jdbc:mysql://localhost:3305/congnizant");
	        dataSourceBuilder.username("root");
	        dataSourceBuilder.password("chidhori");
	      
	        
	        return dataSourceBuilder.build();}
	 
	 @Bean
	 public JdbcTemplate getTemplate(DataSource ds) {
		 
		 
		 
		 return new JdbcTemplate(ds);
	 }
	 public NamedParameterJdbcTemplate getNamedTemplate(DataSource ds) {
		 
		 
		 
		 return new NamedParameterJdbcTemplate(ds);
	 }

	 
	 
}