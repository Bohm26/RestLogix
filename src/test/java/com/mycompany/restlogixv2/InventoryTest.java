/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restlogixv2;

import com.mycompany.restlogixv2.entities.Inventory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Testes da Classe Inventory")
class InventoryTest {

	private Inventory inventory;

	@BeforeEach
	void setUp() {
		inventory = new Inventory("Arroz", 50.0, "kg");
		inventory.setMinimumStock(10);
	}

	@Test
	@DisplayName("Teste 1: Criar inventory válido")
	void testCreateValidInventory() {
		assertNotNull(inventory);
		assertEquals("Arroz", inventory.getName());
		assertEquals(50.0, inventory.getQuantity());
		assertEquals("kg", inventory.getUnit());
		assertEquals(10, inventory.getMinimumStock());
	}

	@Test
	@DisplayName("Teste 2: Verificar estoque baixo")
	void testIsLowStock() {
		// Com quantidade maior que mínimo
		inventory.setQuantity(15.0);
		assertFalse(inventory.isLowStock());

		// Com quantidade igual ao mínimo
		inventory.setQuantity(10.0);
		assertFalse(inventory.isLowStock());

		// Com quantidade menor que mínimo
		inventory.setQuantity(5.0);
		assertTrue(inventory.isLowStock());
	}

	@Test
	@DisplayName("Teste 3: Adicionar quantidade")
	void testAddQuantity() {
		// Arrange
		double initial = inventory.getQuantity(); // 50.0
		double toAdd = 25.0;
		double expected = 75.0;

		// Act
		inventory.addQuantity(toAdd);

		// Assert
		assertEquals(expected, inventory.getQuantity());
	}

	@Test
	@DisplayName("Teste 4: Remover quantidade válida")
	void testRemoveQuantity_Valid() {
		// Arrange
		double initial = inventory.getQuantity(); // 50.0
		double toRemove = 20.0;
		double expected = 30.0;

		// Act
		inventory.removeQuantity(toRemove);

		// Assert
		assertEquals(expected, inventory.getQuantity());
	}

	@Test
	@DisplayName("Teste 5: Tentar remover mais que disponível")
	void testRemoveQuantity_MoreThanAvailable() {
		// Arrange
		double toRemove = 60.0; // Mais que os 50 disponíveis

		// Act & Assert
		inventory.removeQuantity(toRemove); // Não deve lançar exceção, apenas não fazer nada
		assertEquals(50.0, inventory.getQuantity()); // Quantidade deve permanecer
	}

	@Test
	@DisplayName("Teste 6: Verificar validade próxima")
	void testIsExpiringSoon() {
		// Este método depende de LocalDate, vamos testar cenários básicos
		// Para simplificar, vamos testar apenas a lógica existente

		// Inventory sem data de validade
		assertFalse(inventory.isExpiringSoon(7));

		// Inventory com data futura (não implementado no método atual)
		// Este teste é para documentar que a funcionalidade precisa ser implementada
		System.out.println("Teste de validade: funcionalidade a ser implementada");
	}

	@Test
	@DisplayName("Teste 7: Método toString")
	void testToString() {
		String result = inventory.toString();
		assertNotNull(result);
		assertTrue(result.contains("Arroz"));
		assertTrue(result.contains("50.0"));
		assertTrue(result.contains("kg"));
	}
}
