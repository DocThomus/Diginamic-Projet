package fr.durandal.durandalback.user;

import javax.persistence.*;

@Entity
public class Visitor {
	
	@Id
	@SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", initialValue=1)
	@GeneratedValue(generator = "user_id_seq")
	private int id;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String hashedPassword;
	
	@Column
	private String role;
	
	public Visitor(int id, String email, String hashedPassword, String role) {
		super();
		this.id = id;
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.role = role;
	}
	
	public Visitor(String email, String hashedPassword) {
		this.email = email;
		this.hashedPassword = hashedPassword;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return hashedPassword;
	}
	
	public void setPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void SetRole(String role) {
		this.role = role;
	}	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", hashedPassword=" + hashedPassword + ", role=" + role + "]";
	}

	// TEST
	public static Visitor getTestUser() {
		return new Visitor(42, "Greg@greg.com", "123pass", "utilisateur");
	}
}
