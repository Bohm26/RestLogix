package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author joaopedro
 */
@Entity	 //Define que é uma Entidade
@Table(name = "inventory")	 //Define o nome no Banco de Dados
public class Inventory {

	@Id		//Indica que é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//Define como o valor é gerado, nesse caso: Usa o auto incremento
	private Integer id;

	@Column(nullable = false)	//Equivale ao NotNull no SQL
	private String name;

	@Column(nullable = false)
	private Double quantity;

	@Column(name = "expiry_date")	//Personaliza o nome da coluna, no banco é expiry_date
	private LocalDate expiryDate;		//No Java será nomeado como expiryDate 

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	private int Min_stock;

	public Inventory() {
	}

	public Inventory(Integer id, String name, Double quantity, LocalDate expiryDate, int Min_stock) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.expiryDate = expiryDate;
		this.Min_stock = Min_stock;
	}

	public int getMin_stock() {
		return Min_stock;
	}

	public void setMin_stock(int min_stock) {
		this.Min_stock = min_stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
