package fr.durandal.durandalback.user;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationDAO {
	
	@RequestMapping("/visitor")
	public Principal visitor(Principal user) {
		return user;
	}
	
	@RequestMapping("/visitor/details")
	  public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("hello", "Hello World");
	    return model;
	  }

	/*// Methode pour créer un compte utilisateur
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
	}*/

	
}














