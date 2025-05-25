package models;

/**
 *
 * @author joaopedro
 */
import enums.MovementType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements")
public class InventoryMovement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)		//Define o relacionamento de Muitos para Um
	@JoinColumn(name = "inventory_id", nullable = false)		//Personaliza o nome da chave estrangeira, no banco é inventory_id
	private Inventory inventory;						//Aqui no Java, será inventory

	@Enumerated(EnumType.STRING)		//Mapeia um enum para a coluna no banco o .STRING faz o nome literal ser armazenado
	@Column(nullable = false)
	private MovementType type;		 // INBOUND funciona como entrada e OUTBOUND como saída

	@Column(nullable = false)
	private Double quantity;

	@Column(name = "movement_date", nullable = false)
	private LocalDateTime movementDate = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(length = 500)			//Define o tamanho máximo do Campo, nesse caso 500 caracteres
	private String notes;


	public InventoryMovement(Inventory item, MovementType INBOUND, Double quantity1, User user1, String notes1) {
	}

	public InventoryMovement(Integer id, Inventory inventory, MovementType type, Double quantity, User user, String notes) {
		this.id = id;
		this.inventory = inventory;
		this.type = type;
		this.quantity = quantity;
		this.user = user;
		this.notes = notes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(LocalDateTime movementDate) {
		this.movementDate = movementDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	//Método para registrar a movimentação de um item
	public static InventoryMovement createInbound(Inventory item, Double quantity, User user, String notes) {
		return new InventoryMovement(item, MovementType.INBOUND, quantity, user, notes);
	}

}
