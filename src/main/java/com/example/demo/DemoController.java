package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/get/{id}")
    public Optional<ApplicationForm> getApplicationFormByName(@PathVariable("id") String id) {
        return repo.findById(id);
    }

    @GetMapping(value = "/add")
    public ApplicationForm add() {
        return repo.save(new ApplicationForm("emaffil", "edu", "a", "email", "edu", "test", "test", "test", "test", "test"));
    }



}
