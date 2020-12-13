package com.project.vehicle.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.vehicle.model.User;
import com.project.vehicle.model.vehicleDetails;

import com.project.vehicle.service.vehicleService;

@Controller
public class vehicleController {

	private static String UPLOADED_FOLDER = "C:\\Users\\rahul\\Congnizant\\vehicle\\src\\main\\resources\\static\\images\\";

	@Autowired
	vehicleService vehicle;
	
	Logger logger=LoggerFactory.getLogger(vehicleController.class);
	
	@RequestMapping("/")
	public ModelAndView countData(@ModelAttribute("status") String status,HttpSession session) {
		List<vehicleDetails> vehiclelist=vehicle.getVehicles();
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("vehicleDetails",vehiclelist);
		mv.addObject("logoutLink",session.getAttribute("username"));
		logger.trace("inside vehicle controller home");
		boolean stat=false;
		try {
		boolean loggedin=(boolean)session.getAttribute("loggedin");
		if(loggedin) {
		stat=loggedin;}
		System.out.println(loggedin);
		}
		catch (Exception e) {
			// TODO: handle exception
		System.out.println(e);
		}
		try {
			if(status.compareTo("true")==0) {
				stat=true;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			stat=false;
		}
		System.out.println(stat);
			mv.addObject("stat", stat);
		System.out.println(session.getAttribute("username"));
		System.out.println("hello");
		System.out.println(vehicle.getVehicles());
		return mv;
	}
	@RequestMapping("user")
	public ModelAndView userInterface(@RequestParam("id") int id) {
		
		ModelAndView mv=new ModelAndView("user");
		
		vehicleDetails v=vehicle.getVehicle(id);
		mv.addObject("vehicle", v);
		System.out.println(v);
		logger.trace("vehicle controller");
		return mv;
	}
	
		
	
	
	@RequestMapping("/getresult")
	public ModelAndView getResult(@RequestParam("name") String name) {
		
		List<vehicleDetails> v=vehicle.getVehicleByName(name);
		
		if(!v.isEmpty()) {
			ModelAndView mv=new ModelAndView("filter");
			mv.addObject("vehicleDetails", v);
		System.out.println(v);
		return mv;
	
		}
		else {
			ModelAndView mv=new ModelAndView("error");
		return mv;
		}
		
	}
	
}
