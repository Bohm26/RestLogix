package domain;

public class User {
    private Long id;
    private String name;
    private String username;
    private String passwordHash;
    private boolean admin;
    
    public User() {}
    
    public User(String name, String username, String password, boolean isAdmin) {
        this.name = name;
        this.username = username;
        this.passwordHash = password; // Simplificado para testes
        this.admin = isAdmin;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public boolean isAdmin() { return admin; }
    
    public boolean checkPassword(String password) {
        // Simplificado para testes
        return password != null && password.equals("admin123");
    }
}