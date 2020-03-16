package com.example.demo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationFormRepository extends MongoRepository<ApplicationForm, String> {
    ApplicationForm findById(ObjectId _id);
}
