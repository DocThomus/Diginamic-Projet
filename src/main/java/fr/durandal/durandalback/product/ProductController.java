package fr.durandal.durandalback.product;

import java.net.URL;

import javax.persistence.EntityManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import fr.durandal.durandalback.DatabaseHelper;

@RestController
public class ProductController {
	@GetMapping(value="/produit", produces= MediaType.APPLICATION_JSON_VALUE)
	public  Product getProductDetails( @RequestParam(value="id" ) Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();

		Product p = em.find(Product.class, id);
		System.out.println(p.getDescription());
		return p;
	}

	@RequestMapping(value = "/produit", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
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
	
	@GetMapping (value = "/image/{imageName}", produces= MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody 
	public ResponseEntity<Resource> getImageProduct(@PathVariable(value="imageName" ) String imageName) {
		URL path = this.getClass().getClassLoader().getResource("images/" + imageName);
		//System.out.println(path.toString());
		ApplicationContext appContext = new ClassPathXmlApplicationContext();


		Resource res = appContext.getResource(path.toString());
		
	
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "images/jpg").body(res);
	}

}
