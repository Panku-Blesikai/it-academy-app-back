package it.academy.app.services;

import com.mongodb.BasicDBObject;
import it.academy.app.models.ApplicationForm;
import it.academy.app.shared.Constants;
import it.academy.app.shared.Status;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    Properties properties = new Properties();

    public EmailService() throws IOException {
        properties.load(new FileReader("src/main/resources/email/config.properties"));
    }

    public void sendMail(ApplicationForm applicationForm) {

        final String email = Constants.EMAIL;
        final String password = System.getenv("EMAIL_PASS");
        try {
            Session session = Session.getDefaultInstance(properties,
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

    public void sendCommentMail(ApplicationForm applicationForm) {

        final String email = Constants.EMAIL;
        final String password = System.getenv("EMAIL_PASS");
        try {
            Session session = Session.getDefaultInstance(properties,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, password);
                        }
                    });
            Message message = setupCommentMessage(session, applicationForm);
            Transport.send(message);
        } catch (MessagingException e) {
            e.getMessage();
        }
    }

    public Message setupParticipationMessage(Session session, ApplicationForm applicationForm) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(Constants.EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(applicationForm.getEmail(), false));
        message.setSubject(properties.getProperty("message.subject"));
        String text = String.format(properties.getProperty("participation.message"), applicationForm.getName(),
                applicationForm.getSurname(), applicationForm.getIdHash());
        message.setText(text);
        message.setSentDate(new Date());
        return message;
    }

    public Message setupCommentMessage(Session session, ApplicationForm applicationForm) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Constants.EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(applicationForm.getEmail(), false));
        message.setSubject(properties.getProperty("message.subject"));
        BasicDBObject newComment = applicationForm.getComments().get(applicationForm.getComments().size() - 1);
        String text;
        if(newComment.getString("comment").contains("Kandidatui numatytas interviu laikas: ")) {
            text = String.format(properties.getProperty("interview.message"), applicationForm.getName(),
                    applicationForm.getSurname(), newComment.getString("comment"), applicationForm.getIdHash());
        } else {
            text = String.format(properties.getProperty("comment.message"), applicationForm.getName(),
                    applicationForm.getSurname(), newComment.getString("author"),
                    newComment.getString("comment"), applicationForm.getIdHash());
        }
        message.setText(text);
        message.setSentDate(new Date());
        return message;
    }

    public Message setupSuccessMessage(Session session, ApplicationForm applicationForm) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(Constants.EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(applicationForm.getEmail(), false));
        message.setSubject(properties.getProperty("message.subject"));
        String text = String.format(properties.getProperty("success.message"), applicationForm.getName(),
                applicationForm.getSurname(), applicationForm.getIdHash());
        message.setText(text);
        message.setSentDate(new Date());
        return message;
    }

}
