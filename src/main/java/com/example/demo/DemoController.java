package com.example.demo;


import com.example.demo.validator.ApplicationFormValidator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    DemoRepository repo;

    @GetMapping(value = "/getAll")
    public List<ApplicationForm> getAllApplications() {
        return repo.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ApplicationForm addApplication(@RequestBody ApplicationForm applicationForm) {
        ApplicationFormValidator validator = new ApplicationFormValidator();
        validator.validate(applicationForm);
        applicationForm.setId(ObjectId.get());
        return repo.save(applicationForm);
    }
}
