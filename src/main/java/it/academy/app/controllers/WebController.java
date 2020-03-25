package it.academy.app.controllers;

import it.academy.app.models.Admin;
import it.academy.app.services.AdminService;
import it.academy.app.models.ApplicationForm;
import it.academy.app.services.ApplicationFormService;
import it.academy.app.exception.IncorrectDataException;
import com.google.gson.Gson;
import it.academy.app.validators.ApplicationFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.security.Principal;
import javax.validation.Valid;
import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class WebController {

//    @Autowired
    ApplicationFormService applicationFormService;

//    @Autowired
    AdminService adminService;

//    @RequestMapping("/login")
//    public boolean login(@RequestBody SecurityProperties.User user) {
//        return
//                user.getName().equals("ADMIN_NAME") && user.getPassword().equals("ADMIN_PASS");
//    }
    @RequestMapping("/login")
    public String login() {
        return "auth successful";
    }

    @GetMapping(value = "/applications")
    public List<ApplicationForm> getAllApplications() {
        return applicationFormService.getAllApplications();
    }

    @GetMapping(value = "/applications/{idHash}")
    public ApplicationForm getApplicationFormById(@PathVariable("idHash") String id) throws IncorrectDataException {
        return applicationFormService.findApplicationFormByIdHash(id);
    }

    @PutMapping(value = "/applications")
    @ResponseBody
    public ApplicationForm changeStatus(@RequestBody @Valid ApplicationForm applicationForm) throws IncorrectDataException {
        return applicationFormService.changeStatus(applicationForm);
    }

    //galbut vystysim
    @PostMapping(value = "/admin/registration")
    @ResponseBody
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

    @PostMapping(value = "/admin/login")
    @ResponseBody
    public Admin loginAdmin(@RequestBody String logInfo) throws Exception {
        Gson parser = new Gson();
        Admin input = parser.fromJson(logInfo, Admin.class);
        return adminService.login(input.getEmail(),input.getPassword());
    }

    @PostMapping(value = "/applications")
    @ResponseBody
    public ApplicationForm addApplication(@RequestBody @Valid ApplicationForm applicationForm) {
        ApplicationFormValidator validator = new ApplicationFormValidator();
        validator.validate(applicationForm);
        return applicationFormService.createNewForm(applicationForm);
    }

}
