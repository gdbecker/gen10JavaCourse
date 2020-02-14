package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Equipment;
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
public class EquipmentRepositoryTest {
    
    @Autowired
    EquipmentRepository equipment;
    
    public EquipmentRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Equipment> allE = equipment.findAll();
        for (Equipment equ : allE) {
            equipment.delete(equ);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testFindAllEquipment() {
        Equipment newE1 = new Equipment();
        newE1.setName("Name1");
        newE1.setDescription("Descr1");
        newE1.setPhotoFilePath("Path1");
        newE1.setPhotoLink("Link1");
        newE1.setTrips(null);
        equipment.save(newE1);
        
        assertEquals(equipment.findAll().size(), 1);
        
        Equipment newE2 = new Equipment();
        newE2.setName("Name2");
        newE2.setDescription("Descr2");
        newE2.setPhotoFilePath("Path2");
        newE2.setPhotoLink("Link2");
        newE2.setTrips(null);
        equipment.save(newE2);
        
        assertEquals(equipment.findAll().size(), 2);
    }
    
    @Test
    public void testFindEquipmentByID() {
        Equipment newE1 = new Equipment();
        newE1.setName("Name1");
        newE1.setDescription("Descr1");
        newE1.setPhotoFilePath("Path1");
        newE1.setPhotoLink("Link1");
        newE1.setTrips(null);
        equipment.save(newE1);
        Equipment fromDB1 = equipment.findById(newE1.getEquipmentId()).orElse(null);
        assertEquals(newE1.getDescription(), fromDB1.getDescription());
        
        Equipment newE2 = new Equipment();
        newE2.setName("Name2");
        newE2.setDescription("Descr2");
        newE2.setPhotoFilePath("Path2");
        newE2.setPhotoLink("Link2");
        newE2.setTrips(null);
        equipment.save(newE2);
        Equipment fromDB2 = equipment.findById(newE2.getEquipmentId()).orElse(null);
        assertEquals(newE2.getDescription(), fromDB2.getDescription());
    }
    
    @Test
    public void testAddUpdateEquipment() {
        //Test add
        Equipment newE1 = new Equipment();
        newE1.setName("Name1");
        newE1.setDescription("Descr1");
        newE1.setPhotoFilePath("Path1");
        newE1.setPhotoLink("Link1");
        newE1.setTrips(null);
        equipment.save(newE1);
        Equipment fromDB1 = equipment.findById(newE1.getEquipmentId()).orElse(null);
        assertNotNull(fromDB1);
        
        //Test edit
        Equipment fromDB2 = equipment.findById(newE1.getEquipmentId()).orElse(null);
        fromDB2.setDescription("NewDescr");
        fromDB2.setPhotoLink("NewPath");
        equipment.save(fromDB2);
        Equipment fromDB3 = equipment.findById(fromDB2.getEquipmentId()).orElse(null);
        assertNotNull(fromDB3);
        assertFalse(newE1.getDescription().equals(fromDB3.getDescription()));
    }
    
    @Test
    public void testDeleteEquipmentByID() {
        Equipment newE1 = new Equipment();
        newE1.setName("Name1");
        newE1.setDescription("Descr1");
        newE1.setPhotoFilePath("Path1");
        newE1.setPhotoLink("Link1");
        newE1.setTrips(null);
        equipment.save(newE1);
        Equipment fromDB1 = equipment.findById(newE1.getEquipmentId()).orElse(null);
        assertNotNull(fromDB1);
        
        equipment.delete(newE1);
        Equipment fromDB2 = equipment.findById(newE1.getEquipmentId()).orElse(null);
        assertNull(fromDB2);
    }
}
