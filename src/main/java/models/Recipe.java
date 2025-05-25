package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaopedro
 */
@Entity
@Table(name = "recipes")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(name = "preparation_time") // Define o tempo do preparo, deve ser colocado em minutos
	private Integer preparationTime;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<RecipeIngredients> ingredients = new ArrayList<>();

	public Recipe() {
	}

	public Recipe(Integer id, String name, String description, Integer preparationTime) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.preparationTime = preparationTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(Integer preparationTime) {
		this.preparationTime = preparationTime;
	}

	public List<RecipeIngredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredients> ingredients) {
		this.ingredients = ingredients;
	}

	public List<RecipeIngredients> getRecipeIngredients() {
		return this.ingredients;
	}
}
