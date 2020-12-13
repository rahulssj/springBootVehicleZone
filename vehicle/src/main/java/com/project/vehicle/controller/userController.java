package com.project.vehicle.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.vehicle.model.User;
import com.project.vehicle.model.buyDetails;
import com.project.vehicle.model.useDashboard;
import com.project.vehicle.model.vehicleDetails;
import com.project.vehicle.service.productService;
import com.project.vehicle.service.userService;
import com.project.vehicle.service.vehicleService;

@Controller
@RequestMapping("user")

public class userController {
	@Autowired
	userService userservice;
	@Autowired
	vehicleService vehicle;

	@Autowired
	productService productservice;
	@PostMapping("/registerUser")
	public String getRegister(User user) {
		long mills=System.currentTimeMillis();
		Date d=new Date(mills);
		user.setDate(d);
		user.setActive(false);
		user.setSuperuser(false);
		System.out.println("inside registerUser");
		System.out.println(userservice.insertUserData(user));
		System.out.println(user);
		return "redirect:/user/loginpage";
		
		
	}
	
	@GetMapping("/register")
	public ModelAndView registerPage() {
	/*	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "yawinpassword";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println();
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + encodedPassword);*/
		return new ModelAndView("register");
	}
	
	@GetMapping("/loginpage")
	public String loginPage() {
		System.out.println("inside loginpage");
		return "login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,@RequestParam("password") String password,RedirectAttributes ra,HttpSession session) {
		
		System.out.println("inside login of userController");
		if(userservice.getLoginCount(username, password)>0&&userservice.getSuperUser(username, password)==0) {
		System.out.println("userFound");
		User user=userservice.getUser(username);
		userservice.updateLogin(username);
		User user1=userservice.getUser(username);
		ra.addFlashAttribute("status",user1.isActive());
		ModelAndView mv=new ModelAndView("userhome");
		System.out.println(user1);
		mv.addObject("user",user1);
		session.setAttribute("username", username);
		session.setAttribute("loggedin", true);
		return "redirect:/";}
		
		else {
			return "redirect:/loginpage";
		}
	
		
	}
	
	@RequestMapping("logout/{id}")
public String logout(@PathVariable("id") String id,HttpSession session) {
	userservice.updateLogout(id);
	session.invalidate();
 
	return "redirect:/";
	}
	
	@RequestMapping("product/{id}")
	public ModelAndView product(@PathVariable("id") String id,@RequestParam("vehicle_id") int vid,HttpSession session) {
		ModelAndView mv=new ModelAndView();
		
		System.out.println("inside product(user)");
	try {
		boolean loggedin=(boolean)session.getAttribute("loggedin");
		if(loggedin==true) {
		User user=userservice.getUser(id);
		vehicleDetails v=vehicle.getVehicle(vid);
		mv.addObject("username", user.getUsername());
		mv.addObject("vehicleid", vehicle.getVehicle(vid));
		mv.setViewName("fillDetails");
		session.setAttribute("username", user.getUsername());
		session.setAttribute("user", user);
		session.setAttribute("vehicle",v );
	}
		else {
			mv.setViewName("login");
		}
	}
	
	catch (Exception e) {
		// TODO: handle exception
	System.out.println(e);
	mv.setViewName("login");
	}
		return mv;	
	}
	
	@RequestMapping("/forgotPassword")
	public String forgot() {
		
		return "forgot";
	}
	@RequestMapping("/resetpassword")
	@ResponseBody
	public String changePass(@RequestParam("username") String username,@RequestParam("password") String password) {
		System.out.println("inside reset");
		System.out.println(username);
		System.out.println(password);
		System.out.println("userid");
		int y=userservice.getUserid(username);
		System.out.println(y);
		int x=userservice.updatePassword(y, password);
		if(x>0) {
			
			return "success";
			
		}
		else {
			
			return "failed";
		}
		
	}
	
	@RequestMapping("/dash")
	public ModelAndView myDashboard(HttpSession session) {
		ModelAndView mv=new ModelAndView();
		
		try {
		String username=(String)session.getAttribute("username");	
		boolean loggedin=(boolean)session.getAttribute("loggedin");

		if(loggedin==true) {
		List<buyDetails> details= productservice.getAllSales(username);
System.out.println(details);
	List<useDashboard> dash=new ArrayList<useDashboard>();
	for(buyDetails detail:details) {
	useDashboard obj=new useDashboard();
	obj.setUsername(detail.getUsername());
	obj.setDate(detail.getDate());
	obj.setMobile(detail.getMobile());
	obj.setAddress(detail.getAddress()+" "+detail.getState()+" "+detail.getPincode());
	obj.setVehicleid(detail.getVehicleid());
	vehicleDetails v=vehicle.getVehicle(obj.getVehicleid());
	obj.setBrand(v.getBrand());
	obj.setImage(v.getImage());
	obj.setTotalamount(detail.getTotalamount());
	obj.setQuantity(detail.getQuantity());
	dash.add(obj);
	}
	System.out.println(dash);
	mv.setViewName("userdashboard");
	mv.addObject("details", dash);
	mv.addObject("username", username);
	
	
		}
		
		}
		
		catch (Exception e) {
			// TODO: handle exception
		System.out.println("error");
		mv.setViewName("error");
		mv.addObject("error", "login first");
		}
	
	return mv;
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
	
return "redirect:/user/dash";	}



@RequestMapping("/checkemail")
@ResponseBody
public String checkemail(@RequestParam("email") String email)
{
	int x=userservice.getEmailCount(email);
	System.out.println(x);
	if(x>0) {
		
		return "found";
		
	}
	else {
		
		return "not found";
	}
}


@RequestMapping("/checkuser")
@ResponseBody
public String checkUser(@RequestParam("user") String user) {
	System.out.println("inside");
	int x=userservice.getUserCount(user);
	System.out.println(x);
	if(x>0) {
		
		return "found";
		
	}
	else {
		
		return "not found";
	}
	
}


}
