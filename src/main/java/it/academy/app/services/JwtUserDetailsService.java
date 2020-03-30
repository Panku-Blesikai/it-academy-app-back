package it.academy.app.services;

import it.academy.app.exception.IncorrectDataException;
import it.academy.app.models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = null;
        try {
            admin = adminService.findAdminByUsername(username);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }
        return new User(admin.getUsername(), admin.getPassword(),
                new ArrayList<>());
    }
}