package fr.durandal.durandalback.product;

import java.awt.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.durandal.durandalback.DatabaseHelper;

@RestController
public class ProductDAO {

	@GetMapping(value="/produit", produces= MediaType.APPLICATION_JSON_VALUE)
	public  Product getProductDetails( @RequestParam(value="id" ) Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();
		
		Product p = em.find(Product.class, id);
		System.out.println(p.getDescription());
		return p;
	}

	 @PostMapping(value = "/produit", consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus( HttpStatus.CREATED)
	public String addProduct(@RequestBody Product p) {
		
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(p);
		DatabaseHelper.commitTxAndClose(em);
		return "Produit Ajouté";
		
	}
	 
	 
	 @PutMapping(value = "/produit" , consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus( HttpStatus.ACCEPTED)
	 public String updateProduct(@RequestBody Product prod) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.merge(prod);
		DatabaseHelper.commitTxAndClose(em);
		 
		 return "Produit a jour";
		 
	 }
	 
	 @DeleteMapping(value = "/produit")
	 @ResponseStatus( HttpStatus.ACCEPTED)
	 public String delProduct(@RequestBody long id) {
			EntityManager em = DatabaseHelper.createEntityManager();
			DatabaseHelper.beginTx(em);
			Product prod = em.find(Product.class, id);
			em.remove(prod);
			DatabaseHelper.commitTxAndClose(em);
			 
			 return "Produit Supprimé";
			 
		 }
	
}
