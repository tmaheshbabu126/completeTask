package com.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Service.FileService;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

//    @GetMapping("/admin")
//    public String index() {
//        return "admin";
//    }

//    @PostMapping("/uploadFile")
//    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//
//        fileService.uploadFile(file);
//
//        redirectAttributes.addFlashAttribute("message",
//            "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {
    	
        Arrays.asList(files)
            .stream()
            .forEach(file -> fileService.uploadFile(file));

        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded all files!");

        return "redirect:/admin";
    }
    
	
	
	
	/*
	 * @RequestMapping(value = "/download", method = RequestMethod.GET) public
	 * ResponseEntity<Object> downloadFile() throws IOException { String filename =
	 * "C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\task notes files\\Error.txt"; File
	 * file = new File(filename); InputStreamResource resource = new
	 * InputStreamResource(new FileInputStream(file)); HttpHeaders headers = new
	 * HttpHeaders();
	 * 
	 * 
	 * 
	 * 
	 * headers.add("Content-Disposition",
	 * String.format("attachment; filename=\"%s\"", file.getName()));
	 * headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	 * headers.add("Pragma", "no-cache"); headers.add("Expires", "0");
	 * 
	 * ResponseEntity<Object> responseEntity =
	 * ResponseEntity.ok().headers(headers).contentLength(
	 * file.length()).contentType(MediaType.parseMediaType("application/txt")).body(
	 * resource);
	 * 
	 * 
	 * 
	 * return responseEntity; }
	 */

	 
	 
	
    
    
	
    
    @GetMapping("/downloadFiles")
    public ResponseEntity downloadFileFromLocal(@RequestParam("files") String files, RedirectAttributes redirectAttributes) {
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
    
    
    
    
    
	 
	/*
	 * @GetMapping("/download") public ResponseEntity
	 * downloadFileFromLocal(@PathVariable("fileName")String file) {
	 * System.out.println(file); System.out.println("in file control"); Path path =
	 * Paths.get("C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\task notes files" +
	 * file); Resource resource = null; try { resource = new
	 * UrlResource(path.toUri()); } catch (MalformedURLException e) {
	 * e.printStackTrace(); } return ResponseEntity.ok()
	 * .contentType(MediaType.parseMediaType("Content-Type: text/html"))
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	 * resource.getFilename() + "\"") .body(resource); }
	 */
    
    
    
    
    
    
    
	/*
	 * @GetMapping("/download/{fileName:.+}") public ResponseEntity
	 * downloadFileFromLocal(@PathVariable String fileName) { Path path =
	 * Paths.get(//fileBasePath
	 * "C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\task notes files"+ fileName);
	 * Resource resource = null; try { resource = new UrlResource(path.toUri()); }
	 * catch (MalformedURLException e) { e.printStackTrace(); } return
	 * ResponseEntity.ok()
	 * .contentType(MediaType.parseMediaType("application/octet-stream"))
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	 * resource.getFilename() + "\"") .body(resource); }
	 */
    
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String create_file(RedirectAttributes redirectAttributes) throws IOException  {
    	final String FILE_NAME = "C://Users/mahes/Desktop/Task-CG-Asha/Files/Output File/CreatedFile.csv";
        File newFile = new File(FILE_NAME);
        boolean success = newFile.createNewFile();
        //InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));
        if(success) System.out.println("File created");
        else System.out.println("already file exist please remove it if want to create again");
        
//        HttpHeaders headers = new HttpHeaders();
//        
//        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", newFile.getName()));
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        
//        ResponseEntity<Object> 
//        responseEntity = ResponseEntity.ok().headers(headers).contentLength(
//           newFile.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
//        
//        return responseEntity;
        
        redirectAttributes.addFlashAttribute("message",
                "You have successfully Exported the file!");
        return "redirect:/admin";
    }
    
    
    
}