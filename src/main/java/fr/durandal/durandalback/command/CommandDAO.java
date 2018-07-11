package fr.durandal.durandalback.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.durandal.durandalback.product.ProductDAO;
import fr.durandal.durandalback.user.AuthenticationDAO;
import fr.durandal.durandalback.user.Visitor;

@Repository
public class CommandDAO {
	
	@Autowired 
	AuthenticationDAO visitorDAO;
	
	@Autowired
	ProductDAO productDAO;

	@Autowired 
	EntityManager em;
	
	
	@Transactional
	public List<Command> getAllCommands() {
		TypedQuery<Command> query = em.createQuery(" FROM Command", Command.class);
		List<Command> commands = query.getResultList();
		return commands;		
	}
	
	@Transactional
	public List<Command> getCommandsByUserEmail(String userEmail) {
		TypedQuery<Command> query = em.createQuery("SELECT c FROM Command c INNER JOIN c.client cl WHERE cl.email = :userEmail", Command.class);
		query.setParameter("userEmail", userEmail);
		List<Command> commands = query.getResultList();
		return commands;		
	}
	
	@Transactional
	public void addCommand(Command c) {
		em.persist(c);
	}

	@Transactional
	public void addCommand(NewCommand nc) {
		Visitor client = visitorDAO.getUserByEmail(nc.getUserEmail());
		
		Command command = new Command(client);
		em.persist(command);
		
		List<ProductQuantity> contenuCommande = new ArrayList<ProductQuantity>();
		for (Entry<Integer, Integer> kv : nc.getProducts().entrySet()) {
			ProductQuantity productQuantity = new ProductQuantity(productDAO.getProductDetailsByID(kv.getKey().longValue()), kv.getValue());
			productQuantity.command = command;
			em.persist(productQuantity);
			contenuCommande.add(productQuantity);
		}
		
		command.setContenuCommande(contenuCommande);	
		em.merge(command);				
	}	
}
