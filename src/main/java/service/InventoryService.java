package service;

import domain.Inventory;
import exception.BusinessException;
import repository.InventoryRepository;
import java.util.List;
import java.util.Optional;

public class InventoryService {
    
    private final InventoryRepository inventoryRepository;
    
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    
    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }
    
    public Inventory getItemById(Long id) {
        Optional<Inventory> item = inventoryRepository.findById(id);
        if (item.isEmpty()) {
            throw new BusinessException("Item não encontrado");
        }
        return item.get();
    }
    
    public List<Inventory> searchItems(String searchTerm) {
        return inventoryRepository.searchByName(searchTerm);
    }
    
    public Inventory createItem(Inventory item) {
        return inventoryRepository.save(item);
    }
    
    public Inventory updateItem(Inventory item) {
        return inventoryRepository.update(item);
    }
    
    public void deleteItem(Long id) {
        inventoryRepository.delete(id);
    }
}