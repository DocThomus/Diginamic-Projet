package fr.durandal.durandalback.product;

import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {
	@Autowired
	ProductDAO productDAO;
	
	@GetMapping(value="/produit", produces= MediaType.APPLICATION_JSON_VALUE)
	public Product getProductDetails( @RequestParam(value="id" ) Long id) {
		return productDAO.getProductDetailsByID(id);
	}

	@GetMapping(value="/produits", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts() {
		return productDAO.getAllProduct();
	}
	
	@PostMapping(value = "/produit", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.CREATED)
	public String addProduct(@RequestBody Product p) {
		productDAO.addProduct(p);
		return "added";
	}


	@PutMapping(value = "/produit" , consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus( HttpStatus.ACCEPTED)
	public String updateProduct(@RequestBody Product prod) {
		productDAO.updateProduct(prod);
		return "Produit a jour";

	}

	@DeleteMapping(value = "/produit")
	@ResponseStatus( HttpStatus.ACCEPTED)
	public String delProduct(@RequestBody long id) {
		productDAO.deleteProductByID(id);
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
