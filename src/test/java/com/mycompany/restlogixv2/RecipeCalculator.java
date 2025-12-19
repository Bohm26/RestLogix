/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restlogixv2;

/**
 *
 * @author joaopedro
 */
import java.math.BigDecimal;
import java.util.List;

/**
 * Classe com cálculos simples para testes unitários
 */
public class RecipeCalculator {

	// Método 1: Cálculo simples de custo
	public static BigDecimal calculateCost(int portions, BigDecimal pricePerPortion) {
		if (portions <= 0) {
			throw new IllegalArgumentException("Número de porções deve ser positivo");
		}
		if (pricePerPortion == null || pricePerPortion.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Preço por porção inválido");
		}

		return pricePerPortion.multiply(BigDecimal.valueOf(portions));
	}

	// Método 2: Verificar se quantidade é suficiente
	public static boolean hasSufficientQuantity(double required, double available) {
		if (required <= 0) {
			throw new IllegalArgumentException("Quantidade requerida deve ser positiva");
		}
		if (available < 0) {
			throw new IllegalArgumentException("Quantidade disponível não pode ser negativa");
		}

		return available >= required;
	}

	// Método 3: Calcular tempo total
	public static int calculateTotalTime(int preparationTime, int cookingTime) {
		if (preparationTime < 0 || cookingTime < 0) {
			throw new IllegalArgumentException("Tempos não podem ser negativos");
		}

		return preparationTime + cookingTime;
	}

	// Método 4: Calcular máximo de porções
	public static int calculateMaxPortions(double totalIngredient, double ingredientPerPortion) {
		if (totalIngredient <= 0 || ingredientPerPortion <= 0) {
			return 0;
		}

		return (int) Math.floor(totalIngredient / ingredientPerPortion);
	}
}
