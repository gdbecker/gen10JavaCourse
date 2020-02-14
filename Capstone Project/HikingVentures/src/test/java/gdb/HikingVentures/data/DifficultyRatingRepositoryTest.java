package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.DifficultyRating;
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
public class DifficultyRatingRepositoryTest {
    
    @Autowired
    DifficultyRatingRepository dr;
    
    public DifficultyRatingRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<DifficultyRating> allDR = dr.findAll();
        for (DifficultyRating diffRat : allDR) {
            dr.delete(diffRat);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testFindAllDifficultyRatings() {
        DifficultyRating newDR1 = new DifficultyRating();
        newDR1.setType("Type1");
        dr.save(newDR1);
        
        assertEquals(dr.findAll().size(), 1);
        
        DifficultyRating newDR12 = new DifficultyRating();
        newDR12.setType("Type2");
        dr.save(newDR12);
        
        assertEquals(dr.findAll().size(), 2);
    }
    
    @Test
    public void testFindDifficultyRatingByID() {
        DifficultyRating newDR1 = new DifficultyRating();
        newDR1.setType("Type1");
        dr.save(newDR1);
        
        DifficultyRating fromDB1 = dr.findById(newDR1.getDifficultyRatingId()).orElse(null);
        
        assertEquals(newDR1.getType(), fromDB1.getType());
        
        DifficultyRating newDR2 = new DifficultyRating();
        newDR2.setType("Type2");
        dr.save(newDR2);
        
        DifficultyRating fromDB2 = dr.findById(newDR2.getDifficultyRatingId()).orElse(null);
        
        assertEquals(newDR2.getType(), fromDB2.getType());
    }
}
