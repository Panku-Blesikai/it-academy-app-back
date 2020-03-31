package it.academy.app.services;

import it.academy.app.models.ApplicationForm;
import it.academy.app.shared.Constants;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    public EmailService() {
    }

    public void sendMail(ApplicationForm applicationForm) {
        Properties props = setupProps();
        final String username = Constants.EMAIL;
        final String password = System.getenv("EMAIL_PASS");
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            if (applicationForm.getStatus().equals("PERŽIŪRIMA")) {
                Message message = setupParticipationMessage(session, applicationForm);
                Transport.send(message);
            } else {
                Message message = setupSuccessMessage(session, applicationForm);
                Transport.send(message);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Properties setupProps() {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        return props;
    }


    public Message setupParticipationMessage(Session session, ApplicationForm applicationForm) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(Constants.EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(applicationForm.getEmail(), false));
        message.setSubject("IT akademija");
        message.setText("Labas " + applicationForm.getName() + " " + applicationForm.getSurname() + ",\n" +
                "\n" +
                "Ačiū už registraciją, savo registracijos anketą gali rasti čia:\n" +
                "\n" +
                "https://it-academy-app-front.herokuapp.com/application/" + applicationForm.getIdHash() + "\n" +
                "\n" +
                "Pagarbiai" + "\n" +
                "IT akademijos komanda");
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
