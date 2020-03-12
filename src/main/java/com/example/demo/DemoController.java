package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    public static final String BE_SUCCESS_MESSAGE = "BE works ";

    @Autowired
    DemoRepository repo;

    @GetMapping(value = "/")
    public String helloWorld() {
        StringBuilder result = new StringBuilder(BE_SUCCESS_MESSAGE);

        List<Message> messages = repo.findAll();
        Optional<Message> message = messages.stream().findFirst();
        message.ifPresent(value -> result.append(value.getMessage()));

        return result.toString();
    }
}
