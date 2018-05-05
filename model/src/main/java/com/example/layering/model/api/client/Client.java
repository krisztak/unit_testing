package com.example.layering.model.api.client;

import java.math.BigDecimal;
import java.util.List;

/**
 * Client Model API.
 */
public interface Client {

	long getId();
	
	String getName();

	Enum<?> getType();

	List<Collateral> getCollaterals();

	List<Product> getProducts();

	void setProducts(List<Product> products);

	void setProductAmount(BigDecimal productAmount);

	BigDecimal getProductAmount();

	void setName(String name);
	
}
