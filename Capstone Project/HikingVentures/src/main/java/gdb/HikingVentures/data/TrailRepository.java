package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Repository
public interface TrailRepository extends JpaRepository<Trail, Integer> {
    
}
