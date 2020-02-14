package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 * Security entity
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
