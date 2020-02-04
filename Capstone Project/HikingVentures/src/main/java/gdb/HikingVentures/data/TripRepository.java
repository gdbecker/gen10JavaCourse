package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    
}
