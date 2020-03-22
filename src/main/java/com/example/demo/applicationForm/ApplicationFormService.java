package com.example.demo.applicationForm;

import com.example.demo.exception.IncorrectDataException;
import com.example.demo.validator.StatusChangeValidator;
import com.mongodb.*;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.net.www.ApplicationLaunchException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ApplicationFormService {

    MongoClient mongo = new MongoClient(new MongoClientURI(System.getenv("MONGODB_URI")));
    DB db = mongo.getDB("heroku_6b64t1nj");
    DBCollection collection = db.getCollection("applicationForm");
    DateFormat dateFormat;


    public ApplicationFormService() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


    @Autowired
    ApplicationFormRepository applicationFormRepository;

    public void sendMail(ApplicationForm applicationForm) {
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
        final String username = "pankublesikai@gmail.com";
        final String password = System.getenv("EMAIL_PASS");
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("pankublesikai@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(applicationForm.getEmail(), false));
            message.setSubject("IT academy");
            message.setText("Dear " + applicationForm.getName() + " " + applicationForm.getSurname() + ",\n" +
                    "\n" +
                    "Thank you for participation, you can find your application here:\n" +
                    "\n" +
                    "https://it-academy-app-front.herokuapp.com/admin/applications/" + applicationForm.getId() + "\n" +
                    "\n" +
                    "Best Regards, IT academy");
            message.setSentDate(new Date());
            Transport.send(message);
//            System.out.println("Message sent.");
        } catch (MessagingException e) {
            System.out.println("Error, cause: " + e);
        }
    }

    public ApplicationForm findById(ObjectId id) throws IncorrectDataException {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", id);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(whereQuery);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect id");
        return setApplicationForm(dbObject);
    }

    public List<ApplicationForm> allApplications() {
        DBCursor cursor = collection.find();
        List<ApplicationForm> applicationForms = new ArrayList<>();
        while (cursor.hasNext()) {
            applicationForms.add(setApplicationForm((BasicDBObject) cursor.next()));
        }
        return applicationForms;
    }

    public ApplicationForm changeStatus(ApplicationForm applicationForm) throws IncorrectDataException {
        ObjectId objectId = new ObjectId(applicationForm.getId());
        StatusChangeValidator validator = new StatusChangeValidator();
        validator.checkIsStatusInProgress(findById(objectId).getStatus());
        BasicDBObject searchQuery = new BasicDBObject().append("_id", objectId);
        BasicDBObject newStatus = new BasicDBObject().append("status", applicationForm.getStatus());
        BasicDBObject newDocument = new BasicDBObject().append("$set", newStatus);
        collection.update(searchQuery, newDocument);
        return findById(objectId);
    }

    public ApplicationForm createNewForm(ApplicationForm applicationForm) {
        BasicDBObject formToAdd = new BasicDBObject();
        formToAdd.put("email", applicationForm.getEmail());
        formToAdd.put("education", applicationForm.getEducation());
        formToAdd.put("name", applicationForm.getName());
        formToAdd.put("surname", applicationForm.getSurname());
        formToAdd.put("tel", applicationForm.getTel());
        formToAdd.put("answerFreeTimeActivity", applicationForm.getAnswerFreeTimeActivity());
        formToAdd.put("answerThreePartAgreement", applicationForm.getAnswerThreePartAgreement());
        formToAdd.put("answerAvailable14To18", applicationForm.getAnswerAvailable14To18());
        formToAdd.put("answerMotivation", applicationForm.getAnswerMotivation());
        formToAdd.put("answerExperience", applicationForm.getAnswerExperience());
        formToAdd.put("answerInfoAboutAcademy", applicationForm.getAnswerInfoAboutAcademy());
        formToAdd.put("status", Status.INPROGRESS.name());
        formToAdd.put( "dateTime", dateFormat.format(new Date()));
        collection.save(formToAdd);
        sendMail(setApplicationForm(formToAdd));
        return setApplicationForm(formToAdd);
    }


    public ApplicationForm setApplicationForm(BasicDBObject basicDBObject) {
        String id = basicDBObject.getString("_id");
        String email = basicDBObject.getString("email");
        String education = basicDBObject.getString("education");
        String name = basicDBObject.getString("name");
        String surname = basicDBObject.getString("surname");
        String tel = basicDBObject.getString("tel");
        String answerFreeTimeActivity = basicDBObject.getString("answerFreeTimeActivity");
        String answerThreePartAgreement = basicDBObject.getString("answerThreePartAgreement");
        String answerAvailable14To18 = basicDBObject.getString("answerAvailable14To18");
        String answerMotivation = basicDBObject.getString("answerMotivation");
        String answerExperience = basicDBObject.getString("answerExperience");
        String answerInfoAboutAcademy = basicDBObject.getString("answerInfoAboutAcademy");
        String status = basicDBObject.getString("status");
        String dateTime = basicDBObject.getString("dateTime");
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setId(id);
        applicationForm.setEmail(email);
        applicationForm.setName(name);
        applicationForm.setSurname(surname);
        applicationForm.setTel(tel);
        applicationForm.setEducation(education);
        applicationForm.setAnswerFreeTimeActivity(answerFreeTimeActivity);
        applicationForm.setAnswerThreePartAgreement(answerThreePartAgreement);
        applicationForm.setAnswerAvailable14To18(answerAvailable14To18);
        applicationForm.setAnswerMotivation(answerMotivation);
        applicationForm.setAnswerExperience(answerExperience);
        applicationForm.setAnswerInfoAboutAcademy(answerInfoAboutAcademy);
        applicationForm.setStatus(status);
        applicationForm.setDateTime(dateTime);
        return applicationForm;
    }
}