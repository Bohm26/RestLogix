/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;
    
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal quantity;
    
    @Column(name = "instructions", length = 500)
    private String instructions; // Ex: "picado fino", "em temperatura ambiente"
    
    // Construtor protegido para JPA
    protected RecipeIngredient() {}
    
    // Construtor principal
    public RecipeIngredient(Recipe recipe, Ingredient ingredient, BigDecimal quantity) {
        setRecipe(recipe);
        setIngredient(ingredient);
        setQuantity(quantity);
    }
    
    // Construtor completo
    public RecipeIngredient(Recipe recipe, Ingredient ingredient, 
                           BigDecimal quantity, String instructions) {
        this(recipe, ingredient, quantity);
        this.instructions = instructions;
    }
    
    // VALIDAÇÕES
    public void setRecipe(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Receita não pode ser nula");
        }
        this.recipe = recipe;
    }
    
    public void setIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingrediente não pode ser nulo");
        }
        this.ingredient = ingredient;
    }
    
    public void setQuantity(BigDecimal quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantidade não pode ser nula");
        }
        if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        this.quantity = quantity;
    }
    
    // MÉTODOS DE NEGÓCIO
    public double calculateCost() {
        // Aqui você implementaria a lógica de custo
        // Por enquanto retorna 0.0
        return 0.0;
    }
    
    public boolean hasSufficientStock() {
        if (ingredient == null) return false;
        
        // Converte BigDecimal para double para comparação
        double requiredQuantity = quantity.doubleValue();
        double availableQuantity = ingredient.getQuantity();
        
        return availableQuantity >= requiredQuantity;
    }
    
    public BigDecimal getQuantityForPortions(int portions) {
        if (portions <= 0) {
            throw new IllegalArgumentException("Número de porções deve ser positivo");
        }
        return quantity.multiply(BigDecimal.valueOf(portions));
    }
    
    // GETTERS E SETTERS
    public Long getId() { return id; }
    
    public Recipe getRecipe() { return recipe; }
    
    public Ingredient getIngredient() { return ingredient; }
    
    public BigDecimal getQuantity() { return quantity; }
    
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { 
        this.instructions = instructions; 
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return ingredient.getName() + ": " + quantity + 
               (ingredient.getUnit() != null ? " " + ingredient.getUnit() : "") +
               (instructions != null ? " (" + instructions + ")" : "");
    }
}