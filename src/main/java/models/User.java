package models;

import enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import utils.PasswordService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaopedro
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "full_name", nullable = false, length = 100)
	private String fullName;

	@Column(unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InventoryMovement> movements = new ArrayList<>();

	public User() {
	}

	public User(Integer id, String fullName, String username, String passwordHash, UserRole role) {
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public void setPassword(String plainPassword) {
		this.passwordHash = PasswordService.hashPassword(plainPassword);
	}

	public boolean checkPassword(String plainPassword) {
		return PasswordService.checkPassword(plainPassword, this.passwordHash);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<InventoryMovement> getMovements() {
		return movements;
	}

	public void setMovements(List<InventoryMovement> movements) {
		this.movements = movements;
	}

}
