package com.example.demo;

import com.example.demo.admin.Admin;
import com.example.demo.admin.AdminService;
import com.google.gson.Gson;
import com.example.demo.validator.ApplicationFormValidator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    ApplicationFormRepository applicationFormRepository;


    @GetMapping(value = "/getAll")
    public List<ApplicationForm> getAllApplications() {
        return applicationFormRepository.findAll();
    }

    @RequestMapping(value = "/get/{id}")
    public Optional<ApplicationForm> getApplicationFormByName(@PathVariable("id") String id) {
        return applicationFormRepository.findById(id);
    }

    @PostMapping(value = "/admin/registration")
    @ResponseBody
    public Admin createAdmin(@RequestBody Admin admin) throws Exception {
        AdminService adminService = new AdminService();
        return adminService.create(admin);
    }

    @PostMapping(value = "/admin/login")
    @ResponseBody
    public Admin loginAdmin(@RequestBody String logInfo) throws Exception {
        AdminService adminService = new AdminService();
        Gson parser = new Gson();
        Admin input = parser.fromJson(logInfo, Admin.class);
        return adminService.login(input.getEmail(),input.getPassword());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ApplicationForm addApplication(@RequestBody ApplicationForm applicationForm) {
        ApplicationFormValidator validator = new ApplicationFormValidator();
        validator.validate(applicationForm);
        applicationForm.setId(ObjectId.get());
        return applicationFormRepository.save(applicationForm);
    }



}
