package it.academy.app.controllers;

import it.academy.app.admin.Admin;
import it.academy.app.services.AdminService;
import it.academy.app.form.ApplicationForm;
import it.academy.app.services.ApplicationFormService;
import it.academy.app.exception.IncorrectDataException;
import com.google.gson.Gson;
import it.academy.app.validators.ApplicationFormValidator;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
@RestController
public class WebController {


    ApplicationFormService applicationFormService = new ApplicationFormService();
    AdminService adminService = new AdminService();

    @RequestMapping("/login")
    public boolean login(@RequestBody Admin user) {
        return
                user.getName().equals("ADMIN_NAME") && user.getPassword().equals("ADMIN_PASS");
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @GetMapping(value = "/applications")
    public List<ApplicationForm> getAllApplications() {
        return applicationFormService.allApplications();
    }

    @GetMapping(value = "/applications/{idHash}")
    public ApplicationForm getApplicationFormById(@PathVariable("idHash") String id) throws IncorrectDataException {
        return applicationFormService.findByIdHash(id);
    }

    @PutMapping (value = "/applications")
    @ResponseBody
    public ApplicationForm changeStatus(@RequestBody ApplicationForm applicationForm) throws IncorrectDataException {
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
    public ApplicationForm addApplication(@RequestBody ApplicationForm applicationForm) {
        ApplicationFormValidator validator = new ApplicationFormValidator();
        validator.validate(applicationForm);
        return applicationFormService.createNewForm(applicationForm);
    }

}
