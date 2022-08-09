package dev.cameronsims.springtodo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        if (request.isUserInRole("ROLE_USER")) {
            return "redirect:app";
        }

        return "index";
    }

}
