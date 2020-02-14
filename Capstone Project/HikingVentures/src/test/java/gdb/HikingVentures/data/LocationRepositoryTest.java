package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Location;
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
public class LocationRepositoryTest {
    
    @Autowired
    LocationRepository location;
    
    public LocationRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Location> allL = location.findAll();
        for (Location loc : allL) {
            location.delete(loc);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testFindAllLocations() {
        Location newL1 = new Location();
        newL1.setParkName("Name1");
        newL1.setNearbyCity("City1");
        newL1.setState("S1");
        newL1.setPhotoFilePath("Path1");
        newL1.setPhotoLink("Link1");
        location.save(newL1);
        assertEquals(location.findAll().size(), 1);
        
        Location newL2 = new Location();
        newL2.setParkName("Name2");
        newL2.setNearbyCity("City2");
        newL2.setState("S2");
        newL2.setPhotoFilePath("Path2");
        newL2.setPhotoLink("Link2");
        location.save(newL2);
        assertEquals(location.findAll().size(), 2);
    }
    
    @Test
    public void testFindLocationByID() {
        Location newL1 = new Location();
        newL1.setParkName("Name1");
        newL1.setNearbyCity("City1");
        newL1.setState("S1");
        newL1.setPhotoFilePath("Path1");
        newL1.setPhotoLink("Link1");
        location.save(newL1);
        Location fromDB1 = location.findById(newL1.getLocationId()).orElse(null);
        assertEquals(newL1.getParkName(), fromDB1.getParkName());
        
        Location newL2 = new Location();
        newL2.setParkName("Name2");
        newL2.setNearbyCity("City2");
        newL2.setState("S2");
        newL2.setPhotoFilePath("Path2");
        newL2.setPhotoLink("Link2");
        location.save(newL2);
        Location fromDB2 = location.findById(newL2.getLocationId()).orElse(null);
        assertEquals(newL2.getParkName(), fromDB2.getParkName());
    }
    
    @Test
    public void testAddUpdateLocation() {
        //Test add
        Location newL1 = new Location();
        newL1.setParkName("Name1");
        newL1.setNearbyCity("City1");
        newL1.setState("S1");
        newL1.setPhotoFilePath("Path1");
        newL1.setPhotoLink("Link1");
        location.save(newL1);
        Location fromDB1 = location.findById(newL1.getLocationId()).orElse(null);
        assertNotNull(fromDB1);
        
        //Test edit
        Location fromDB2 = location.findById(newL1.getLocationId()).orElse(null);
        fromDB2.setNearbyCity("NewCity");
        fromDB2.setState("NS");
        location.save(fromDB2);
        Location fromDB3 = location.findById(newL1.getLocationId()).orElse(null);
        assertNotNull(fromDB3);
        assertFalse(newL1.getNearbyCity().equals(fromDB3.getNearbyCity()));
    }
    
    @Test
    public void testDeleteLocationByID() {
        Location newL1 = new Location();
        newL1.setParkName("Name1");
        newL1.setNearbyCity("City1");
        newL1.setState("S1");
        newL1.setPhotoFilePath("Path1");
        newL1.setPhotoLink("Link1");
        location.save(newL1);
        Location fromDB1 = location.findById(newL1.getLocationId()).orElse(null);
        assertNotNull(fromDB1);
        
        location.delete(newL1);
        Location fromDB2 = location.findById(newL1.getLocationId()).orElse(null);
        assertNull(fromDB2);
    }
}
