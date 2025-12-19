/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restlogixv2;

/**
 *
 * @author joaopedro
 */

import repository.InventoryRepository;
import domain.Inventory;
import exception.BusinessException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import service.InventoryService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários - InventoryService")
class InventoryServiceTest {

	@Mock
	private InventoryRepository inventoryRepository;

	@InjectMocks
	private InventoryService inventoryService;

	private Inventory sampleItem;

	@BeforeEach
	void setUp() {
		sampleItem = new Inventory("Arroz", 50.0, "kg");
		sampleItem.setId(1L);
		sampleItem.setMinimumStock(10);
		sampleItem.setCategory("Grãos");
	}

	@Test
	@DisplayName("Teste 1: Buscar item por ID existente")
	void testGetItemById_Exists() {
		// Arrange
		when(inventoryRepository.findById(1L)).thenReturn(Optional.of(sampleItem));

		// Act
		Inventory result = inventoryService.getItemById(1L);

		// Assert
		assertNotNull(result);
		assertEquals("Arroz", result.getName());
		assertEquals(50.0, result.getQuantity());
		verify(inventoryRepository, times(1)).findById(1L);
	}

	@Test
	@DisplayName("Teste 2: Buscar item por ID não existente")
	void testGetItemById_NotExists() {
		// Arrange
		when(inventoryRepository.findById(999L)).thenReturn(Optional.empty());

		// Act & Assert
		BusinessException exception = assertThrows(BusinessException.class, () -> {
			inventoryService.getItemById(999L);
		});

		assertTrue(exception.getMessage().contains("não encontrado"));
		verify(inventoryRepository, times(1)).findById(999L);
	}

	@Test
	@DisplayName("Teste 3: Listar todos os itens")
	void testGetAllItems() {
		// Arrange
		Inventory item1 = new Inventory("Arroz", 50.0, "kg");
		Inventory item2 = new Inventory("Feijão", 30.0, "kg");
		List<Inventory> expectedItems = Arrays.asList(item1, item2);

		when(inventoryRepository.findAll()).thenReturn(expectedItems);

		// Act
		List<Inventory> result = inventoryService.getAllItems();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(inventoryRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("Teste 4: Buscar itens por termo")
	void testSearchItems() {
		// Arrange
		String searchTerm = "arroz";
		Inventory item1 = new Inventory("Arroz", 50.0, "kg");
		List<Inventory> expectedItems = Arrays.asList(item1);

		when(inventoryRepository.searchByName(searchTerm)).thenReturn(expectedItems);

		// Act
		List<Inventory> result = inventoryService.searchItems(searchTerm);

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Arroz", result.get(0).getName());
		verify(inventoryRepository, times(1)).searchByName(searchTerm);
	}

	@Test
	@DisplayName("Teste 5: Buscar itens com termo vazio retorna todos")
	void testSearchItems_EmptyTerm() {
		// Arrange
		Inventory item1 = new Inventory("Arroz", 50.0, "kg");
		Inventory item2 = new Inventory("Feijão", 30.0, "kg");
		List<Inventory> allItems = Arrays.asList(item1, item2);

		when(inventoryRepository.findAll()).thenReturn(allItems);

		// Act
		List<Inventory> result = inventoryService.searchItems("");

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(inventoryRepository, times(1)).findAll();
		verify(inventoryRepository, never()).searchByName(any());
	}

	@Test
	@DisplayName("Teste 6: Criar novo item")
	void testCreateItem() {
		// Arrange
		Inventory newItem = new Inventory("Novo Item", 100.0, "un");
		Inventory savedItem = new Inventory("Novo Item", 100.0, "un");
		savedItem.setId(99L);

		when(inventoryRepository.save(any(Inventory.class))).thenReturn(savedItem);

		// Act
		Inventory result = inventoryService.createItem(newItem);

		// Assert
		assertNotNull(result);
		assertEquals(99L, result.getId());
		assertEquals("Novo Item", result.getName());
		verify(inventoryRepository, times(1)).save(newItem);
	}
}
