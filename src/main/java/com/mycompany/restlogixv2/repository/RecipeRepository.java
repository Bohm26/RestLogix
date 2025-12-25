package com.mycompany.restlogixv2.repository;

import com.mycompany.restlogixv2.entities.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

 List<Recipe> findByNameContainingIgnoreCase(String name);
 
}
