package fr.durandal.durandalback.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private int phone;
	
	@Column
	private Date birthDate;
	
	@Column
	private String adress;
	
	
	//@OneToMany(mappedBy="id")
	//private List<Command> commands;
	
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
	

	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void SetIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	
	
	
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	/*public List<Command> getCommands() {
		return commands;
	}


	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}*/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
