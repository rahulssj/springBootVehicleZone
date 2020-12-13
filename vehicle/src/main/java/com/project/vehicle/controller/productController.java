package com.project.vehicle.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.vehicle.model.Recipt;
import com.project.vehicle.model.User;
import com.project.vehicle.model.buyDetails;
import com.project.vehicle.model.vehicleDetails;
import com.project.vehicle.service.productService;
import com.project.vehicle.service.userService;
import com.project.vehicle.service.vehicleService;

@Controller
public class productController {

	@Autowired
	vehicleService v;
	@Autowired
	productService p;
	@Autowired
	userService u;
	
	@RequestMapping("bookProduct")
	public ModelAndView Book(buyDetails detail ,HttpSession session) {
		System.out.println("inside bookproduct");
		ModelAndView mv=new ModelAndView("payment");
		
		try {
		boolean loggedin=(boolean)session.getAttribute("loggedin");
		List<buyDetails> buy=new ArrayList<buyDetails>();
		if(loggedin==true) {
		User user=(User)session.getAttribute("user");
		vehicleDetails vehicle=(vehicleDetails)session.getAttribute("vehicle");
		detail.setUsername(user.getUsername());
		detail.setVehicleid(vehicle.getId());
		long mills=System.currentTimeMillis();
		Date d=new Date(mills);
detail.setDate(d);
detail.setTotalamount(detail.getQuantity()*vehicle.getPrice());
detail.setAddress(detail.getAddressline1()+detail.getAddressline2());
System.out.println(detail);
buy.add(detail);
session.setAttribute("product", buy);
if(vehicle.getQuantity()<detail.getQuantity()) {
	mv.setViewName("error");
	mv.addObject("error","quantity is not available");
	
	
}
else {
	mv.setViewName("payment");
mv.addObject("amount", detail.getTotalamount());	

}	
		}
		
		else {
			mv.setViewName("error");
			mv.addObject("error","Login Error Login First");
				
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		
		System.out.println(e);
		mv.setViewName("error");
		mv.addObject("error","Login Error Login First");
		
		}
		

	return mv;
	}
	
	@RequestMapping("pay")
	public ModelAndView reciept(@RequestParam("debit") String debit,HttpSession session) {
	System.out.println("inside pay");
	System.out.println(debit);	
	ModelAndView mv=new ModelAndView();
	
	try {
	boolean loggedin=(boolean)session.getAttribute("loggedin");	
	
	if(loggedin==true) {
	String username=(String)session.getAttribute("username");
	if(debit.compareTo("rahul")==0)
	{List<Recipt> recipt=new ArrayList<Recipt>();
		List<buyDetails> obj=(List<buyDetails>)session.getAttribute("product");
		System.out.println(obj);
		float totalPaid=0;
		for(buyDetails b:obj) {
		Recipt r=new Recipt();
			
		p.insertSaleData(b);
		vehicleDetails veh=v.getVehicle(b.getVehicleid());
		int q=veh.getQuantity()-b.getQuantity();
		v.updateQuantity(b.getVehicleid(), q);
		User userobj=u.getUser(b.getUsername());
		totalPaid+=b.getTotalamount();	
		r.setUsername(b.getUsername());
		r.setAddress(b.getAddress());
		r.setBrand(veh.getBrand());
		r.setModel(veh.getModel());
		r.setEmail(userobj.getEmail());
		r.setName(userobj.getName());
		r.setAddress(b.getAddress());
		r.setDate(b.getDate());
		r.setPincode(b.getPincode());
		r.setPrice(veh.getPrice());
		r.setQuantity(b.getQuantity());
		r.setState(b.getState());
		r.setTotalamount(b.getTotalamount());
		r.setImage(veh.getImage());
		recipt.add(r);
		}
		System.out.println(recipt);
		mv.setViewName("reciept");
		mv.addObject("details",recipt);
		mv.addObject("total", totalPaid);
		mv.addObject("username", username);
		session.removeAttribute("product");

		return mv;

		}
		
	}
	else {
		mv.setViewName("error");
		mv.addObject("error", "login to book product");
		
	}
	}
	catch (Exception e) {
		// TODO: handle exception
		mv.setViewName("error");
		mv.addObject("error", "login to book product");
		System.out.println("error");
	}
	
	session.removeAttribute("product");
	return mv;
	
	}

	
	@RequestMapping("/handleCart")
	@ResponseBody
	public String getJsonVehicle(@RequestParam("vehicleid") int vehicleid) {
	
		vehicleDetails veh=v.getVehicle(vehicleid);
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
		   json = mapper.writeValueAsString(veh);
		  System.out.println("ResultingJSONstring = " + json);
		  //System.out.println(json);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}

		return json;
	}
	

	@RequestMapping("/bookCartProduct")
	public ModelAndView getCartDetails(buyDetails detail,HttpSession session) {
	
		System.out.println("inside bookproduct");
		ModelAndView mv=new ModelAndView("payment");
		List<buyDetails> buyObj=new ArrayList<buyDetails>();
		try {
		boolean loggedin=(boolean)session.getAttribute("loggedin");
		if(loggedin==true) {
			int save=0;
			String name=(String)session.getAttribute("username");
	
			Map<Integer,Integer> m=(Map<Integer,Integer>)session.getAttribute("cart");
			Set<Map.Entry<Integer, Integer>> entrySet=m.entrySet();
			for(Map.Entry<Integer, Integer> entry:entrySet) {
				
			
			detail.setQuantity(entry.getValue());
			
		detail.setUsername(name);
		detail.setVehicleid(entry.getKey());
		vehicleDetails vehicle=v.getVehicle(detail.getVehicleid());
		detail.setTotalamount(detail.getQuantity()*vehicle.getPrice());
		long mills=System.currentTimeMillis();
		Date d=new Date(mills);
detail.setDate(d);

detail.setAddress(detail.getAddressline1()+detail.getAddressline2());
buyObj.add(detail);
System.out.println(detail);
			

if(vehicle.getQuantity()<detail.getQuantity()) {
	save=0;
	mv.setViewName("error");
	mv.addObject("error","quantity not available");
	break;
}
else {
save=1;

}	
			
			}
		
		if(save==1) {
			mv.addObject("product",buyObj );
			session.setAttribute("product", buyObj);
			System.out.println(buyObj);
			mv.setViewName("payment");
		}
		
		else {
			mv.setViewName("error");
			mv.addObject("error","Login Error Login First");
				
		}
		
		}
		
		else {
			mv.setViewName("error");
			mv.addObject("error","Login Error Login First");
				
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		
		System.out.println(e);
		mv.setViewName("error");
		mv.addObject("error","Login Error Login First");
		
		}
		

	return mv;
	
		
	}
	
@GetMapping("/cartRequest")
public String cartRequest() {
	
	
	return "adress";
}

	
@RequestMapping(value="/cartBuy",consumes="application/json")
@ResponseBody
public String buyy(@RequestBody Map<Integer,Integer> m,HttpSession session) {
	
	try {
		boolean loggedin=(boolean)session.getAttribute("loggedin");
		
		if(loggedin) {
		session.setAttribute("cart", m);
		System.out.println(m);
Set<Map.Entry<Integer, Integer>> s=m.entrySet();

for(Map.Entry<Integer,Integer> item:s) {

	System.out.println(item.getKey());
System.out.println(item.getValue());
}
	
		return "success";}
		else {
			return "failed";
		}
	}
	catch (Exception e) {
		// TODO: handle exception
	return "failed";
	}
	
}



}
