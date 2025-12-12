package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author joaopedro
 */
@Entity
@Table(name = "recipe_ingredients")

public class IngredientRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	private Inventory ingredient;

	@Column(nullable = false)
	private Double quantity;

	public IngredientRecipe() {
	}

	public IngredientRecipe(Integer id, Recipe recipe, Inventory ingredient, Double quantity) {
		this.id = id;
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Inventory getIngredient() {
		return ingredient;
	}

	public void setIngredient(Inventory ingredient) {
		this.ingredient = ingredient;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

}
