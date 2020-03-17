package com.example.demo.admin;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AdminService {

    MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://admin:pankublesikai1@ds161346.mlab.com:61346/heroku_6b64t1nj?retryWrites=false"));
    DB db = mongo.getDB("heroku_6b64t1nj");
    DBCollection collection = db.getCollection("admin");

    public AdminService() {
    }


    @Autowired
    AdminRepository adminRepository;

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public Admin login(String email, String password) throws Exception {
        Admin candidate = findUserByEmail(email);
        if (!BCrypt.checkpw(password, candidate.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        return candidate;
    }

    public Admin findUserByEmail(String logEmail) throws IncorrectEmailException {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("email", logEmail);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(whereQuery);
        if (dbObject == null)
            throw new IncorrectEmailException("Incorrect email");
        return setAdmin(dbObject);
    }

    public Admin create(Admin admin) throws Exception {
        AdminService adminService = new AdminService();
        admin.setPassword(adminService.hashPassword(admin.getPassword()));
        BasicDBObject adminToAdd = new BasicDBObject();
        adminToAdd.put("email", admin.getEmail());
        adminToAdd.put("password", admin.getPassword());
        adminToAdd.put("name", admin.getName());
        adminToAdd.put("surname", admin.getSurname());
        collection.save(adminToAdd);
        return  setAdmin(adminToAdd);
    }
    public Admin setAdmin(BasicDBObject basicDBObject){
        String id = basicDBObject.getString("_id");
        String email = basicDBObject.getString("email");
        String password = basicDBObject.getString("password");
        String name = basicDBObject.getString("name");
        String surname = basicDBObject.getString("surname");
        Admin admin = new Admin();
        admin.setId(id);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setName(name);
        admin.setSurname(surname);
        return admin;
    }
}