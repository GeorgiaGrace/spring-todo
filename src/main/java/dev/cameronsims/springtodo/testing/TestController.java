package dev.cameronsims.springtodo.testing;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cameronsims.springtodo.security.auth.user.AppUserDetails;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    @PreAuthorize("permitAll()")
    public Long testGet() {

        AppUserDetails user = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user.getId();

    }
    
}
