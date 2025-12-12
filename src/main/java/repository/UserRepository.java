/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author joaopedro
 */
import domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

	User save(User user);

	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

	List<User> findAll();

	void delete(Long id);

	boolean existsByUsername(String username);
}
