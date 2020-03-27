package it.academy.app.controllers;

import it.academy.app.models.Admin;
import it.academy.app.services.AdminService;
import it.academy.app.models.ApplicationForm;
import it.academy.app.services.ApplicationFormService;
import it.academy.app.exception.IncorrectDataException;
import com.google.gson.Gson;
import it.academy.app.validators.ApplicationFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RestController
public class WebController {

//    @Autowired
    ApplicationFormService applicationFormService;

//    @Autowired
    AdminService adminService;

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

    @PostMapping(value = "/applications")
    @ResponseBody
    public ApplicationForm addApplication(@RequestBody @Valid ApplicationForm applicationForm) {
        ApplicationFormValidator validator = new ApplicationFormValidator();
        validator.validate(applicationForm);
        return applicationFormService.createNewForm(applicationForm);
    }

    //galbut vystysim
    @PostMapping(value = "/admin/registration")
    @ResponseBody
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

//    @RequestMapping(value = "/login")
//    public boolean login(@RequestBody String logInfo) {
//        Gson parser = new Gson();
//        Admin input = parser.fromJson(logInfo, Admin.class);
//        return input.getName().equals(System.getenv("ADMIN_NAME")) && input.getPassword().equals(System.getenv("ADMIN_PASS"));
//    }
//
//    @GetMapping("/user")
//    @ResponseBody
//    public Principal user(Principal user) {
//        return user;
//    }
//
    @GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public Admin validateLogin() {
		return new Admin("User successfully authenticated");
	}

}
