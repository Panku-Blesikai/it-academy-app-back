package it.academy.app;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends MongoRepository<Test, String> {

    public List<Test> findAll();
}