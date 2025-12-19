/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restlogixv2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testes da Classe RecipeCalculator")
class RecipeCalculatorTest {

	@Test
	@DisplayName("Teste 1: Cálculo de custo válido")
	void testCalculateCost_Valid() {
		// Arrange
		int portions = 4;
		BigDecimal pricePerPortion = new BigDecimal("10.50");
		BigDecimal expected = new BigDecimal("42.00"); // 10.50 * 4

		// Act
		BigDecimal result = RecipeCalculator.calculateCost(portions, pricePerPortion);

		// Assert
		assertNotNull(result);
		assertEquals(0, expected.compareTo(result));
	}

	@Test
	@DisplayName("Teste 2: Cálculo de custo com porções zero")
	void testCalculateCost_ZeroPortions() {
		// Arrange
		int portions = 0;
		BigDecimal pricePerPortion = new BigDecimal("10.50");

		// Act & Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> RecipeCalculator.calculateCost(portions, pricePerPortion)
		);

		assertTrue(exception.getMessage().contains("porções deve ser positivo"));
	}

	@Test
	@DisplayName("Teste 3: Cálculo de custo com preço negativo")
	void testCalculateCost_NegativePrice() {
		// Arrange
		int portions = 4;
		BigDecimal pricePerPortion = new BigDecimal("-5.00");

		// Act & Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> RecipeCalculator.calculateCost(portions, pricePerPortion)
		);

		assertTrue(exception.getMessage().contains("Preço por porção inválido"));
	}

	@ParameterizedTest
	@CsvSource({
		"2.5, 5.0, true", // Disponível > Requerido
		"2.5, 2.5, true", // Disponível = Requerido
		"3.0, 2.5, false", // Disponível < Requerido
		"0.5, 1.0, true" // Disponível maior que requerido
	})
	@DisplayName("Teste 4: Verificar quantidade suficiente (parametrizado)")
	void testHasSufficientQuantity_Parameterized(double required, double available, boolean expected) {
		// Act
		boolean result = RecipeCalculator.hasSufficientQuantity(required, available);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Teste 5: Verificar quantidade com valor requerido negativo")
	void testHasSufficientQuantity_NegativeRequired() {
		// Act & Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> RecipeCalculator.hasSufficientQuantity(-1.0, 5.0)
		);

		assertTrue(exception.getMessage().contains("Quantidade requerida deve ser positiva"));
	}

	@Test
	@DisplayName("Teste 6: Calcular tempo total")
	void testCalculateTotalTime() {
		// Arrange
		int preparationTime = 30;
		int cookingTime = 45;
		int expected = 75; // 30 + 45

		// Act
		int result = RecipeCalculator.calculateTotalTime(preparationTime, cookingTime);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Teste 7: Calcular tempo com valores negativos")
	void testCalculateTotalTime_NegativeValues() {
		// Act & Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> RecipeCalculator.calculateTotalTime(-10, 20)
		);

		assertTrue(exception.getMessage().contains("Tempos não podem ser negativos"));
	}

	@ParameterizedTest
	@CsvSource({
		"10.0, 2.0, 5", // 10/2 = 5 porções
		"9.0, 2.0, 4", // 9/2 = 4.5 -> floor = 4
		"5.0, 1.5, 3", // 5/1.5 = 3.33 -> floor = 3
		"0.0, 2.0, 0", // quantidade zero
		"5.0, 0.0, 0" // porção zero
	})
	@DisplayName("Teste 8: Calcular máximo de porções (parametrizado)")
	void testCalculateMaxPortions_Parameterized(double total, double perPortion, int expected) {
		// Act
		int result = RecipeCalculator.calculateMaxPortions(total, perPortion);

		// Assert
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Teste 9: Cálculo em sequência - Cenário completo")
	void testCompleteScenario() {
		// Cenário: Receita de arroz
		int portions = 4;
		BigDecimal pricePerPortion = new BigDecimal("3.75");
		double riceRequired = 2.0; // kg por receita
		double riceAvailable = 10.0; // kg em estoque
		int prepTime = 15;
		int cookTime = 25;

		// 1. Calcular custo total
		BigDecimal totalCost = RecipeCalculator.calculateCost(portions, pricePerPortion);
		assertEquals(new BigDecimal("15.00"), totalCost); // 3.75 * 4

		// 2. Verificar se tem quantidade suficiente
		boolean hasRice = RecipeCalculator.hasSufficientQuantity(riceRequired, riceAvailable);
		assertTrue(hasRice);

		// 3. Calcular tempo total
		int totalTime = RecipeCalculator.calculateTotalTime(prepTime, cookTime);
		assertEquals(40, totalTime); // 15 + 25

		// 4. Calcular máximo de porções possíveis
		int maxPortions = RecipeCalculator.calculateMaxPortions(riceAvailable, riceRequired);
		assertEquals(5, maxPortions); // 10/2 = 5

		System.out.println("Cenário completo executado com sucesso!");
		System.out.println("Custo total: R$ " + totalCost);
		System.out.println("Tempo total: " + totalTime + " minutos");
		System.out.println("Porções máximas possíveis: " + maxPortions);
	}
}
