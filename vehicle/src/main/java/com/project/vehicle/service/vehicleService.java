package com.project.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vehicle.dao.vehicleDao;
import com.project.vehicle.model.vehicleDetails;

@Service
public class vehicleService {

	@Autowired
	vehicleDao vehicleObj;
	
	public int insertNewVehicle(vehicleDetails v) {
		
		
		return vehicleObj.insertVehicle(v);
	}
	
	
	public List<vehicleDetails>getVehicleByName(String name) {
		return vehicleObj.getVehicleName(name);
	}
	
	public vehicleDetails getVehicle(int id) {
		
		return vehicleObj.getVehicles(id);
	}
	public List<vehicleDetails> getVehicles(){
		
		
		return vehicleObj.getAllVehicles();
	}
public int updateQuantity(int vehicleid,int quantity) {
	
	return vehicleObj.getQuantityUpdate(vehicleid, quantity);
}
}
