package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Traveler;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Capstone Project
 * @author garrettbecker
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TravelerRepositoryTest {
    
    @Autowired
    TravelerRepository traveler;
    
    public TravelerRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Traveler> allT = traveler.findAll();
        for (Traveler t : allT) {
            traveler.delete(t);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testFindAllTravelers() {
        Traveler newT1 = new Traveler();
        newT1.setFirstName("First1");
        newT1.setLastName("Last1");
        newT1.setAddress("A1");
        newT1.setCity("C1");
        newT1.setState("S1");
        newT1.setZip("123");
        newT1.setBirthDate(null);
        newT1.setPhotoFilePath("P1");
        newT1.setPhotoLink("L1");
        newT1.setTrips(null);
        traveler.save(newT1);
        assertEquals(traveler.findAll().size(), 1);
        
        Traveler newT2 = new Traveler();
        newT2.setFirstName("First1");
        newT2.setLastName("Last1");
        newT2.setAddress("A1");
        newT2.setCity("C1");
        newT2.setState("S1");
        newT2.setZip("123");
        newT2.setBirthDate(null);
        newT2.setPhotoFilePath("P1");
        newT2.setPhotoLink("L1");
        newT2.setTrips(null);
        traveler.save(newT2);
        assertEquals(traveler.findAll().size(), 2);
    }
    
    @Test
    public void testFindTravelerByID() {
        Traveler newT1 = new Traveler();
        newT1.setFirstName("First1");
        newT1.setLastName("Last1");
        newT1.setAddress("A1");
        newT1.setCity("C1");
        newT1.setState("S1");
        newT1.setZip("123");
        newT1.setBirthDate(null);
        newT1.setPhotoFilePath("P1");
        newT1.setPhotoLink("L1");
        newT1.setTrips(null);
        traveler.save(newT1);
        Traveler fromDB1 = traveler.findById(newT1.getTravelerId()).orElse(null);
        assertEquals(newT1.getAddress(), fromDB1.getAddress());
        
        Traveler newT2 = new Traveler();
        newT2.setFirstName("First1");
        newT2.setLastName("Last1");
        newT2.setAddress("A1");
        newT2.setCity("C1");
        newT2.setState("S1");
        newT2.setZip("123");
        newT2.setBirthDate(null);
        newT2.setPhotoFilePath("P1");
        newT2.setPhotoLink("L1");
        newT2.setTrips(null);
        traveler.save(newT2);
        Traveler fromDB2 = traveler.findById(newT2.getTravelerId()).orElse(null);
        assertEquals(newT2.getAddress(), fromDB2.getAddress());
    }
    
    @Test
    public void testAddUpdateTraveler() {
        //Test add
        Traveler newT1 = new Traveler();
        newT1.setFirstName("First1");
        newT1.setLastName("Last1");
        newT1.setAddress("A1");
        newT1.setCity("C1");
        newT1.setState("S1");
        newT1.setZip("123");
        newT1.setBirthDate(null);
        newT1.setPhotoFilePath("P1");
        newT1.setPhotoLink("L1");
        newT1.setTrips(null);
        traveler.save(newT1);
        Traveler fromDB1 = traveler.findById(newT1.getTravelerId()).orElse(null);
        assertNotNull(fromDB1);
        
        //Test edit
        Traveler fromDB2 = traveler.findById(newT1.getTravelerId()).orElse(null);
        fromDB2.setAddress("NewA");
        fromDB2.setPhotoLink("NewLink");
        traveler.save(fromDB2);
        Traveler fromDB3 = traveler.findById(newT1.getTravelerId()).orElse(null);
        assertNotNull(fromDB3);
        assertFalse(newT1.getAddress().equals(fromDB3.getAddress()));
    }
    
    @Test
    public void testDeleteTravelerByID() {
        Traveler newT1 = new Traveler();
        newT1.setFirstName("First1");
        newT1.setLastName("Last1");
        newT1.setAddress("A1");
        newT1.setCity("C1");
        newT1.setState("S1");
        newT1.setZip("123");
        newT1.setBirthDate(null);
        newT1.setPhotoFilePath("P1");
        newT1.setPhotoLink("L1");
        newT1.setTrips(null);
        traveler.save(newT1);
        Traveler fromDB1 = traveler.findById(newT1.getTravelerId()).orElse(null);
        assertNotNull(fromDB1);
        
        traveler.delete(newT1);
        Traveler fromDB2 = traveler.findById(newT1.getTravelerId()).orElse(null);
        assertNull(fromDB2);
    }
    
}
