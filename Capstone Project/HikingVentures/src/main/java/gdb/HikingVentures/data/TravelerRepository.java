package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Integer> {
    
}
