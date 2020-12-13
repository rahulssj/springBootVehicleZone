package com.project.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.vehicle.service.vehicleService;

@SpringBootApplication

public class VehicleApplication {

	public static void main(String[] args) {
		
	
	SpringApplication.run(VehicleApplication.class, args);
		
	
	}

}
