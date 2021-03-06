package fr.durandal.durandalback.product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Type;

import fr.durandal.durandalback.command.ProductQuantity;

@Entity
public class Product {
	
	@Id
	@SequenceGenerator(name="product_id_seq", sequenceName = "product_id_seq", initialValue=1)
	@GeneratedValue(generator = "product_id_seq")
	private Long id;
	
	/* ON FERA CA PLUS TARD
	@Column
	private String ref;
	*/
	
	@Type (type = "text")
	@Column
	private String name;
	
	@Column(columnDefinition="text")
	private String description;
	
	@Column
	@Min(value = 0)
	private double price;
	
	
	@Column
	@Min(value = 0)
	private Integer quantity; 
	
	@Column
	private String picture;
	
	@Column
	private String type;
	
	@Column
	private String editor;
	
	@Column(name="is_active")
	private boolean isActive = true;
	
	@OneToMany(mappedBy="id")
	private List<ProductQuantity> commandated;
	
	// Constructeure par défaut
	public Product() {
		
	}
	
	// Constructeur initialisation des produits en base (lien ProductDAO)
	public Product (String name, String description, double price, int quantity, String type, String picture, String editor) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		this.picture = picture;
		this.editor = editor;
	}
	
	public Product (String name, String description, double price, int quantity, String type, String picture, String editor, boolean isActive) {
		this(name, description, price, quantity, type, picture, editor);
		this.isActive = isActive;
	}
	




	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getEditor() {
		return editor;
	}


	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	
	
	/* ON FERA CA PLUS TARD
	public String getRef() {
		return ref;
	}

	public void setRef() {
		this.ref = this.getType().substring(0, 3)+this.getId();
	}
	*/
	
}
