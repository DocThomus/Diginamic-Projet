package fr.durandal.durandalback.user;

import java.util.List;

import javax.persistence.*;

import fr.durandal.durandalback.command.Command;

@Entity
public class Visitor {	
	@Id
	@SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", initialValue=1)
	@GeneratedValue(generator = "user_id_seq")
	private int id;
	
	@Column(unique = true)
	private String email;
	
	@Column(name="hashedpassword")
	private String hashedPassword;
	
	@Column(name="isadmin")
	private boolean isAdmin;
	
	@OneToMany
	private List<Command> commands;
	
	public Visitor() {
		
	}
	
	public Visitor(int id, String email, String hashedPassword, boolean isAdmin) {
		super();
		this.id = id;
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.isAdmin = isAdmin;
	}
	
	public Visitor(String email, String hashedPassword, boolean isAdmin) {
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.isAdmin = isAdmin;
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
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void SetIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	
	
	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", hashedPassword=" + hashedPassword + ", isAdmin=" + isAdmin + "]";
	}

	// TEST
	public static Visitor getTestUser() {
		return new Visitor(42, "Greg@greg.com", "123pass", false);
	}
}
