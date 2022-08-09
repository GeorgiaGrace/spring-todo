package dev.cameronsims.springtodo.security.auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.cameronsims.springtodo.security.auth.registration.RegistrationRequest;
import dev.cameronsims.springtodo.security.auth.registration.RegistrationService;
import dev.cameronsims.springtodo.security.auth.user.AppUserDetails;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService service;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String register(HttpServletRequest request) {
        
        if (request.isUserInRole("ROLE_USER")) {
            return "redirect:app";
        }
        
        return "register";
    }

    @PostMapping("/register")
    @ExceptionHandler( IllegalStateException.class )
    public AppUserDetails registerUser(@RequestBody RegistrationRequest regRequest, HttpServletRequest request) throws ServletException {

        // TODO: Add server-side password validation
            AppUserDetails user = service.register(regRequest);

            try {
                request.login(regRequest.getUsername(), regRequest.getPassword());
            } catch (ServletException e) {
                
            }

            if (user == null) {
                throw new IllegalStateException();
            }

            return user;

        
    }
    
}
