package fr.durandal.durandalback.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@Autowired
	ProductDAO productDAO;
	
	@GetMapping(value="/produit", produces= MediaType.APPLICATION_JSON_VALUE)
	public Product getProductDetails( @RequestParam(value="id" ) Long id) {
		return productDAO.getProductDetailsByID(id);
	}

	@RequestMapping(value = "/produit", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
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

}
