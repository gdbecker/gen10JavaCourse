package gdb.HikingVentures.data;

import gdb.HikingVentures.entities.Equipment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    
}
