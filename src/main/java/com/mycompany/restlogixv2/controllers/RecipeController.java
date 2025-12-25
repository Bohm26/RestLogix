package com.mycompany.restlogixv2.controllers;

import com.mycompany.restlogixv2.entities.Recipe;
import com.mycompany.restlogixv2.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepo;

    public RecipeController(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @GetMapping
    public List<Recipe> getAll() {
        return recipeRepo.findAll();
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {
        return recipeRepo.save(recipe);
    }
}