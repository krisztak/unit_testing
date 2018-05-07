package com.example.layering.business.impl.client;

import com.example.layering.business.api.client.ClientBO;
import com.example.layering.data.api.client.ClientDO;
import com.example.layering.data.api.client.ProductDO;
import com.example.layering.model.api.client.Amount;
import com.example.layering.model.api.client.Client;
import com.example.layering.model.api.client.Currency;
import com.example.layering.model.api.client.Product;
import com.example.layering.model.impl.client.AmountImpl;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Data Object for the Client entity.
 */
@Service
public class ClientBOImpl implements ClientBO {

	@Autowired
	ProductDO productDO;

	@Autowired
	ClientDO clientDO;

	@Override
	public Client getClientDetailsWithTotalAmount(long clientId) {
		List<Product> existingProducts = productDO.getAllProducts(clientId);

		Client clientWithDetails = clientDO.getClientDetails(clientId);

		clientWithDetails.setProductAmount(calculateClientProductSum(existingProducts));
		return clientWithDetails;
	}

	@Override
	public Client addNewClientSetProductSum(Client client) {

		for (Product newProduct : client.getProducts()) {
			productDO.insertProduct(client.getId(), newProduct);
		}

		client.setProductAmount(calculateClientProductSum(client.getProducts()));
		clientDO.saveClient(client);

		return client;
	}

	@Override
	public Amount getClientProductsSum(long clientId) {

		List<Product> existingProducts = productDO.getAllProducts(clientId);

		return new AmountImpl(calculateClientProductSum(existingProducts),
				Currency.EURO);
	}

	@Override
	public Client simulateClientReset(Client client) {
		client.setName(null);
		client.setProductAmount(BigDecimal.ZERO);
		client.setProducts(Collections.emptyList());
		return client;
	}

	@Override
	public void changeClient(Client client, long clientId) {
		Client clientToUpdate = clientDO.getClientDetails(clientId);
		clientToUpdate.setName(client.getName());
		clientToUpdate.setProductAmount(client.getProductAmount());
		clientDO.saveClient(clientToUpdate);
	}

	@Override
	public void saveChangedProducts(long clientId,
			List<Product> userEnteredProducts) {

		List<Product> databaseProducts = productDO.getAllProducts(clientId);

		updateExistingProductsWhichAreModified(clientId, userEnteredProducts,
				databaseProducts);

		insertNewProducts(clientId, userEnteredProducts, databaseProducts);

		deleteStaleProducts(clientId, databaseProducts, userEnteredProducts);

	}

	@Override
	public void calculateAndSaveClientProductSum(Client client) {
		BigDecimal clientProductSum = calculateClientProductSum(client
				.getProducts());
		client.setProductAmount(clientProductSum);
		clientDO.saveClient(client);
	}

	@Override
	public void saveClientWithRetry(Client client){

		try {
			clientDO.saveClient(client);
		}catch(RuntimeException e){

		client.setName("DefaultName");
		clientDO.saveClient(client);}
	}

	@Override
	public void increaseProductPriceForClient(long clientId){
		List<Product> existingProducts = productDO.getAllProducts(clientId);
		for (Product newProduct : existingProducts) {
				Amount increasedAmount = newProduct.getAmount();
				increasedAmount.setValue(increasedAmount.getValue().multiply(BigDecimal.valueOf(1.2)));
				newProduct.setAmount(increasedAmount);
				productDO.updateProduct(clientId, newProduct);
			}
		}

	private void deleteStaleProducts(long clientId,
			List<Product> existingProducts, List<Product> newProducts) {
		Map<Long, Product> newProductsMap = convertToMap(newProducts);
		for (Product product1 : existingProducts) {
			if (!newProductsMap.containsKey(product1.getId())) {
				productDO.deleteProduct(clientId, product1);
			}
		}
	}

	private void insertNewProducts(long clientId, List<Product> newProducts,
			List<Product> existingProducts) {
		Map<Long, Product> existingProductsMap = convertToMap(existingProducts);
		for (Product newProduct : newProducts) {
			if (!existingProductsMap.containsKey(newProduct.getId())) {
				productDO.insertProduct(clientId, newProduct);
			}
		}
	}

	private void updateExistingProductsWhichAreModified(long clientId,
			List<Product> newProducts, List<Product> existingProducts) {
		Map<Long, Product> existingProductsMap = convertToMap(existingProducts);
		for (Product newProduct : newProducts) {
			if (existingProductsMap.containsKey(newProduct.getId())) {
				productDO.updateProduct(clientId, newProduct);
			}
		}
	}

	private Map<Long, Product> convertToMap(List<Product> products) {
		Map<Long, Product> productMap = new HashMap<Long, Product>();
		for (Product existingProduct : products) {
			productMap.put(existingProduct.getId(), existingProduct);
		}
		return productMap;
	}

	private BigDecimal calculateClientProductSum(List<Product> existingProducts) {
		BigDecimal clientProductsSum = BigDecimal.ZERO;

		for (Product product : existingProducts) {
			clientProductsSum = clientProductsSum.add(product.getAmount()
					.getValue());
		}
		return clientProductsSum;
	}

}