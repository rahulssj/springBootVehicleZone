package com.project.vehicle.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.vehicle.model.requestVehicle;
import com.project.vehicle.service.requestService;

@Controller
public class requestController {

	@Autowired
	requestService requestservice;
	@RequestMapping("/reorder")
	@ResponseBody
	public String ReorderProduct(@RequestParam("id") int id,@RequestParam("username")  String username,HttpSession session) {
		
		try {
			boolean loggedin=(boolean)session.getAttribute("loggedin");
		if(loggedin==true) {
			requestVehicle req=new requestVehicle();
		long mills=System.currentTimeMillis();
		Date d=new Date(mills);
		req.setDate(d);
		req.setUsername(username);
		req.setStatus("Requested");
		req.setVehicleid(id);
		
		requestservice.insertRequest(req);
		
		return "{\"success\":1}";
		
		}
else {
			
			return "failed"; 
		}
		}
		catch (Exception e) {
			// TODO: handle exception
	System.out.println(e);
	return "failed";
		}
		
		}
	
}
