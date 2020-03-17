package com.example.demo.applicationForm;

import com.example.demo.admin.IncorrectDataException;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationFormService {

    MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://admin:pankublesikai1@ds161346.mlab.com:61346/heroku_6b64t1nj?retryWrites=false"));
    DB db = mongo.getDB("heroku_6b64t1nj");
    DBCollection collection = db.getCollection("applicationForm");

    public ApplicationFormService() {
    }


    @Autowired
    ApplicationFormRepository applicationFormRepository;

    public ApplicationForm findById(ObjectId id) throws IncorrectDataException {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", id);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(whereQuery);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect id");
        return setApplicationForm(dbObject);
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
        collection.save(formToAdd);
        return  setApplicationForm(formToAdd);
    }


    public ApplicationForm setApplicationForm(BasicDBObject basicDBObject) {
        ObjectId id = basicDBObject.getObjectId("_id");
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
        return applicationForm;
    }
}