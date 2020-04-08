package it.academy.app;

import com.mongodb.BasicDBObject;
import it.academy.app.models.ApplicationForm;
import it.academy.app.models.Comment;
import it.academy.app.services.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(StartApplication.class, args);
        /*EmailService emailService = new EmailService();
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setEmail("ingvaras.galinskas@gmail.com");
        applicationForm.setName("ingvaras");
        applicationForm.setSurname("galinskas");
        applicationForm.setIdHash("6819f7e32958ccb9e554e2a217797e5fc6d675b7a935888939a8e79f66ad50c0");
        Comment comment = new Comment("Pankublesikai", "Nbelogas kadidatas", "2020-04-08 15:22:35");
        List<BasicDBObject> comments = new ArrayList<>();
        comments.add(new BasicDBObject().append("comments", comment));
        applicationForm.setComments(comments);
        emailService.sendCommentMail(applicationForm);*/
    }
}

