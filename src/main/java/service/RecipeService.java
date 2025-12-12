package service;

import domain.Recipe;
import exception.BusinessException;
import repository.RecipeRepository;
import java.util.List;
import java.util.Optional;

public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new BusinessException("Receita não encontrada");
        }
        return recipe.get();
    }
    
    public List<Recipe> searchRecipes(String searchTerm) {
        return recipeRepository.searchByName(searchTerm);
    }
    
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    
    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.update(recipe);
    }
    
    public void deleteRecipe(Long id) {
        recipeRepository.delete(id);
    }
}