package com.mycompany.restlogixv2.service;

import com.mycompany.restlogixv2.entities.User;
import com.mycompany.restlogixv2.dto.LoginDTO;
import com.mycompany.restlogixv2.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.restlogixv2.repository.UserRepository;


import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private User currentUser;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Login
    public User login(LoginDTO loginDTO) {
        if (!loginDTO.isValid()) {
            throw new AuthenticationException("Credenciais inválidas");
        }

        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new AuthenticationException("Usuário não encontrado"));

        if (!user.checkPassword(loginDTO.getPassword())) {
            throw new AuthenticationException("Senha incorreta");
        }

        this.currentUser = user;
        return user;
    }

    // Logout
    public void logout() {
        this.currentUser = null;
    }

    public boolean isAuthenticated() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Criar usuário
    public User createUser(String name, String username, String password, boolean isAdmin) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username já está em uso");
        }

        User newUser = new User(name, username, password, isAdmin);
        return userRepository.save(newUser);
    }

    // Listar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar usuário por ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    // Deletar usuário
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado para deletar");
        }
        userRepository.deleteById(id);
    }
}
