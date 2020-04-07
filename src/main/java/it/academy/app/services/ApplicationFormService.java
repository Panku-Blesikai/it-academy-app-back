package it.academy.app.services;

import com.mongodb.*;
import it.academy.app.exception.IdNotFoundException;
import it.academy.app.exception.IncorrectDataException;
import it.academy.app.models.ApplicationForm;
import it.academy.app.parser.ParserForApplicationFormAttributes;
import it.academy.app.repositories.ApplicationFormRepository;
import it.academy.app.shared.Constants;
import it.academy.app.shared.Status;
import it.academy.app.validators.ApplicationFormValidator;
import it.academy.app.validators.StatusChangeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ApplicationFormService {

    private MongoClient mongo = new MongoClient(new MongoClientURI(Constants.DB_URI));
    private DB db = mongo.getDB(Constants.DB_NAME);
    private DBCollection collection = db.getCollection(Constants.COLLECTION_APPLICATIONFORM);
    private DateFormat dateFormat;
    private HashService hashService;


    public ApplicationFormService() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hashService = new HashService();
    }

    @Autowired
    ApplicationFormRepository applicationFormRepository;

    public ApplicationForm findApplicationFormByIdHash(String idHash) throws IdNotFoundException {
        BasicDBObject query = new BasicDBObject();
        query.put("idHash", idHash);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(query);
        if (dbObject == null){
            throw new IdNotFoundException("Incorrect id");
        }
        return mapApplicationForm(dbObject);
    }

    public List<ApplicationForm> getAllApplications() {
        DBCursor cursor = collection.find();
        List<ApplicationForm> applicationForms = new ArrayList<>();
        while (cursor.hasNext()) {
            applicationForms.add(mapApplicationForm((BasicDBObject) cursor.next()));
        }
        return applicationForms;
    }

    public BasicDBObject changeApplicationFormStatus(ApplicationForm applicationForm) {
        StatusChangeValidator validator = new StatusChangeValidator();
        validator.checkIsStatusInProgress(findApplicationFormByIdHash(applicationForm.getIdHash()).getStatus());
        BasicDBObject searchQuery = new BasicDBObject().append("idHash", applicationForm.getIdHash());
        BasicDBObject newStatus = new BasicDBObject().append("status", applicationForm.getStatus());
        BasicDBObject newDocument = new BasicDBObject().append("$set", newStatus);
        collection.update(searchQuery, newDocument);
        return newDocument;
    }

    public BasicDBObject addComment(ApplicationForm applicationForm) throws IncorrectDataException {
        BasicDBObject searchQuery = new BasicDBObject().append("idHash", applicationForm.getIdHash());
        BasicDBObject newComments = new BasicDBObject().append("comments", applicationForm.getComments());
        BasicDBObject newDocument = new BasicDBObject().append("$set", newComments);
        collection.update(searchQuery, newDocument);
        return newDocument;
    }

    public BasicDBObject addNewApplicationFormToDB(ApplicationForm applicationForm) {
        ApplicationFormValidator validator = new ApplicationFormValidator();
        validator.validate(applicationForm);
        ParserForApplicationFormAttributes parserForApplicationFormAttributes = new ParserForApplicationFormAttributes();
        applicationForm = parserForApplicationFormAttributes.parse(applicationForm);
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
        formToAdd.put("status", Status.INPROGRESS.getStatusInLithuanian());
        List<BasicDBObject> comments = new ArrayList<>();
        formToAdd.put("comments", comments);
        String currentDateTime = dateFormat.format(Date.from(java.time.ZonedDateTime
                .now(ZoneId.of(Constants.LT_ZONE)).toInstant()));
        formToAdd.put("dateTime", currentDateTime);
        String uniqueId = currentDateTime.concat(applicationForm.getEmail());
        formToAdd.put("idHash", hashService.getHash(uniqueId));
        collection.save(formToAdd);
        return formToAdd;
    }

    public ApplicationForm mapApplicationForm(BasicDBObject basicDBObject) {
        ApplicationForm applicationForm = new ApplicationForm();
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
        applicationForm.setComments((List<BasicDBObject>) basicDBObject.get("comments"));
        return applicationForm;
    }
}