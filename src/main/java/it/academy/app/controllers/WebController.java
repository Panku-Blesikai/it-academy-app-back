package it.academy.app.controllers;

import com.mongodb.BasicDBObject;
import it.academy.app.exception.IncorrectDataException;
import it.academy.app.models.ApplicationForm;
import it.academy.app.services.AdminService;
import it.academy.app.services.ApplicationFormService;
import it.academy.app.services.EmailService;
import it.academy.app.shared.Status;
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

    @Autowired
    EmailService emailService;

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
        applicationFormService.changeApplicationFormStatus(applicationForm);
        ApplicationForm applicationFormWithNewStatus =
                applicationFormService.findApplicationFormByIdHash(applicationForm.getIdHash());
        if (applicationFormWithNewStatus.getStatus().equals(Status.ACCEPTED.getStatusInLithuanian())) {
            emailService.sendMail(applicationFormWithNewStatus);
        }
        return applicationFormWithNewStatus;
    }

    @PutMapping(value = "/applications/comment")
    @ResponseBody
    public ApplicationForm addComment(@RequestBody @Valid ApplicationForm applicationForm) throws IncorrectDataException {
        applicationFormService.addComment(applicationForm);
        ApplicationForm applicationFormWithNewComments =
                applicationFormService.findApplicationFormByIdHash(applicationForm.getIdHash());
        // send email
        return applicationFormWithNewComments;
    }

    @PostMapping(value = "/applications")
    @ResponseBody
    public ApplicationForm addApplication(@RequestBody @Valid ApplicationForm applicationForm) {
        BasicDBObject newFormDBObject = applicationFormService.addNewApplicationFormToDB(applicationForm);
        ApplicationForm newForm = applicationFormService.mapApplicationForm(newFormDBObject);
        emailService.sendMail(newForm);
        return newForm;
    }

}
