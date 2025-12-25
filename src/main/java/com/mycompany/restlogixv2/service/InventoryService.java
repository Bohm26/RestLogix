package com.mycompany.restlogixv2.service;

import com.mycompany.restlogixv2.entities.Inventory;
import com.mycompany.restlogixv2.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.restlogixv2.repository.InventoryRepository;
import java.util.Optional;

import java.util.List;

@Service
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	@Autowired
	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public List<Inventory> getAllItems() {
		return inventoryRepository.findAll();
	}

	public List<Inventory> searchItems(String term) {
		if (term == null || term.isBlank()) {
			return inventoryRepository.findAll(); // retorna todos os itens se o termo estiver vazio
		}
		return inventoryRepository.searchByName(term);
	}

	public Inventory createItem(Inventory item) {
		return inventoryRepository.save(item);
	}

	public Inventory updateItem(Inventory item) {
		if (!inventoryRepository.existsById(item.getId())) {
			throw new BusinessException("Item não encontrado para atualizar");
		}
		return inventoryRepository.save(item);
	}

	public Inventory getItemById(Long id) {
		Optional<Inventory> itemOpt = inventoryRepository.findById(id);
		if (itemOpt.isEmpty()) {
			throw new BusinessException("Item não encontrado: " + id);

		}
		return itemOpt.get();
	}

	public void deleteItem(Long id) {
		if (!inventoryRepository.existsById(id)) {
			throw new BusinessException("Item não encontrado para deletar");
		}
		inventoryRepository.deleteById(id);
	}
}
