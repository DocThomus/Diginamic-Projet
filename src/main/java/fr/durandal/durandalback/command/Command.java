package fr.durandal.durandalback.command;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import fr.durandal.durandalback.user.Visitor;

@Entity
public class Command {
	
	@Id
	@SequenceGenerator(name="command_id_seq", sequenceName = "command_id_seq", initialValue=1)
	@GeneratedValue(generator = "command_id_seq")
	private Long id;
	
	@ManyToOne
	Visitor client;
	
	@OneToMany
	List<ProductQuantity> contenuCommande;
	
	public Command() {
		super();
	}

	public Command(Visitor client, List<ProductQuantity> contenuCommande) {
		super();
		this.client = client;
		this.contenuCommande = contenuCommande;
	}

	@Override
	public String toString() {
		return "Command [id=" + id + ", client=" + client + ", contenuCommande=" + contenuCommande + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Visitor getClient() {
		return client;
	}

	public void setClient(Visitor client) {
		this.client = client;
	}

	public List<ProductQuantity> getContenuCommande() {
		return contenuCommande;
	}

	public void setContenuCommande(List<ProductQuantity> contenuCommande) {
		this.contenuCommande = contenuCommande;
	}
	
	

}
