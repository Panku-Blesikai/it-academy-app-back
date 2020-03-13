package com.example.demo;

import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    DemoRepository repo;

//    @GetMapping(value = "/")
//    public String helloWorld() {
//        StringBuilder result = new StringBuilder(BE_SUCCESS_MESSAGE);
//
//        List<ApplicationForm> applicationForms = repo.findAll();
//        Optional<ApplicationForm> message = applicationForms.stream().findFirst();
//        message.ifPresent(value -> result.append(value.getMessage()));
//
//        return result.toString();
//    }

    @GetMapping(value = "/getAll")
    public List<ApplicationForm> getAllApplications() {
        return repo.findAll();
    }

    @GetMapping(value = "/add")
    public ApplicationForm add() {
        return repo.save(new ApplicationForm("email", "edu", "a", "email", "edu", "test", "test", "test", "test", "test"));
    }
}
