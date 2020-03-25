package it.academy.app.repositories;

import it.academy.app.models.ApplicationForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepository extends MongoRepository<ApplicationForm, String>{
}