package service;

import domain.User;
import dto.LoginDTO;
import exception.AuthenticationException;
import repository.UserRepository;
import java.util.Optional;

public class AuthService {
    
    private final UserRepository userRepository;
    private User currentUser;
    
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User login(LoginDTO loginDTO) {
        if (!loginDTO.isValid()) {
            throw new AuthenticationException("Credenciais inválidas");
        }
        
        Optional<User> userOpt = userRepository.findByUsername(loginDTO.getUsername());
        
        if (userOpt.isEmpty()) {
            throw new AuthenticationException("Usuário não encontrado");
        }
        
        User user = userOpt.get();
        
        if (!user.checkPassword(loginDTO.getPassword())) {
            throw new AuthenticationException("Senha incorreta");
        }
        
        this.currentUser = user;
        return user;
    }
    
    public void logout() {
        this.currentUser = null;
    }
    
    public boolean isAuthenticated() {
        return currentUser != null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public User createUser(String name, String username, String password, boolean isAdmin) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username já está em uso");
        }
        
        User newUser = new User(name, username, password, isAdmin);
        return userRepository.save(newUser);
    }
}