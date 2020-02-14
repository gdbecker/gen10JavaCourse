package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Trip;
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
public class TripRepositoryTest {
    
    @Autowired
    TripRepository trip;
    
    public TripRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Trip> allT = trip.findAll();
        for (Trip t : allT) {
            trip.delete(t);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testFindAllTrips() {
        Trip newT1 = new Trip();
        newT1.setTripName("Name1");
        newT1.setTripCostPerTraveler(0);
        newT1.setStartDate(null);
        newT1.setEndDate(null);
        newT1.setEquipment(null);
        newT1.setTrails(null);
        newT1.setTravelers(null);
        trip.save(newT1);
        assertEquals(trip.findAll().size(), 1);
        
        Trip newT2 = new Trip();
        newT2.setTripName("Name2");
        newT2.setTripCostPerTraveler(1);
        newT2.setStartDate(null);
        newT2.setEndDate(null);
        newT2.setEquipment(null);
        newT2.setTrails(null);
        newT2.setTravelers(null);
        trip.save(newT2);
        assertEquals(trip.findAll().size(), 2);
    }
    
    @Test
    public void testFindTripByID() {
        Trip newT1 = new Trip();
        newT1.setTripName("Name1");
        newT1.setTripCostPerTraveler(0);
        newT1.setStartDate(null);
        newT1.setEndDate(null);
        newT1.setEquipment(null);
        newT1.setTrails(null);
        newT1.setTravelers(null);
        trip.save(newT1);
        Trip fromDB1 = trip.findById(newT1.getTripId()).orElse(null);
        assertEquals(newT1.getTripName(), fromDB1.getTripName());
        
        Trip newT2 = new Trip();
        newT2.setTripName("Name2");
        newT2.setTripCostPerTraveler(1);
        newT2.setStartDate(null);
        newT2.setEndDate(null);
        newT2.setEquipment(null);
        newT2.setTrails(null);
        newT2.setTravelers(null);
        trip.save(newT2);
        Trip fromDB2 = trip.findById(newT2.getTripId()).orElse(null);
        assertEquals(newT2.getTripName(), fromDB2.getTripName());
    }
    
    @Test
    public void testAddUpdateTrip() {
        //Test add
        Trip newT1 = new Trip();
        newT1.setTripName("Name1");
        newT1.setTripCostPerTraveler(0);
        newT1.setStartDate(null);
        newT1.setEndDate(null);
        newT1.setEquipment(null);
        newT1.setTrails(null);
        newT1.setTravelers(null);
        trip.save(newT1);
        Trip fromDB1 = trip.findById(newT1.getTripId()).orElse(null);
        assertNotNull(fromDB1);
        
        //Test edit
        Trip fromDB2 = trip.findById(newT1.getTripId()).orElse(null);
        fromDB2.setTripName("NewName");
        fromDB2.setTripCostPerTraveler(1);
        trip.save(fromDB2);
        Trip fromDB3 = trip.findById(newT1.getTripId()).orElse(null);
        assertFalse(newT1.getTripName().equals(fromDB3.getTripName()));
    }
    
    @Test
    public void testDeleteTripByID() {
        Trip newT1 = new Trip();
        newT1.setTripName("Name1");
        newT1.setTripCostPerTraveler(0);
        newT1.setStartDate(null);
        newT1.setEndDate(null);
        newT1.setEquipment(null);
        newT1.setTrails(null);
        newT1.setTravelers(null);
        trip.save(newT1);
        Trip fromDB1 = trip.findById(newT1.getTripId()).orElse(null);
        assertNotNull(fromDB1);
        
        trip.delete(newT1);
        Trip fromDB2 = trip.findById(newT1.getTripId()).orElse(null);
        assertNull(fromDB2);
    }
}
