package com.sg.inventory.repositories;

import com.sg.inventory.entities.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository {
    List findByStore(Store store);
}
