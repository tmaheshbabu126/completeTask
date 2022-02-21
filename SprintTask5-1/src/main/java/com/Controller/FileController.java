package com.Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Entity.ExportFormats;
import com.Entity.Users;
import com.Repository.UserRepository;
import com.Service.FileService;

@Controller
public class FileController {

    @Autowired
    FileService fileService;
    
    @Autowired
    private UserRepository userRepository;


	

	@RequestMapping("/uploadFiles") public String uploadFiles(@RequestParam("files")
    MultipartFile[] files, RedirectAttributes redirectAttributes) {



    	List<String> fileNames = new ArrayList<>();
    	Arrays.asList(files).stream().forEach(file -> {
    		if(file.getOriginalFilename().toString() == null) {
    			fileNames.clear();
    		}
    		else
    		fileNames.add(file.getOriginalFilename());
    	});


    	System.out.println("files name is  = " + fileNames); 
    	//System.out.println(files[1].toString());
    	
    	System.out.println("the condition is  blank= "+  fileNames.get(0).isBlank());
    	//System.out.println("the condition is not null= "+ (fileNames.get(0).toString() != null));

    /*	if(fileNames.get(0).toString() != null &&  !fileNames.get(0).isBlank()) {
    		
    		System.out.println("files are not null");
    		 System.out.println(files);
    		Arrays.asList(files) .stream() .forEach(file ->
    		fileService.uploadFile(file));

    		redirectAttributes.addFlashAttribute("upload",
    				"You successfully uploaded all files!");
    	}*/
    	
	if(!fileNames.get(0).isBlank()) {
    		
    		System.out.println("files are not null");
    		 System.out.println(files);
    		Arrays.asList(files) .stream() .forEach(file ->
    		fileService.uploadFile(file));

    		redirectAttributes.addFlashAttribute("upload",
    				"You successfully uploaded all files!");
    	}

    	else { 
    		System.out.println("files are null");
    		System.out.println(files);
    		redirectAttributes.addFlashAttribute("upload",
    				"empty file is not accepted select files!");
    	}

    	return "redirect:/admin"; 
    }
	 
	
	


  
	
    
	
    //added type resource
    @GetMapping("/downloadFiles")
    public String downloadFileFromLocal(@RequestParam("files") String files,@RequestParam("format") String format,Model model, RedirectAttributes redirectAttributes) {
    	System.out.println(files);
        String str = files;
        String[] arrOfStr = str.split("\\.");
        		String fileName = arrOfStr[0];
            System.out.println(fileName);
           
    	System.out.println("format of file is "+format);
    	if(format.equals("Choose Format...")) {
    		format = ".csv";
    		
    	}
    	System.out.println("enhanced format is "+format);
    	
    	System.out.println("in file control"+fileName+format);
    	
    	String filePath = "C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\Files\\Output File\\";
    	//File myObj = new File(filePath+fileName+format);
    	
    	if(!fileName.isBlank()) {
    	try {
    		System.out.println(fileName.isBlank());
    		
    		System.out.println(files);
    		File myObj = new File(filePath+fileName+format);
    		System.out.println(filePath+fileName+format);
    	      if (myObj.createNewFile()) {
    	        System.out.println("File created: " + myObj.getName());
    	        redirectAttributes.addFlashAttribute("Export",
        	            "The " + files+ "has been exported and saved as "+myObj.getName());
    	      } else {
    	        System.out.println("File already exists.");
    	        redirectAttributes.addFlashAttribute("Export","File already exists.");
    	      }
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    	}
   	else {
    		 redirectAttributes.addFlashAttribute("Export","Please select any file");
    	}

		/*
		 * Path path =
		 * Paths.get("C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\task notes files\\" +
		 * files);
		 */
    	//Path path = Paths.get(filePath + files);
		
    	 
		/*
		 * Resource resource = null; try { resource = new UrlResource(path.toUri()); }
		 * catch (MalformedURLException e) { e.printStackTrace(); } return
		 * ResponseEntity.ok() .contentType(MediaType.parseMediaType("text/csv"))
		 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
		 * resource.getFilename() + "\"") .body(resource);
		 */
    	
    	 Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 		String username="";
 		if (principal1 instanceof UserDetails) {
 			username = ((UserDetails)principal1).getUsername();
 		} else {
 			username = principal1.toString();
 		}
 		model.addAttribute("uName", username);
 		System.out.println(username);
 		
 		Users user = userRepository.findByUsername(username);
 		String role = user.getRole();
 		role = role.toLowerCase();
         
         return "redirect:/"+role;
//    	return "redirect:/admin";

    }
    
    
    
    
    
    
 
    
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String create_file(RedirectAttributes redirectAttributes,Principal principal,Model model) throws IOException  {
    	final String FILE_NAME = "C://Users/mahes/Desktop/Task-CG-Asha/Files/Output File/CreatedFile.csv";
        File newFile = new File(FILE_NAME);
        boolean success = newFile.createNewFile();
        //InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));
        if(success) { System.out.println("File created");
        redirectAttributes.addFlashAttribute("generate",
                "You have successfully Exported the file!");
        }
        else {
        	System.out.println("already file exist please remove it if want to create again");
        	 redirectAttributes.addFlashAttribute("generate",
                     "already file exist please remove it if want to create again");
        }
 
        
		/*
		 * redirectAttributes.addFlashAttribute("message",
		 * "You have successfully Exported the file!");
		 */
        
        Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal1 instanceof UserDetails) {
			username = ((UserDetails)principal1).getUsername();
		} else {
			username = principal1.toString();
		}
		model.addAttribute("uName", username);
		System.out.println(username);
		
		Users user = userRepository.findByUsername(username);
		String role = user.getRole();
		role = role.toLowerCase();
        
        return "redirect:/"+role;
    }
    
    
    
}