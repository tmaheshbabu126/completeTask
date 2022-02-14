package com.Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.http.HttpHeaders;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Entity.Users;
import com.Repository.UserRepository;
import com.Service.FileService;

@Controller
public class FileController {

    @Autowired
    FileService fileService;
    
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {
    	
        Arrays.asList(files)
            .stream()
            .forEach(file -> fileService.uploadFile(file));

        redirectAttributes.addFlashAttribute("upload",
            "You successfully uploaded all files!");

        return "redirect:/admin";
    }
    
	
	
	
	
    
	
    //added type resource
    @GetMapping("/downloadFiles")
    public ResponseEntity<Resource> downloadFileFromLocal(@RequestParam("files") String files, RedirectAttributes redirectAttributes) {
    	System.out.println(files);
    	System.out.println("in file control");
    	Path path = Paths.get("C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\task notes files\\" + files);
    	Resource resource = null;
    	try {
    		resource = new UrlResource(path.toUri());
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	}
    	return ResponseEntity.ok()
    			.contentType(MediaType.parseMediaType("text/csv"))
    			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
    			.body(resource);
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