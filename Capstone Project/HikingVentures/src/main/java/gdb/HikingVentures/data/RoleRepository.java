package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 * Security entity
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
