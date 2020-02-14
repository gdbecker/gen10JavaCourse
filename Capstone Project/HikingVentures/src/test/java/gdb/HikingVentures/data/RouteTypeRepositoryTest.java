package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.RouteType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class RouteTypeRepositoryTest {
    
    @Autowired
    RouteTypeRepository rt;
    
    public RouteTypeRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<RouteType> allRT = rt.findAll();
        for (RouteType route : allRT) {
            rt.delete(route);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testFindAllRouteTypes() {
        RouteType rt1 = new RouteType();
        rt1.setType("Type1");
        rt1.setDescription("Descr1");
        rt.save(rt1);
        
        assertEquals(rt.findAll().size(), 1);
        
        RouteType rt2 = new RouteType();
        rt2.setType("Type2");
        rt2.setDescription("Descr2");
        rt.save(rt2);
        
        assertEquals(rt.findAll().size(), 2);
    }
    
    @Test
    public void testFindRouteTypeByID() {
        RouteType rt1 = new RouteType();
        rt1.setType("Type1");
        rt1.setDescription("Descr1");
        rt.save(rt1);
        
        RouteType fromDB1 = rt.findById(rt1.getRouteTypeId()).orElse(null);
        
        assertEquals(rt1.getType(), fromDB1.getType());
        
        RouteType rt2 = new RouteType();
        rt2.setType("Type2");
        rt2.setDescription("Descr2");
        rt.save(rt2);
        
        RouteType fromDB2 = rt.findById(rt2.getRouteTypeId()).orElse(null);
        
        assertEquals(rt2.getType(), fromDB2.getType());
    }
    
}
