package domain;

import java.time.LocalDate;

public class Inventory {

	private Long id;
	private String name;
	private String description;
	private Double quantity;
	private String unit;
	private LocalDate expiryDate;
	private Integer minimumStock;
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

	// Métodos simplificados
	public boolean isLowStock() {
		return minimumStock != null && quantity < minimumStock;
	}

	public boolean isExpiringSoon(int days) {
		if (expiryDate == null) {
			return false;
		}
		return expiryDate.isBefore(java.time.LocalDate.now().plusDays(days));
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
