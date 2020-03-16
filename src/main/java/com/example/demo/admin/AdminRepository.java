package com.example.demo.admin;

import com.example.demo.ApplicationForm;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String>{
}

