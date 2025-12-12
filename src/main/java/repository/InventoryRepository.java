package repository;

import domain.Inventory;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository {

	Inventory save(Inventory inventory);

	Optional<Inventory> findById(Long id);

	List<Inventory> findAll();

	Inventory update(Inventory inventory);

	void delete(Long id);

	Optional<Inventory> findByName(String name);

	List<Inventory> searchByName(String name);

	boolean existsByName(String name);

	boolean isUsedInRecipes(Long inventoryId);
}
