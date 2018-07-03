package fr.durandal.durandalback.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;

@Entity
public class Product {
	
	@Id
	@SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", initialValue=1)
	@GeneratedValue(generator = "user_id_seq")
	private Long id ;
	
	@Column
	private String nom ;
	
	@Column
	private String description;
	
	@Column
	@Min(value = 0)
	private Integer prix ;
	
	
	@Column
	@Min(value = 0)
	private Integer quantite; 
	
	@Column
	private String imageURI ;
	
	@Column
	private String categorie;

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

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
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
