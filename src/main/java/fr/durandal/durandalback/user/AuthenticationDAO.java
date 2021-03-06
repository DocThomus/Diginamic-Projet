package fr.durandal.durandalback.user;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationDAO {

	@Autowired
	EntityManager em;

	@Transactional
	public Visitor getUserByEmail(String email) {
		TypedQuery<Visitor> query = em.createQuery(""
				+ "SELECT v "
				+ "FROM Visitor v "
				+ "WHERE v.email = :email ",
				Visitor.class);
		query.setParameter("email", email);
		Visitor user = query.getSingleResult();
		// TODO : cas ou user pas dans base de donnée (il doit se créer un compte)
		// TODO : vérifier le mot de passe
		return user;
	}

	@Transactional
	public void addUser(Visitor v) {
		System.out.println(v);
		System.out.println(em);
		em.persist(v);
	}
	
	@Transactional
	public void updateUser(Visitor v) {
		em.merge(v);
		
	}
	
	@Transactional
	public void mockupUsers() {
		List<Visitor> usersMockup = new ArrayList<Visitor>();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		usersMockup.add(new Visitor("admin", passwordEncoder.encode("admin"), true));
		usersMockup.add(new Visitor("user", passwordEncoder.encode("user"), false));
		usersMockup.add(new Visitor("Champion", passwordEncoder.encode("pass"), true));
		usersMockup.add(new Visitor("Michel", passwordEncoder.encode("pass"), false));

		for (Visitor v : usersMockup) {
			em.persist(v);
		}
	}

}














