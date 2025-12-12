package domain;

public class Recipe {
    private Long id;
    private String name;
    private String description;
    private Integer preparationTime;
    
    public Recipe() {}
    
    public Recipe(String name) {
        this.name = name;
    }
    
    public Recipe(String name, String description, Integer preparationTime) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getPreparationTime() { return preparationTime; }
    public void setPreparationTime(Integer preparationTime) { 
        this.preparationTime = preparationTime; 
    }
}