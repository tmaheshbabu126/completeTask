package com.Configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.Service.CustomUserDetails;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
        String Role = userDetails.getRole();
      
        
       // Model model = null;
     //   model.addAttribute("uName", userDetails.getUsername()); 
        System.out.println(Role);
        
         
        if (Role.equals("USER")) {
            redirectURL = "/user";
        } else if (Role.equals("ADMIN")) {
            redirectURL = "/admin";
        } else {
            redirectURL = "/home";
        }
        
        
        
//       String uName;
//        uName= userDetails.getUsername(); 
         
       
        System.out.println(redirectURL);
        response.sendRedirect(redirectURL);
         
    }
 
}