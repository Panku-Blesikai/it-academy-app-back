package com.example.demo;

import com.example.demo.admin.Admin;
import com.example.demo.admin.AdminRepository;
import com.example.demo.admin.AdminService;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    ApplicationFormRepository applicationFormRepository;

    @Autowired
    AdminRepository adminRepository;


    MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://admin:pankublesikai1@ds161346.mlab.com:61346/heroku_6b64t1nj"));
    DB db = mongo.getDB("heroku_6b64t1nj");
    DBCollection collection = db.getCollection("admin");

    @GetMapping(value = "/getAll")
    public List<ApplicationForm> getAllApplications() {
        return applicationFormRepository.findAll();
    }

    @RequestMapping(value = "/get/{id}")
    public Optional<ApplicationForm> getApplicationFormByName(@PathVariable("id") String id) {
        return applicationFormRepository.findById(id);
    }

    @GetMapping(value = "/admin/registration")
    @ResponseBody
    public Admin createAdmin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        AdminService adminService = new AdminService();
        admin.setPassword(adminService.hashPassword(admin.getPassword()));
        return adminRepository.save(new Admin(admin.getLogin(),admin.getPassword(),admin.getName(),admin.getSurname()));
    }

    @GetMapping(value = "/admin/login")
    @ResponseBody
    public DBObject loginAdmin(@RequestBody Admin loginAdmin) throws NoSuchAlgorithmException {
        AdminService adminService = new AdminService();
        Admin admin = new Admin();
        admin.setLogin(loginAdmin.getLogin());
        admin.setPassword(adminService.hashPassword(loginAdmin.getPassword()));
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("login", admin.getLogin());
//        whereQuery.put("password", admin.getPassword());
        DBObject dbObject = collection.findOne(whereQuery);
        return dbObject;
    }

    @GetMapping(value = "/add")
    public ApplicationForm add() {
        return applicationFormRepository.save(new ApplicationForm("emaffil", "edu", "a", "email", "edu", "test", "test", "test", "test", "test"));
    }



}
