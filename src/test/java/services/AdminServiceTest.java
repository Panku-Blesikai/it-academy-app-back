package services;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.academy.app.repositories.AdminRepository;
import it.academy.app.services.AdminService;
import it.academy.app.shared.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.regex.Matcher;

import static org.mockito.Mockito.when;

public class AdminServiceTest {

//    @Mock
//    private MongoClient mockClient;
//    @Mock
//    private MongoCollection mockCollection;
//    @Mock
//    private MongoDatabase mockDB;
//
////    @InjectMocks
////    private DriverWrapper wrapper;
//
//    @Before
//    public void initMocks() {
//        when(mockClient.getDatabase(Mockito.anyString())).thenReturn(mockDB);
//        when(mockDB.getCollection(Mockito.anyString())).thenReturn(mockCollection);
////        wrapper.init();
//        MockitoAnnotations.initMocks(this);
//    }
//
//    AdminService adminService = new AdminService();
////
////    @Mock
////    MongoClient mongo = new MongoClient(new MongoClientURI(Matchers.anyString()));
//
//
//
//    @Test
//    public void ShouldReturnNotTheSameValue(){
//        AdminRepository adminRepository = Mockito.mock(AdminRepository.class);
//        MongoClientURI mongoClientURI = Mockito.mock(MongoClientURI.class);
//        MongoClient newMongo = new MongoClient(mongoClientURI);
////        MongoClient mongo = Mockito.mock(MongoClient.class);
//        DB db = Mockito.mock(DB.class);
//        DBCollection dbCollection = Mockito.mock(DBCollection.class);
//        when(newMongo.getDB("foo")).thenReturn(db);
//        when(db.getCollection("bar")).thenReturn(dbCollection);
//
//        String result = adminService.hashPassword("password");
//        Assert.assertNotEquals(result, "password");
//    }
}
