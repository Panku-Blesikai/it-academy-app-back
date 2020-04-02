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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    @Autowired
    EmailService emailService;

    //add test
    public ApplicationForm findApplicationFormById(ObjectId id) throws IncorrectDataException {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(query);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect id");
        return setApplicationForm(dbObject);
    }

    //add test
    public ApplicationForm findApplicationFormByIdHash(String id) throws IncorrectDataException {
        BasicDBObject query = new BasicDBObject();
        query.put("idHash", id);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(query);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect id");
        return setApplicationForm(dbObject);
    }

    //add test
    public List<ApplicationForm> getAllApplications() {
        DBCursor cursor = collection.find();
        List<ApplicationForm> applicationForms = new ArrayList<>();
        while (cursor.hasNext()) {
            applicationForms.add(setApplicationForm((BasicDBObject) cursor.next()));
        }
        return applicationForms;
    }

    //add test
    public ApplicationForm changeStatus(ApplicationForm applicationForm) throws IncorrectDataException {
        ObjectId objectId = new ObjectId(applicationForm.getId());
        StatusChangeValidator validator = new StatusChangeValidator();
        validator.checkIsStatusInProgress(findApplicationFormById(objectId).getStatus());
        BasicDBObject searchQuery = new BasicDBObject().append("_id", objectId);
        BasicDBObject newStatus = new BasicDBObject().append("status", applicationForm.getStatus());
        BasicDBObject newDocument = new BasicDBObject().append("$set", newStatus);
        collection.update(searchQuery, newDocument);
        ApplicationForm applicationFormWithNewStatus = findApplicationFormById(objectId);
        if (applicationFormWithNewStatus.getStatus().equals("PATVIRTINTA")) {
            emailService.sendMail(applicationFormWithNewStatus);
        }
        return applicationFormWithNewStatus;
    }
    //add test
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
        String currentDateTime = dateFormat.format(Date.from(java.time.ZonedDateTime.now().toInstant()));
        formToAdd.put("dateTime", currentDateTime);
        String uniqueId = currentDateTime.concat(applicationForm.getEmail());
        formToAdd.put("idHash", hashService.getHash(uniqueId));
        collection.save(formToAdd);
        emailService.sendMail(setApplicationForm(formToAdd));
        return setApplicationForm(formToAdd);
    }

    //add test
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