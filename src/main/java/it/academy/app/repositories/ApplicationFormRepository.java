package it.academy.app.repositories;

import it.academy.app.form.ApplicationForm;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationFormRepository extends MongoRepository<ApplicationForm, String>{
}