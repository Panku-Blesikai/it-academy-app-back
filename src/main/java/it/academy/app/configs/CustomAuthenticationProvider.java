package it.academy.app.configs;

import it.academy.app.exception.IncorrectDataException;
import it.academy.app.models.Admin;
import it.academy.app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminService adminService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Admin admin;
        List<GrantedAuthority> authorities = new ArrayList<>();

        try {
            admin = adminService.checkLoginCredentials(username, password);
            authorities.add(new SimpleGrantedAuthority(admin.getRole()));

        } catch (IncorrectDataException e) {
            throw new BadCredentialsException("User password is incorrect.");
        }
        return new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
