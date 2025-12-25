/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restlogixv2.entities;

/**
 *
 * @author joaopedro
 */
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(length = 20)
	private String unit; // kg, g, ml, L, un, etc

	@Column(name = "unit_price", precision = 10)
	private Double unitPrice; // preço por unidade

	@Column(name = "category")
	private String category; // grãos, proteínas, laticínios, etc

	// Relacionamento com Inventory (se quiser vincular)
	@OneToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventoryItem;

	// Construtor protegido para JPA
	protected Ingredient() {
	}

	// Construtor básico
	public Ingredient(String name, String unit) {
		setName(name);
		this.unit = unit;
	}

	// Construtor completo
	public Ingredient(String name, String description, String unit,
			Double unitPrice, String category) {
		setName(name);
		this.description = description;
		this.unit = unit;
		setUnitPrice(unitPrice);
		this.category = category;
	}

	// VALIDAÇÕES
	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome do ingrediente é obrigatório");
		}
		this.name = name.trim();
	}

	public void setUnitPrice(Double unitPrice) {
		if (unitPrice != null && unitPrice < 0) {
			throw new IllegalArgumentException("Preço unitário não pode ser negativo");
		}
		this.unitPrice = unitPrice;
	}

	// MÉTODOS DE NEGÓCIO
	public Double getQuantity() {
		if (inventoryItem != null) {
			return inventoryItem.getQuantity();
		}
		return 0.0;
	}

	public boolean isLowStock() {
		if (inventoryItem != null) {
			return inventoryItem.isLowStock();
		}
		return false;
	}

	public Double calculateCost(Double quantity) {
		if (unitPrice == null || quantity == null) {
			return 0.0;
		}
		return unitPrice * quantity;
	}

	// GETTERS E SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Inventory getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(Inventory inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Ingredient that = (Ingredient) o;
		return Objects.equals(id, that.id)
				&& Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return name + (unit != null ? " (" + unit + ")" : "");
	}
}
