package it.academy.app.services;

import it.academy.app.models.ApplicationForm;
import it.academy.app.shared.Constants;
import it.academy.app.shared.Status;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    Properties props = new Properties();

    public EmailService() throws IOException {
        props.load(new FileInputStream("src/main/resources/email/config.properties"));
    }

    public void sendMail(ApplicationForm applicationForm){

        final String email = Constants.EMAIL;
        final String password = System.getenv("EMAIL_PASS");
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, password);
                        }
                    });
            if (applicationForm.getStatus().equals(Status.INPROGRESS.getStatusInLithuanian())) {
                Message message = setupParticipationMessage(session, applicationForm);
                Transport.send(message);
            } else if (applicationForm.getStatus().equals(Status.ACCEPTED.getStatusInLithuanian())) {
                Message message = setupSuccessMessage(session, applicationForm);
                Transport.send(message);
            }
        } catch (MessagingException e) {
            e.getMessage();
        }
    }

    public Message setupParticipationMessage(Session session, ApplicationForm applicationForm) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(Constants.EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(applicationForm.getEmail(), false));
        message.setSubject("IT akademija");
        message.setText("Labas " + applicationForm.getName() + " " + applicationForm.getSurname() +
                ",\n" + "\n" + "Ačiū už registraciją, savo registracijos anketą gali rasti čia:\n" + "\n" +
                "https://it-academy-app-front.herokuapp.com/application/" + applicationForm.getIdHash() + "\n" +
                        "\n" + "Pagarbiai" + "\n" + "IT akademijos komanda");
        message.setSentDate(new Date());
        return message;
    }

    public Message setupSuccessMessage(Session session, ApplicationForm applicationForm) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(Constants.EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(applicationForm.getEmail(), false));
        message.setSubject("IT akademija");
        message.setText("Labas " + applicationForm.getName() + " " + applicationForm.getSurname() + ",\n" +
                "\n" +
                "Tavo paraiška buvo patvirtinta. Dėl tolimesnio atrankos proceso susisieksime netrūkus." + "\n" +
                "\n" +
                "Pagarbiai" + "\n" +
                "IT akademijos komanda");
        message.setSentDate(new Date());
        return message;
    }

}
