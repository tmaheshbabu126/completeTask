package com.Controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Entity.SearchDate;
import com.Entity.SearchKeyWords;
import com.Service.SearchDateImp;
import com.Service.SearchKeyWordsServiceImp;


@Controller

public class DashBoardController {
	
	@Autowired
	SearchKeyWordsServiceImp si;
	
	@Autowired
	SearchDateImp sdi;
	
	@RequestMapping("/dashBoard")
	public  String dashController(Map<String, Object> model1,Model model,Principal principal) {
		
		Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  String username="";
		    if (principal1 instanceof UserDetails) {
		       username = ((UserDetails)principal1).getUsername();
		    } else {
		       username = principal1.toString();
		    }
		    model.addAttribute("uName", username);
		    System.out.println(username);
		
		List<SearchKeyWords> searchesData = si.getAllSearch();
		
		
		
		 model1.put("searchesData", searchesData);
		 
		 
		 
		 
		   // model1.put("message", 1);
	        return "dashBoard";
	    
	}
	
	
	/*
	 * @RequestMapping(value = "/dashBoard", method = RequestMethod.GET) public
	 * String createUser(@RequestParam Date dateSearch,Model model,Map<String,
	 * Object> model1){
	 * 
	 * System.out.println("in dashboard post"); List<SearchKeyWords> dateSearchList
	 * = (List<SearchKeyWords>) si.findByDate(dateSearch);
	 * model1.put("dateSearchList", dateSearchList); model1.put("message", 2);
	 * return "redirect:/dashBoard/"; }
	 */
	 
	
	/*
	 * @RequestMapping("/search")
	 * 
	 * @ResponseBody public String thymeleafView( @RequestParam String q
	 * ,Map<String, Object> model,RedirectAttributes redirectAttributes,
	 * HttpServletResponse httpResponse) throws IOException, ParseException {
	 * 
	 * 
	 * 
	 * System.out.println(q);
	 * 
	 * 
	 * 
	 * SearchKeyWords s = new SearchKeyWords((long) 0,q,1);//changed from 0 to 1
	 * 
	 * 
	 * SearchKeyWords te1= si.addSearch(s);//sending the model obtained from client
	 * to service to process, for adding tenant
	 * 
	 * int id = si.findByIdKey(q);
	 * 
	 * System.out.println("the effective search id = "+id);
	 * 
	 * 
	 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	 * LocalDateTime now = LocalDateTime.now(); String sd = dtf.format(now);
	 * 
	 * 
	 * Date date2=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(sd);
	 * 
	 * System.out.println(dtf.format(now));
	 * 
	 * SearchDate sed= new SearchDate(0,date2,id);
	 * 
	 * sdi.addDate(sed);
	 * 
	 * ResponseEntity<SearchKeyWords> rt=new
	 * ResponseEntity<SearchKeyWords>(te1,HttpStatus.OK); //response to client
	 * //return rt;
	 * 
	 * httpResponse.sendRedirect("/dashBoard");
	 * 
	 * return "redirect:/dashBoard"; }
	 */
	

	
	

}
