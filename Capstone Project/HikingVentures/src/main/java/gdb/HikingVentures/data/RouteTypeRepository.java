package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.RouteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Repository
public interface RouteTypeRepository extends JpaRepository<RouteType, Integer> {
    
}
