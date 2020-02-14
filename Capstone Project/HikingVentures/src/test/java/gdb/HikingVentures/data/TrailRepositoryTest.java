package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Trail;
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
public class TrailRepositoryTest {
    
    @Autowired
    TrailRepository trail;
    
    public TrailRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Trail> allT = trail.findAll();
        for (Trail t : allT) {
            trail.delete(t);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testFindAllTrails() {
        Trail newT1 = new Trail();
        newT1.setTrailName("Name1");
        newT1.setLocation(null);
        newT1.setRouteType(null);
        newT1.setDifficultyRating(null);
        newT1.setDistance(0);
        newT1.setElevationGain(0);
        newT1.setMapLink("Link1");
        newT1.setPhotoLink("Link1");
        newT1.setPhotoFilePath("Path1");
        newT1.setTrips(null);
        trail.save(newT1);
        assertEquals(trail.findAll().size(), 1);
        
        Trail newT2 = new Trail();
        newT2.setTrailName("Name2");
        newT2.setLocation(null);
        newT2.setRouteType(null);
        newT2.setDifficultyRating(null);
        newT2.setDistance(0);
        newT2.setElevationGain(0);
        newT2.setMapLink("Link2");
        newT2.setPhotoLink("Link2");
        newT2.setPhotoFilePath("Path2");
        newT2.setTrips(null);
        trail.save(newT2);
        assertEquals(trail.findAll().size(), 2);
    }
    
    @Test
    public void testFindTrailByID() {
        Trail newT1 = new Trail();
        newT1.setTrailName("Name1");
        newT1.setLocation(null);
        newT1.setRouteType(null);
        newT1.setDifficultyRating(null);
        newT1.setDistance(0);
        newT1.setElevationGain(0);
        newT1.setMapLink("Link1");
        newT1.setPhotoLink("Link1");
        newT1.setPhotoFilePath("Path1");
        newT1.setTrips(null);
        trail.save(newT1);
        Trail fromDB1 = trail.findById(newT1.getTrailId()).orElse(null);
        assertEquals(newT1.getPhotoLink(), fromDB1.getPhotoLink());
        
        Trail newT2 = new Trail();
        newT2.setTrailName("Name2");
        newT2.setLocation(null);
        newT2.setRouteType(null);
        newT2.setDifficultyRating(null);
        newT2.setDistance(0);
        newT2.setElevationGain(0);
        newT2.setMapLink("Link2");
        newT2.setPhotoLink("Link2");
        newT2.setPhotoFilePath("Path2");
        newT2.setTrips(null);
        trail.save(newT2);
        Trail fromDB2 = trail.findById(newT2.getTrailId()).orElse(null);
        assertEquals(newT2.getPhotoLink(), fromDB2.getPhotoLink());
    }
    
    @Test
    public void testAddUpdateTrail() {
        //Test add
        Trail newT1 = new Trail();
        newT1.setTrailName("Name1");
        newT1.setLocation(null);
        newT1.setRouteType(null);
        newT1.setDifficultyRating(null);
        newT1.setDistance(0);
        newT1.setElevationGain(0);
        newT1.setMapLink("Link1");
        newT1.setPhotoLink("Link1");
        newT1.setPhotoFilePath("Path1");
        newT1.setTrips(null);
        trail.save(newT1);
        Trail fromDB1 = trail.findById(newT1.getTrailId()).orElse(null);
        assertNotNull(fromDB1);
        
        //Test edit
        Trail fromDB2 = trail.findById(newT1.getTrailId()).orElse(null);
        fromDB2.setDistance(1);
        fromDB2.setElevationGain(1);
        trail.save(fromDB2);
        Trail fromDB3 = trail.findById(newT1.getTrailId()).orElse(null);
        assertNotNull(fromDB3);
        assertFalse(newT1.getElevationGain() == fromDB3.getElevationGain());
    }
    
    @Test
    public void testDeleteTrailByID() {
        Trail newT1 = new Trail();
        newT1.setTrailName("Name1");
        newT1.setLocation(null);
        newT1.setRouteType(null);
        newT1.setDifficultyRating(null);
        newT1.setDistance(0);
        newT1.setElevationGain(0);
        newT1.setMapLink("Link1");
        newT1.setPhotoLink("Link1");
        newT1.setPhotoFilePath("Path1");
        newT1.setTrips(null);
        trail.save(newT1);
        Trail fromDB1 = trail.findById(newT1.getTrailId()).orElse(null);
        assertNotNull(fromDB1);
        
        trail.delete(newT1);
        Trail fromDB2 = trail.findById(newT1.getTrailId()).orElse(null);
        assertNull(fromDB2);
    }
    
}
