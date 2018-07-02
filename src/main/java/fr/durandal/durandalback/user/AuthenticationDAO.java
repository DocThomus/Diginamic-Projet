package fr.durandal.durandalback.user;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.durandal.durandalback.DatabaseHelper;


@RestController
public class AuthenticationDAO {

	@RequestMapping("/testUser")
	public User getTestUser() {
		return User.getTestUser();
	}

	// Methode pour créer un compte utilisateur
	@PostMapping(value="/user/new", consumes= MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody String email, @RequestBody String hashedPassword) {
		User user = new User(email, hashedPassword);
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(user);
		// TODO : traiter le cas où l'email est déjà présent en bdd
		DatabaseHelper.commitTxAndClose(em);		
		// TODO : vérifier l'id de user (soit mis a jour après le persist).
		return user;
	}


	// Méthode pour se connecter
	@PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public User logUser(@RequestBody String email, @RequestBody String hashedPassword){
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<User> query = em.createQuery(""
				+ "SELECT u"
				+ "FROM User u"
				+ "WHERE u.email = :email",
				User.class);
		query.setParameter("email", email);
		DatabaseHelper.commitTxAndClose(em);
		User user = query.getSingleResult();
		// TODO : cas ou user pas dans base de donnée (il doit se créer un compte)
		// TODO : vérifier le mot de passe
		return user;
	}

	
}














