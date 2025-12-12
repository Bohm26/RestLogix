package enums;

/**
 *
 * @author joaopedro
 */
public enum UserRole {
	MANAGER("Gerente"),
	STORAGE("Estoque"),
	KITCHEN("Cozinha");

	private String description;
	
	UserRole(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
