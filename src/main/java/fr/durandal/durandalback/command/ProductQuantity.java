package fr.durandal.durandalback.command;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import fr.durandal.durandalback.product.Product;

@Entity
public class ProductQuantity {
	
	@Id
	@SequenceGenerator(name="pq_id_seq", sequenceName = "pq_id_seq", initialValue=1)
	@GeneratedValue(generator = "pq_id_seq")
	private Long id;
	
	@ManyToOne
	Product product;
	
	@Column
	Integer quantity;
	
	public ProductQuantity() {
		super();
	}
	
	public ProductQuantity(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductQuantity [product=" + product + ", quantity=" + quantity + "]";
	}
}
