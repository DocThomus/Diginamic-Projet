package fr.durandal.durandalback.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Type;

@Entity
public class Product {
	
	@Id
	@SequenceGenerator(name="product_id_seq", sequenceName = "product_id_seq", initialValue=1)
	@GeneratedValue(generator = "product_id_seq")
	private Long id;
	
	@Column
	private String nom;
	
	@Column(length=1000000)
	private String description;
	
	@Column
	@Min(value = 0)
	private double prix;
	
	
	@Column
	@Min(value = 0)
	private Integer quantite; 
	
	@Column
	private String imageURI;
	
	@Column
	private String categorie;
	
	// Constructeur initialisation des produits en base (lien ProductDAO)
	public Product (String nom, String description, double prix, Integer quantite, String categorie, String imageURI) {
	this.nom = nom;
	this.description = description;
	this.prix = prix;
	this.quantite = quantite;
	this.categorie = categorie;
	this.imageURI = imageURI;
	}
	
	
	

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public String getImageURI() {
		return imageURI;
	}

	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}
	
	
}
