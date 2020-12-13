package com.project.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vehicle.dao.requestDao;
import com.project.vehicle.model.requestVehicle;

@Service
public class requestService {
@Autowired
requestDao request;

public List<requestVehicle> getAllRequest(){
	
	return request.getAllRequest();
}

	public List<requestVehicle> getAllRequestByName(String name){
		
		return request.getAllRequestByUsername(name);
	}
	
	public int insertRequest(requestVehicle req) {
		
	return request.insertRequest(req);}
	
	public int updateStatus(int id) {
		return request.updateStatus(id);
	}
	public int getRequestCount() {
		
		return request.totalRequest();
	}

}
