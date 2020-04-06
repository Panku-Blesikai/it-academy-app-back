package it.academy.app.controllers;

import it.academy.app.exception.IncorrectDataException;
import it.academy.app.models.ApplicationForm;
import it.academy.app.services.AdminService;
import it.academy.app.services.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebController {

    @Autowired
    ApplicationFormService applicationFormService;

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/applications")
    public List<ApplicationForm> getAllApplications() {
        return applicationFormService.getAllApplications();
    }

    @GetMapping(value = "/applications/{idHash}")
    public ApplicationForm getApplicationFormById(@PathVariable("idHash") String idHash) {
            return applicationFormService.findApplicationFormByIdHash(idHash);
    }

    @PutMapping(value = "/applications")
    @ResponseBody
    public ApplicationForm changeStatus(@RequestBody @Valid ApplicationForm applicationForm) {
        return applicationFormService.changeApplicationFormStatus(applicationForm);
    }

    @PutMapping(value = "/applications/comment")
    @ResponseBody
    public ApplicationForm addComment(@RequestBody @Valid ApplicationForm applicationForm) throws IncorrectDataException {
        return applicationFormService.addComment(applicationForm);
    }

    @PostMapping(value = "/applications")
    @ResponseBody
    public ApplicationForm addApplication(@RequestBody @Valid ApplicationForm applicationForm) {
        return applicationFormService.addNewApplicationFormToDB(applicationForm);
    }

}
