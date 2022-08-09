package dev.cameronsims.springtodo.security.auth.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cameronsims.springtodo.security.auth.user.AppUserDetails;
import dev.cameronsims.springtodo.security.auth.user.AppUserDetailsService;
import dev.cameronsims.springtodo.security.auth.user.AppUserRole;

@Service
public class RegistrationService {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    public AppUserDetails register(RegistrationRequest request) throws IllegalStateException {

        return appUserDetailsService.signUpUser(
            new AppUserDetails(
                request.getUsername(),
                request.getPassword(),
                AppUserRole.USER
            )
        );
 
    }

}
