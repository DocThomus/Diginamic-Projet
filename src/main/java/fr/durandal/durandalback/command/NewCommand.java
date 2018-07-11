package fr.durandal.durandalback.command;

import java.util.Map;

public class NewCommand {
	private String userEmail;
	private Map<Integer, Integer> products;
	
	public NewCommand(String userEmail, Map<Integer, Integer> products) {
		super();
		this.userEmail = userEmail;
		this.products = products;
	}

	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public Map<Integer, Integer> getProducts() {
		return products;
	}
	
	public void setProducts(Map<Integer, Integer> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "NewCommand [userEmail=" + userEmail + ", products=" + products + "]";
	}	
}
