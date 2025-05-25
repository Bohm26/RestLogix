package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author joaopedro
 */
@Entity
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(length = 100)
	private String contactName;

	@Column(length = 20)
	private String phone;

	@Column(length = 100, unique = true)
	private String email;

	@Column(length = 200)
	private String address;

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)		//Relacionamento um para Muitos, a chave estrangeira esta em Inventory
	private List<Inventory> suppliedItems;		// Itens fornecidos por este supplier

	public Supplier() {
	}

	public Supplier(Integer id, String name, String contactName, String phone, String email, String address, List<Inventory> suppliedItems) {
		this.id = id;
		this.name = name;
		this.contactName = contactName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.suppliedItems = suppliedItems;
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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Inventory> getSuppliedItems() {
		return suppliedItems;
	}

	public void setSuppliedItems(List<Inventory> suppliedItems) {
		this.suppliedItems = suppliedItems;
	}

}
