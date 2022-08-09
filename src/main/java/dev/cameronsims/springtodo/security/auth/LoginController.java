package dev.cameronsims.springtodo.security.auth;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        
        if (request.isUserInRole("ROLE_USER")) {
            return "redirect:app";
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginFailed() {
        return "redirect:/authenticate?error=invalid username or password";
    }
    
}
