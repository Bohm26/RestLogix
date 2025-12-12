/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Recipe;
import java.util.List;
import java.util.Optional;

public interface RecipeRepository {
    Recipe save(Recipe recipe);
    Optional<Recipe> findById(Long id);
    List<Recipe> findAll();
    Recipe update(Recipe recipe);
    void delete(Long id);
    Optional<Recipe> findByName(String name);
    List<Recipe> searchByName(String name);
    boolean existsByName(String name);
}