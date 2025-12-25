package com.mycompany.restlogixv2.controllers;

import com.mycompany.restlogixv2.entities.User;
import com.mycompany.restlogixv2.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) { this.repo = repo; }

    @GetMapping
    public List<User> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public User create(@RequestBody User user) { return repo.save(user); }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existing = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existing.setName(user.getName());
        existing.setUsername(user.getUsername());
        existing.setPasswordHash(user.getPasswordHash());
        existing.setAdmin(user.isAdmin());
        return repo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
