package com.Controller;
import java.io.File;
import java.security.Principal;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Configuration.LoginSuccessHandler;
import com.fasterxml.jackson.core.JsonProcessingException;


@Controller

public class HomeController {
	
	 
	    LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
	    
	
	 
	  @RequestMapping("/")
	    public String root(){
	        return "home";
	    }

	  @RequestMapping("/user")
	    public String user(Principal principal,Model model){
		//  System.out.println(loginSuccessHandler.);
		   
		  Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username="";
		    if (principal1 instanceof UserDetails) {
		       username = ((UserDetails)principal1).getUsername();
		    } else {
		       username = principal1.toString();
		    }
		    model.addAttribute("uName", username);
		    System.out.println(username);
		  
	        return "user";
	    }

	  @RequestMapping("/admin")
	    public String admin(Principal principal,Model model){
		  Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username="";
		    if (principal1 instanceof UserDetails) {
		       username = ((UserDetails)principal1).getUsername();
		    } else {
		       username = principal1.toString();
		    }
		    model.addAttribute("uName", username);
		    System.out.println(username);
	        return "admin";
	    }
	  
	  @SuppressWarnings("unchecked")
	@RequestMapping("/fileSearch")
	    public String root(Map<String, Object> model,RedirectAttributes redirectAttributes,Principal principal,Model model1) throws JsonProcessingException{
		  
		 // ModelAndView result = new ModelAndView("editor/display");
		  //ObjectMapper objectMapper = new ObjectMapper();
		  
		  Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username="";
		    if (principal1 instanceof UserDetails) {
		       username = ((UserDetails)principal1).getUsername();
		    } else {
		       username = principal1.toString();
		    }
		    model1.addAttribute("uName", username);
		    System.out.println(username);
		    
		  File dir = new File("C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\dummy files");
	      String[] children = dir.list();
	      
	      if (children == null) {
	         System.out.println("does not exist or is not a directory");
	      } else {
	    	  System.out.println("in file parsing");
	         for (int i = 0; i < children.length; i++) {
	            String filename = children[i];
	            System.out.println(filename);
	         }
	      }
	      
	      ((Map<String, Object>) model1).put("number", children);
	      ((Map<String, Object>) model1).put("message", "Hello from Spring MVC");
		
		  
	        return "index";
	    }
  
	  
}
