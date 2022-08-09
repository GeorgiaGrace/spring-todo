package dev.cameronsims.springtodo.security.auth.user;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) 
        throws UsernameNotFoundException {
    
        return userRepository.findByUsernameIgnoreCase(username)
                    .orElseThrow( () -> new UsernameNotFoundException("Username, " + username + ", not found."));

    }

    public AppUserDetails signUpUser( AppUserDetails user ) throws IllegalStateException {

        boolean userExists = userRepository.findByUsernameIgnoreCase(user.getUsername())
            .isPresent();

        if ( userExists ) {
            throw new IllegalStateException("Username taken");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        return userRepository.save(user);


    }
    
}
