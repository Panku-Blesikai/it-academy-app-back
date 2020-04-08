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

    MongoClient mongo = new MongoClient(new MongoClientURI(Constants.DB_URI));
    DB db = mongo.getDB(Constants.DB_NAME);
    DBCollection collection = db.getCollection(Constants.COLLECTION_ADMIN);

    public AdminService() {
    }

    @Autowired
    AdminRepository adminRepository;

    public Admin checkLoginCredentials(String username, String password) throws IncorrectDataException {
        Admin candidate = findAdminByUsername(username);
        if (!BCrypt.checkpw(password, candidate.getPassword())) {
            throw new IncorrectDataException("Incorrect password");
        }
        return candidate;
    }

    public Admin findAdminByUsername(String username) throws IncorrectDataException {
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(query);
        if (dbObject == null)
            throw new IncorrectDataException("Incorrect username");
        return mapAdmin(dbObject);
    }

    public Admin mapAdmin(BasicDBObject basicDBObject) {
        return new Admin(basicDBObject.getString("_id"),
                basicDBObject.getString("username"),
                basicDBObject.getString("password"),
                basicDBObject.getString("name"),
                basicDBObject.getString("surname"));
    }

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