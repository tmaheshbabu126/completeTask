package com.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.nio.file.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.CustomException.FileStorageException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import java.net.MalformedURLException;


@Service
public class FileService {

	@Value("${app.upload.dir:${user.home}}")
	public String uploadDir;

	public void uploadFile(MultipartFile file) {

		try {
			Path copyLocation = Paths

					.get("C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\Files\\Input File" + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileStorageException("Could not store file " + file.getOriginalFilename()
			+ ". Please try again!");
		}
	}
	
	/*
	 * public ResponseEntity<Object> downloadFile(MultipartFile file) throws
	 * IOException { String filename =
	 * "C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\task notes files\\Error.txt"; File
	 * files1 = new File(filename); InputStreamResource resource = new
	 * InputStreamResource(new FileInputStream(files1)); HttpHeaders headers = new
	 * HttpHeaders();
	 * 
	 * 
	 * 
	 * 
	 * headers.add("Content-Disposition",
	 * String.format("attachment; filename=\"%s\"", files1.getName()));
	 * headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	 * headers.add("Pragma", "no-cache"); headers.add("Expires", "0");
	 * 
	 * ResponseEntity<Object> responseEntity =
	 * ResponseEntity.ok().headers(headers).contentLength(
	 * files1.length()).contentType(MediaType.parseMediaType("application/txt")).
	 * body( resource);
	 * 
	 * return responseEntity;
	 * 
	 * 
	 * }
	 */

//	public ResponseEntity<Object>  downloadFile(MultipartFile file) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		
//				  
//		return responseEntity;
//	}
	
	

	/*
	 * public Object downloadFile(MultipartFile file) { // TODO Auto-generated
	 * method stub return null; }
	 */
	
	
}