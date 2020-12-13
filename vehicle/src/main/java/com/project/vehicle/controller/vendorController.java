package com.project.vehicle.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.vehicle.model.User;
import com.project.vehicle.model.requestVehicle;
import com.project.vehicle.model.vehicleDetails;
import com.project.vehicle.service.productService;
import com.project.vehicle.service.requestService;
import com.project.vehicle.service.userService;
import com.project.vehicle.service.vehicleService;

@Controller
@RequestMapping("/vendor")
public class vendorController {
@Autowired
requestService requestservice;
@Autowired
userService userservice;

@Autowired
vehicleService vehicle;
@Autowired
productService productservice;

private static String UPLOADED_FOLDER = "C:\\Users\\rahul\\Congnizant\\vehicle\\src\\main\\resources\\static\\images\\";

	@RequestMapping("/login")
	public String vendorLogin(HttpSession session) {
		try {
		boolean vlog=(boolean)session.getAttribute("vloggedin");
			if(vlog==true){
		
		return "redirect:vendorDash";
				}
			else {
				return "vendorLogin";
						
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			return "vendorLogin";
			
		}
		
		
		}
		
		@RequestMapping("vendorDash")
		public ModelAndView getVendorDash(HttpSession session) {
			
			String username=(String)session.getAttribute("username");
	
			ModelAndView mv=new ModelAndView("vendorDashboard");
			mv.addObject("username", username);
			
			session.getAttribute("vloggedin");
			return mv;

		}
	@RequestMapping("vendorlogin")
	public ModelAndView loginvendor(@RequestParam("username") String username,@RequestParam("password") String password,HttpSession session) {
		System.out.println("inside vendor");
		int count=userservice.getSuperUser(username, password);
		if(count>0) {
			userservice.updateLogin(username);
			ModelAndView mv=new ModelAndView("vendorDashboard");
			mv.addObject("username", username);
			session.setAttribute("username", username);
			session.setAttribute("vloggedin", true);
			return mv;
		}
		else {
		
			ModelAndView mv=new ModelAndView("vendorError");
			mv.addObject("error","USER NOT FOUND");
		return mv;
		}
		
		
		
	}
	
	@RequestMapping("/sales")
	public ModelAndView sales(HttpSession session) {
		
		ModelAndView mv=new ModelAndView();

		try {boolean vloggedin=(boolean)session.getAttribute("vloggedin");
		if(vloggedin==true) {
		List<Integer> sales=new ArrayList<Integer>();
		List<String> brand=new ArrayList<String>();
		Map<String,Integer> m=new HashMap<String, Integer>();
		int totalsales=productservice.getTotalSale();
		long totalamt=productservice.getTotalSaleAmount();
		List<vehicleDetails> v=vehicle.getVehicles();
		for(vehicleDetails vehicle:v) {
			
			int x=productservice.getCountById(vehicle.getId());
			sales.add(x);
			String b=vehicle.getBrand();
			m.put(b,x);
			
		}
		
		int max=Collections.max(sales);
		int min=Collections.min(sales);
		Set<Map.Entry<String,Integer>> values=m.entrySet();
		for(Map.Entry<String, Integer> res: values) {
			if(res.getValue()==max) {
				String maxBrand=res.getKey();
				brand.add(maxBrand);
			}
			
		}
		mv.setViewName("sales");
		mv.addObject("max", max);
		mv.addObject("brands", brand);
		mv.addObject("totalsales", totalsales);
		mv.addObject("totalamt", totalamt);
		}
		else {
			mv.setViewName("error");
			mv.addObject("error", "Vendor Log in to View Page!!");
		}
		
		}
		catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("error");
			mv.addObject("error", "Vendor Log in to View Page!!");
		
		}
	return mv;
		
		
	}

	@RequestMapping("/showRequest")
	public ModelAndView showRequest(HttpSession session) {
		ModelAndView mv=new ModelAndView();
		try {
		boolean vloggedin=(boolean)session.getAttribute("vloggedin");
		if(vloggedin==true) {
		mv.setViewName("request");
	System.out.println("inside show request");
	List<requestVehicle> req=requestservice.getAllRequest();
		
		System.out.println(req);
	mv.addObject("requests", req);
		
		}
		else {
			mv.setViewName("error");
			mv.addObject("error", "Not Logged In Please Login");
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("error");
			mv.addObject("error", "Not Logged In Please Login");
		
		}		
	return mv;
		
	}
	@RequestMapping("/acceptRequest")
	@ResponseBody
	public String acceptResult(@RequestParam("vehicleid") int vid,@RequestParam("id") int id,HttpSession session) {
		System.out.println("inside acceptrequest");
		try {
		boolean vloggedin=(boolean)session.getAttribute("vloggedin");
		if(vloggedin==true) {
	
		int x=requestservice.updateStatus(id);
		System.out.println(x);
		vehicleDetails veh=vehicle.getVehicle(id);
		int y=vehicle.updateQuantity(vid,veh.getQuantity()+1);
		System.out.println(x);
		System.out.println(y);
		if(x >0&&y>0) {
			return "success";
		}
		else {
			return "failed";
		}
		
	}
		else {
			return "failed";
		}
		
		}
	catch (Exception e) {
		// TODO: handle exception
return "failed pls login";
	}
	
	}
	
@GetMapping("/addingvehicle")
public String addvehicleImage() {
	
	return "addvehicle";
}


	@RequestMapping("/showInventory")
	public ModelAndView Inventory(HttpSession session) {
		
		ModelAndView mv=new ModelAndView();
		try {
		boolean vloggedin=(boolean)session.getAttribute("vloggedin");
		if(vloggedin==true) {
		
		List<vehicleDetails> vehiclelist=vehicle.getVehicles();
		mv.setViewName("inventory");
		mv.addObject("vehicleDetails",vehiclelist);
		String username=(String)session.getAttribute("username");
		mv.addObject("username", username);
		}
		else {
			mv.setViewName("error");
			mv.addObject("error", "Not Logged In Please Login");
			
		}
		
		}
		catch (Exception e) {
			// TODO: handle exception
		
			mv.setViewName("error");
			mv.addObject("error", "Not Logged In Please Login");
		
		}
		return mv;
	}
	
	@RequestMapping("/addvehicles")
	public ModelAndView addvehicle(vehicleDetails v,HttpSession session) {
		System.out.println(v);
		
		ModelAndView mv=new ModelAndView();
		try {
		boolean vloggedin=(boolean)session.getAttribute("vloggedin");
		if(vloggedin==true) {
		
		String img=(String)session.getAttribute("imagedata");
System.out.println(img);
v.setImage(img);
int x=vehicle.insertNewVehicle(v);
if(x>0) {
		mv.setViewName("success");
		
}
else {
	
mv.setViewName("error");
	mv.addObject("error", "insertfailed");
	return mv;
}
	
		}
		
		else {
			mv.setViewName("error");
			mv.addObject("error", "Not Logged In Please Login");
		
		}
		}
	catch (Exception e) {
			// TODO: handle exception
		mv.setViewName("error");
		mv.addObject("error", "Not Logged In Please Login");
		
	}	
	
	return mv;}
	
	@GetMapping("/inventory")
public String getInventory() {
		
		return "redirect:showInventory";
	}
	@RequestMapping("/requestvehicle")
	public String requestvehicle() {
		
		return "redirect:/vendor/showRequest";
	}
	@GetMapping("addvehicle")
	public String add() {
		
		
		return "redirect:/vendor/uploadFile";
	}
	
	
	

	@RequestMapping("/uploadFile")
	public String file(HttpSession session) {
		try {
		boolean vloggedin=(boolean)session.getAttribute("vloggedin");
	
		if(vloggedin==true) {
		return "file";}
	
		else {
			return "error";
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		System.out.println("error");
		return "error";
		}
	}
	
	@RequestMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,HttpSession session) {
		try {
			boolean vloggedin=(boolean)session.getAttribute("vloggedin");
		
			if(vloggedin==true) {
		
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
System.out.println("inside upload");
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            session.setAttribute("imagedata", file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

           
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        return "redirect:addingvehicle";
			}
			
			else {
				return "error";
			}
			}
		catch (Exception e) {
			// TODO: handle exception
		return "error";
		}
     
    }

    @GetMapping("/UploadStatus")
    public String uploadStatus() {
        return "UploadStatus";
    }


	@GetMapping("/dashboard")
	public String getDashboards(HttpSession session) {
		
		try {
			boolean vloggedin=(boolean)session.getAttribute("vloggedin");
		
			if(vloggedin==true) {
				return "redirect:vendorDash";
			}
			else {
				
				return "error";
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		return "error";
		}
	}


	@RequestMapping("logout/{id}")
public String logout(@PathVariable("id") String id,HttpSession session) {
	userservice.updateLogout(id);
	session.invalidate();
 
	return "redirect:/vendor/login";
	}
}
