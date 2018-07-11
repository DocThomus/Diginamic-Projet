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

	@Transactional 
	public void initCommands() {
		Command command1 = new Command(visitorDAO.getUserByEmail("Champion"));

		List<ProductQuantity> contenuCommand1 = new ArrayList<ProductQuantity>();
		ProductQuantity p1c1 = new ProductQuantity(productDAO.getProductDetailsByID((long) 4), 1);
		p1c1.command = command1;
		contenuCommand1.add(p1c1);
		ProductQuantity p2c1 = new ProductQuantity(productDAO.getProductDetailsByID((long) 8), 1);
		p2c1.command = command1;
		contenuCommand1.add(p2c1);
		ProductQuantity p3c1 = new ProductQuantity(productDAO.getProductDetailsByID((long) 21), 2);
		p3c1.command = command1;
		contenuCommand1.add(p3c1);

		command1.setContenuCommande(contenuCommand1);
		em.persist(command1);
		em.persist(p1c1);
		em.persist(p2c1);
		em.persist(p3c1);


		
		
		Command command2 = new Command(visitorDAO.getUserByEmail("Michel"));

		List<ProductQuantity> contenuCommand2 = new ArrayList<ProductQuantity>();
		ProductQuantity p1c2 = new ProductQuantity(productDAO.getProductDetailsByID((long) 5), 1);
		p1c2.command = command2;
		contenuCommand1.add(p1c2);
		ProductQuantity p2c2 = new ProductQuantity(productDAO.getProductDetailsByID((long) 13), 1);
		p2c2.command = command2;
		contenuCommand1.add(p2c2);
		ProductQuantity p3c2 = new ProductQuantity(productDAO.getProductDetailsByID((long) 28), 1);
		p3c2.command = command2;
		contenuCommand1.add(p3c2);

		command2.setContenuCommande(contenuCommand2);
		em.persist(command2);
		em.persist(p1c2);
		em.persist(p2c2);
		em.persist(p3c2);
	}

}
