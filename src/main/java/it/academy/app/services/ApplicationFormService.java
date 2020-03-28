package it.academy.app.services;

import com.mongodb.*;
import it.academy.app.exception.IncorrectDataException;
import it.academy.app.models.ApplicationForm;
import it.academy.app.repositories.ApplicationFormRepository;
import it.academy.app.shared.Constants;
import it.academy.app.validators.StatusChangeValidator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@Service
public class ApplicationFormService {

    private MongoClient mongo = new MongoClient(new MongoClientURI(System.getenv(Constants.DB_URI)));
    private DB db = mongo.getDB(System.getenv(Constants.DB_NAME));
    private DBCollection collection = db.getCollection(System.getenv(Constants.COLLECTION_APPLICATIONFORM));
    private DateFormat dateFormat;
    private HashService hashService;


    public ApplicationFormService() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hashService = new HashService();
    }



    @Autowired
    ApplicationFormRepository applicationFormRepository;

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
            Message message = setupMessage(session, applicationForm);
            Transport.send(message);
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

    public Message setupMessage(Session session, ApplicationForm applicationForm) throws MessagingException {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(Constants.EMAIL));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(applicationForm.getEmail(), false));
        message.setSubject("IT academy");
        message.setText("Dear " + applicationForm.getName() + " " + applicationForm.getSurname() + ",\n" +
                "\n" +
                "Thank you for participation, you can find your application here:\n" +
                "\n" +
                "https://it-academy-app-front.herokuapp.com/applications/" + applicationForm.getIdHash() + "\n" +
                "\n" +
                "Best Regards, IT academy");
        message.setSentDate(new Date());
        return message;
    }

    public ApplicationForm findApplicationFormById(ObjectId id) throws IncorrectDataException {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(query);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect id");
        return setApplicationForm(dbObject);
    }

    public ApplicationForm findApplicationFormByIdHash(String id) throws IncorrectDataException {
        BasicDBObject query = new BasicDBObject();
        query.put("idHash", id);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(query);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect id");
        return setApplicationForm(dbObject);
    }

    public List<ApplicationForm> getAllApplications() {
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
        validator.checkIsStatusInProgress(findApplicationFormById(objectId).getStatus());
        BasicDBObject searchQuery = new BasicDBObject().append("_id", objectId);
        BasicDBObject newStatus = new BasicDBObject().append("status", applicationForm.getStatus());
        BasicDBObject newDocument = new BasicDBObject().append("$set", newStatus);
        collection.update(searchQuery, newDocument);
        return findApplicationFormById(objectId);
    }

    public ApplicationForm createNewForm(ApplicationForm applicationForm) {
        BasicDBObject formToAdd = new BasicDBObject();
        formToAdd.put("email", applicationForm.getEmail());
        formToAdd.put("education", applicationForm.getEducation());
        formToAdd.put("name", applicationForm.getName());
        formToAdd.put("surname", applicationForm.getSurname());
        formToAdd.put("phone", applicationForm.getPhone());
        formToAdd.put("freeTimeActivity", applicationForm.getFreeTimeActivity());
        formToAdd.put("threePartyAgreement", applicationForm.getThreePartyAgreement());
        formToAdd.put("available14To18", applicationForm.getAvailable14To18());
        formToAdd.put("motivation", applicationForm.getMotivation());
        formToAdd.put("experience", applicationForm.getExperience());
        formToAdd.put("infoAboutAcademy", applicationForm.getInfoAboutAcademy());
        formToAdd.put("status", "PERŽIŪRIMA");
        String currentDateTime = dateFormat.format(new Date());
        formToAdd.put( "dateTime", currentDateTime);
        String uniqueId = currentDateTime.concat(applicationForm.getEmail());
        formToAdd.put("idHash", hashService.getHash(uniqueId));
        collection.save(formToAdd);
        sendMail(setApplicationForm(formToAdd));
        return setApplicationForm(formToAdd);
    }


    public ApplicationForm setApplicationForm(BasicDBObject basicDBObject) {
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setId(basicDBObject.getString("_id"));
        applicationForm.setEmail(basicDBObject.getString("email"));
        applicationForm.setName(basicDBObject.getString("name"));
        applicationForm.setSurname(basicDBObject.getString("surname"));
        applicationForm.setPhone(basicDBObject.getString("phone"));
        applicationForm.setEducation(basicDBObject.getString("education"));
        applicationForm.setFreeTimeActivity(basicDBObject.getString("freeTimeActivity"));
        applicationForm.setThreePartyAgreement(basicDBObject.getString("threePartyAgreement"));
        applicationForm.setAvailable14To18(basicDBObject.getString("available14To18"));
        applicationForm.setMotivation(basicDBObject.getString("motivation"));
        applicationForm.setExperience(basicDBObject.getString("experience"));
        applicationForm.setInfoAboutAcademy(basicDBObject.getString("infoAboutAcademy"));
        applicationForm.setStatus(basicDBObject.getString("status"));
        applicationForm.setDateTime(basicDBObject.getString("dateTime"));
        applicationForm.setIdHash(basicDBObject.getString("idHash"));
        return applicationForm;
    }
}