package com.example.demo;

import com.example.demo.admin.Admin;
import com.example.demo.admin.AdminService;
import com.example.demo.exception.IncorrectDataException;
import com.example.demo.applicationForm.*;
import com.google.gson.Gson;
import com.example.demo.validator.ApplicationFormValidator;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {


    ApplicationFormService applicationFormService = new ApplicationFormService();
    AdminService adminService = new AdminService();


    @GetMapping(value = "/get/all")
    public List<ApplicationForm> getAllApplications() {
        return applicationFormService.allApplications();
    }

    @GetMapping(value = "/get/application/{id}")
    public ApplicationForm getApplicationFormById(@PathVariable("id") ObjectId id) throws IncorrectDataException {
        return applicationFormService.findById(id);
    }

    @PostMapping(value = "/admin/registration")
    @ResponseBody
    public Admin createAdmin(@RequestBody Admin admin) throws Exception {
        return adminService.create(admin);
    }

    @PostMapping(value = "/admin/login")
    @ResponseBody
    public Admin loginAdmin(@RequestBody String logInfo) throws Exception {
        Gson parser = new Gson();
        Admin input = parser.fromJson(logInfo, Admin.class);
        return adminService.login(input.getEmail(),input.getPassword());
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ApplicationForm addApplication(@RequestBody ApplicationForm applicationForm) {
        ApplicationFormValidator validator = new ApplicationFormValidator();
        validator.validate(applicationForm);
        return applicationFormService.createNewForm(applicationForm);
    }

}
