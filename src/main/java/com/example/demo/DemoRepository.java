package com.example.demo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoRepository extends MongoRepository<ApplicationForm, String> {
    ApplicationForm findById(ObjectId _id);
}