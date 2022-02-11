package com.Controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String viewHomePage1(Model model,Principal principal,Map<String, Object> model1) {
		System.out.println("in get dashboard request");
		
		//getting loggin user
		Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal1 instanceof UserDetails) {
			username = ((UserDetails)principal1).getUsername();
		} else {
			username = principal1.toString();
		}
		model.addAttribute("uName", username);
		System.out.println(username);
		
		//getting all values
		List<SearchKeyWords> searchesData = si.getAllSearch();

		model1.put("searchesData", searchesData);
		return "dashBoard";


	}

	@RequestMapping("/dashBoard-view")
	public String viewHomePage(Model model, @RequestParam String dateSearch ,
			Map<String, Object> model1,Principal principal) throws ParseException {

		System.out.println("in dashboard post");
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateSearch);
		List<SearchKeyWords> dateSearchList = si.findByDate(date1);
		model1.put("dateSearchList",dateSearchList);

		// getting loggined user name
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
		
		model1.put("dateSearch", dateSearch);

		System.out.println("in dashboard post");


		System.out.println("in dashboard post-view to return");
		return "dashBoard-view";



	}

}
