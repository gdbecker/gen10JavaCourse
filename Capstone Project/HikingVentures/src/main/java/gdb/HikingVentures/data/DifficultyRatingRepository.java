package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.DifficultyRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Repository
public interface DifficultyRatingRepository extends  JpaRepository<DifficultyRating, Integer> {
    
}
