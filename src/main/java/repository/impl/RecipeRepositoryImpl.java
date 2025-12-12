package repository.impl;

import domain.Recipe;
import repository.RecipeRepository;
import java.util.*;

public class RecipeRepositoryImpl implements RecipeRepository {
    
    private final Map<Long, Recipe> database = new HashMap<>();
    private long nextId = 1L;
    
    public RecipeRepositoryImpl() {
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Usando construtor com 3 parâmetros
        Recipe arroz = new Recipe("Arroz Simples", "Arroz branco cozido", 20);
        arroz.setId(nextId++);
        database.put(arroz.getId(), arroz);
        
        Recipe feijao = new Recipe("Feijão Carioca", "Feijão tradicional", 60);
        feijao.setId(nextId++);
        database.put(feijao.getId(), feijao);
        
        Recipe panqueca = new Recipe("Panqueca Simples", "Massa básica", 15);
        panqueca.setId(nextId++);
        database.put(panqueca.getId(), panqueca);
    }
    
    @Override
    public Recipe save(Recipe recipe) {
        if (recipe.getId() == null) {
            recipe.setId(nextId++);
        }
        database.put(recipe.getId(), recipe);
        return recipe;
    }
    
    @Override
    public Optional<Recipe> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }
    
    @Override
    public List<Recipe> findAll() {
        return new ArrayList<>(database.values());
    }
    
    @Override
    public Recipe update(Recipe recipe) {
        if (!database.containsKey(recipe.getId())) {
            throw new RuntimeException("Receita não encontrada");
        }
        database.put(recipe.getId(), recipe);
        return recipe;
    }
    
    @Override
    public void delete(Long id) {
        database.remove(id);
    }
    
    @Override
    public Optional<Recipe> findByName(String name) {
        return database.values().stream()
            .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
            .findFirst();
    }
    
    @Override
    public List<Recipe> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return findAll();
        }
        
        String searchTerm = name.toLowerCase();
        return database.values().stream()
            .filter(recipe -> recipe.getName().toLowerCase().contains(searchTerm))
            .toList();
    }
    
    @Override
    public boolean existsByName(String name) {
        return findByName(name).isPresent();
    }
}