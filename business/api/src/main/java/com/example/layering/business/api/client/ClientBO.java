package com.example.layering.business.api.client;

import java.util.List;

import com.example.layering.model.api.client.Amount;
import com.example.layering.model.api.client.Client;
import com.example.layering.model.api.client.Product;

/**
 * Business Interface for Client.
 */
public interface ClientBO {

    /**
     * Sum of product amounts. Assumes Currency as EURO.
     * @param clientId clientId
     * @return the sum amount
     */
    Amount getClientProductsSum(long clientId);

    /**
     * Compares the new products with existing products and saves changed
     * products.
     * @param clientId clientId
     * @param newProducts
     */
    void saveChangedProducts(long clientId, List<Product> newProducts);

    /**
     * Calculates client product sum and saves it to database.
     * @param client
     */
    void calculateAndSaveClientProductSum(Client client);

    void saveClientWithRetry(Client client);

    /**
     * Returns saved details for a client
     * @param clientId
     */
    Client getClientDetailsWithTotalAmount(long clientId);

    /**
     * Dummy method to simulatre rename client
     * @param client, name
     */
    Client simulateClientReset(Client client);

    /**
     * Change client details
     * @param client, clientId
     */
    void changeClient(Client client, long clientId);


    /**
     * Change client details
     * @param clientId
     */
    void increaseProductPriceForClient(long clientId);

}