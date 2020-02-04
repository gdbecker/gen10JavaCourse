package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    
}
