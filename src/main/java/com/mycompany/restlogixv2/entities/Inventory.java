package com.mycompany.restlogixv2.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column
	private Double quantity;

	@Column(length = 20)
	private String unit;

	@Column(name = "expiry_date")
	private LocalDate expiryDate;

	@Column(name = "minimum_stock")
	private Integer minimumStock;

	@Column(length = 50)
	private String category;

	public Inventory() {
	}

	public Inventory(String name, Double quantity, String unit) {
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getMinimumStock() {
		return minimumStock;
	}

	public void setMinimumStock(Integer minimumStock) {
		this.minimumStock = minimumStock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// Métodos de negócio
	public boolean isLowStock() {
		return minimumStock != null && quantity < minimumStock;
	}

	@Override
	public String toString() {
		return name + " - " + quantity + " " + unit;
	}

	public boolean isExpiringSoon(int days) {
		if (expiryDate == null) {
			return false;
		}
		return expiryDate.isBefore(LocalDate.now().plusDays(days));
	}

	public void addQuantity(Double amount) {
		if (amount > 0) {
			this.quantity += amount;
		}
	}

	public void removeQuantity(Double amount) {
		if (amount > 0 && amount <= quantity) {
			this.quantity -= amount;
		}
	}
}
