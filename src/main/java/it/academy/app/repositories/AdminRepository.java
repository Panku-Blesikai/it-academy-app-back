package it.academy.app.repositories;

import it.academy.app.admin.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String>{
}

