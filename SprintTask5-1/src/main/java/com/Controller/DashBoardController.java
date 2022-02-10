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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	
	
	/*@RequestMapping("/dashBoard")
	public String viewHomePage(Model model, @Param("dateSearch") Date dateSearch,
			Map<String, Object> model1,Principal principal) {
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
		
		System.out.println("in dashboard post");
		List<SearchKeyWords> dateSearchList= (List<SearchKeyWords>) si.findByDate(dateSearch);
		 model1.put("dateSearchList", dateSearchList);
		 //model1.put("message", 2); //
			 return "redirect:/dashBoard"; 
		  return "/dashBoard";

		//model1.put("message", 1);

	}	*/
	
	@RequestMapping("/dashBoard")
	public String viewHomePage1() {
		System.out.println("in get dashboard request");
		return "dashBoard";
		
		
	}
	
	@RequestMapping("/dashBoard-view")
	public String viewHomePage(Model model, @RequestParam String dateSearch ,
			Map<String, Object> model1,Principal principal) throws ParseException {
		
		 System.out.println("in dashboard post");
		 Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateSearch);
		 List<SearchKeyWords> dateSearchList = si.findByDate(date1);
		 model1.put("dateSearchList",dateSearchList);
				 
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
		
		System.out.println("in dashboard post");
		
 
		 System.out.println("in dashboard post-view to return");
		 return "dashBoard-view";

		

	}
	
	
	
	
	
	
	/*
	 * @GetMapping("/dashBoard") public String dashController(Map<String, Object>
	 * model1,Model model,Principal principal) {
	 * System.out.println("in dashboard get");
	 * 
	 * Object principal1 =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); String
	 * username=""; if (principal1 instanceof UserDetails) { username =
	 * ((UserDetails)principal1).getUsername(); } else { username =
	 * principal1.toString(); } model.addAttribute("uName", username);
	 * System.out.println(username);
	 * 
	 * List<SearchKeyWords> searchesData = si.getAllSearch();
	 * 
	 * 
	 * 
	 * model1.put("searchesData", searchesData);
	 * 
	 * 
	 * 
	 * 
	 * //model1.put("message", 1); return "dashBoard";
	 * 
	 * }
	 * 
	 * 
	 * @PostMapping("/dashBoard") public String dashController2(Map<String,Object>
	 * model1,Model model,Principal principal) {
	 * 
	 * System.out.println("in dashboard post"); return "dashBoard-view"; }
	 * 
	 * 
	 * @GetMapping("/dashBoard-view") public String datePassController(@RequestParam
	 * String dateSearch,Model model,Map<String, Object> model1,RedirectAttributes
	 * redirectAttributes, HttpServletResponse httpResponse) throws ParseException,
	 * IOException{
	 * 
	 * System.out.println("in dashboard post"); Date date1=new
	 * SimpleDateFormat("yyyy-MM-dd").parse(dateSearch); List<SearchKeyWords>
	 * dateSearchList = si.findByDate(date1); model1.put("dateSearchList",
	 * dateSearchList);
	 * 
	 * ResponseEntity<List<SearchKeyWords>> rt=new
	 * ResponseEntity<List<SearchKeyWords> >(dateSearchList,HttpStatus.OK);
	 * //response to client //return rt;
	 * 
	 * // httpResponse.sendRedirect("/dashBoard-view");
	 * 
	 * return "redirect:/dashBoard-view"; }
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
