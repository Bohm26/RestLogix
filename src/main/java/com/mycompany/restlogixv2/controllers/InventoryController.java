package com.mycompany.restlogixv2.controllers;

import com.mycompany.restlogixv2.entities.Inventory;
import com.mycompany.restlogixv2.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepo;

    public InventoryController(InventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @GetMapping
    public List<Inventory> getAll() {
        return inventoryRepo.findAll();
    }

    @PostMapping
    public Inventory create(@RequestBody Inventory item) {
        return inventoryRepo.save(item);
    }
}
