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
    }
}

