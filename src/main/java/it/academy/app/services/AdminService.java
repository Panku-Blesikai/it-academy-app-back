package it.academy.app.services;

import com.mongodb.*;
import it.academy.app.exception.IncorrectDataException;
import it.academy.app.models.Admin;
import it.academy.app.repositories.AdminRepository;
import it.academy.app.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService implements UserDetailsService {

    MongoClient mongo = new MongoClient(new MongoClientURI(System.getenv(Constants.DB_URI)));
    DB db = mongo.getDB(System.getenv(Constants.DB_NAME));
    DBCollection collection = db.getCollection(System.getenv(Constants.COLLECTION_ADMIN));

    public AdminService() {
    }

    @Autowired
    AdminRepository adminRepository;

    //add test
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(Constants.LOG_ROUNDS));
    }
    //add test
    public Admin checkLoginCredentials(String username, String password) throws IncorrectDataException {
        Admin candidate = findAdminByUsername(username);
        if (!BCrypt.checkpw(password, candidate.getPassword())) {
            throw new IncorrectDataException("Incorrect password");
        }
        return candidate;
    }
    //add test
    public Admin findAdminByUsername(String username) throws IncorrectDataException {
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(query);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect username");
        return setAdmin(dbObject);
    }

    public Admin create(Admin admin) {
        AdminService adminService = new AdminService();
        admin.setPassword(adminService.hashPassword(admin.getPassword()));
        BasicDBObject adminToAdd = new BasicDBObject();
        adminToAdd.put("username", admin.getUsername());
        adminToAdd.put("password", admin.getPassword());
        adminToAdd.put("name", admin.getName());
        adminToAdd.put("surname", admin.getSurname());
        adminToAdd.put("role", admin.getRole());
        collection.save(adminToAdd);
        return setAdmin(adminToAdd);
    }
    //add test
    public Admin setAdmin(BasicDBObject basicDBObject) {
        Admin admin = new Admin();
        admin.setId(basicDBObject.getString("_id"));
        admin.setUsername(basicDBObject.getString("username"));
        admin.setPassword(basicDBObject.getString("password"));
        admin.setName(basicDBObject.getString("name"));
        admin.setSurname(basicDBObject.getString("surname"));
        admin.setRole(basicDBObject.getString("role"));
        return admin;
    }
    //add test
    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin;
        try {
            admin = findAdminByUsername(username);
        } catch (IncorrectDataException e) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(admin.getUsername(), admin.getPassword(),
                new ArrayList<>());
    }
}