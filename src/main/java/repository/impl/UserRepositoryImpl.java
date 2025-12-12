package repository.impl;

import domain.User;
import repository.UserRepository;
import java.util.*;

public class UserRepositoryImpl implements UserRepository {
    
    private final Map<Long, User> database = new HashMap<>();
    private long nextId = 1L;
    
    public UserRepositoryImpl() {
        initializeDefaultAdmin();
    }
    
    private void initializeDefaultAdmin() {
        User admin = new User("Administrador", "admin", "admin123", true);
        admin.setId(nextId++);
        database.put(admin.getId(), admin);
    }
    
    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(nextId++);
        }
        database.put(user.getId(), user);
        return user;
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        return database.values().stream()
            .filter(user -> user.getUsername().equalsIgnoreCase(username))
            .findFirst();
    }
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }
    
    @Override
    public void delete(Long id) {
        database.remove(id);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }
}