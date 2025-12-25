package com.mycompany.restlogixv2.repository;

import com.mycompany.restlogixv2.entities.Inventory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	  List<Inventory> searchByName(String name);
		  
}
