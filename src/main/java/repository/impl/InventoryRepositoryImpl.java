package repository.impl;

import domain.Inventory;
import repository.InventoryRepository;
import java.time.LocalDate;
import java.util.*;

public class InventoryRepositoryImpl implements InventoryRepository {

	private final Map<Long, Inventory> database = new HashMap<>();
	private long nextId = 1L;

	public InventoryRepositoryImpl() {
		initializeSampleData();
	}

	private void initializeSampleData() {
		// Usando construtor básico
		Inventory arroz = new Inventory("Arroz", 50.0, "kg");
		arroz.setId(nextId++);
		arroz.setDescription("Arroz branco tipo 1");
		arroz.setMinimumStock(10);
		arroz.setCategory("Grãos");
		database.put(arroz.getId(), arroz);

		Inventory feijao = new Inventory("Feijão", 30.0, "kg");
		feijao.setId(nextId++);
		feijao.setDescription("Feijão carioca");
		feijao.setMinimumStock(5);
		feijao.setCategory("Grãos");
		database.put(feijao.getId(), feijao);
	}

	@Override
	public Inventory save(Inventory inventory) {
		if (inventory.getId() == null) {
			inventory.setId(nextId++);
		}
		database.put(inventory.getId(), inventory);
		return inventory;
	}

	@Override
	public Optional<Inventory> findById(Long id) {
		return Optional.ofNullable(database.get(id));
	}

	@Override
	public List<Inventory> findAll() {
		return new ArrayList<>(database.values());
	}

	@Override
	public Inventory update(Inventory inventory) {
		if (!database.containsKey(inventory.getId())) {
			throw new RuntimeException("Item não encontrado");
		}
		database.put(inventory.getId(), inventory);
		return inventory;
	}

	@Override
	public void delete(Long id) {
		database.remove(id);
	}

	@Override
	public Optional<Inventory> findByName(String name) {
		return database.values().stream()
				.filter(item -> item.getName().equalsIgnoreCase(name))
				.findFirst();
	}

	@Override
	public List<Inventory> searchByName(String name) {
		if (name == null || name.trim().isEmpty()) {
			return findAll();
		}

		String searchTerm = name.toLowerCase();
		return database.values().stream()
				.filter(item -> item.getName().toLowerCase().contains(searchTerm))
				.toList();
	}

	@Override
	public boolean existsByName(String name) {
		return findByName(name).isPresent();
	}

	@Override
	public boolean isUsedInRecipes(Long inventoryId) {
		return false; // Simulação
	}
}
