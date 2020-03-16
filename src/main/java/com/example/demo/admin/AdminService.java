package com.example.demo.admin;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;

public class AdminService {

    public AdminService() {
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AdminRepository adminRepository;

    public String hashPassword(String loginPass) throws NoSuchAlgorithmException {
            String originalPassword = loginPass;
            String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
//            System.out.println(generatedSecuredPasswordHash);

            boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//            System.out.println(matched);
        return  generatedSecuredPasswordHash;
    }
}