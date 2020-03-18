package it.academy.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    public static final String BE_SUCCESS_MESSAGE = "BE works ";

    @Autowired
    private DemoRepository demoRepository;

    @GetMapping(value = "/")
    public String helloWorld() {
        StringBuilder result = new StringBuilder(BE_SUCCESS_MESSAGE);

        List<Test> tests = demoRepository.findAll();
        Optional<Test> name = tests.stream().findFirst();
        name.ifPresent(value -> result.append(value.getName()));

        return result.toString();
    }
}
