package com.example.demo.applicationForm;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationFormRepository extends MongoRepository<ApplicationForm, String>{
}