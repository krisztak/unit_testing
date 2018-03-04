package com.example.layering.business.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.layering.business.api.client.ClientBO;
import com.example.layering.business.impl.client.ClientBOImpl;
import com.example.layering.data.api.client.ClientDO;
import com.example.layering.data.api.client.ProductDO;
import com.example.layering.model.api.client.Amount;
import com.example.layering.model.api.client.Client;
import com.example.layering.model.api.client.Currency;
import com.example.layering.model.api.client.Product;
import com.example.layering.model.api.client.ProductType;
import com.example.layering.model.impl.client.AmountImpl;
import com.example.layering.model.impl.client.ClientImpl;
import com.example.layering.model.impl.client.ProductImpl;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class ClientBOMockitoTest {

    ArgumentCaptor<Client> clientArgumentCaptured = ArgumentCaptor
            .forClass(Client.class);

    private static long id= 1;

    @Mock
    private ProductDO productDO;

    @Mock
    private ClientDO clientDO;

    @InjectMocks
    private ClientBO clientBO = new ClientBOImpl();


    @Test
    public void testClientProductSum() {

        List<Product> products = Arrays.asList(
                createProductWithAmount("5.0"), createProductWithAmount("6.0"));

       Mockito.when(productDO.getAllProducts(eq(1L))).thenReturn(products);

        clientBO.getClientProductsSum(1L);

        clientBO.getClientProductsSum(2L);
        assertAmountEquals(
                new AmountImpl(new BigDecimal("11.0"), Currency.EURO),
                clientBO.getClientProductsSum(id));
    }


    @Ignore
    @Test
    public void saveChangedProducts_ProductInDatabaseButNotInScreen_Deleted() {

        Product productFromDatabase = createProduct(id);

        List<Product> databaseProducts = Arrays.asList(productFromDatabase);
        List<Product> emptyScreenProducts = new ArrayList<Product>();
        when(productDO.getAllProducts(anyLong())).thenReturn(databaseProducts);

        clientBO.saveChangedProducts(1, emptyScreenProducts);
        verify(productDO, atLeastOnce()).deleteProduct(anyLong(), any(Product.class));

    }

    @Test
    public void calculateAndSaveClientProductSum1() {

        ClientImpl client = createClientWithProducts(
                createProductWithAmount("6.0"),
                createProductWithAmount("6.0"));

        clientBO.calculateAndSaveClientProductSum(client);

        verify(clientDO).saveClient(clientArgumentCaptured.capture());


        assertEquals(new BigDecimal("12.0"), clientArgumentCaptured.getValue()
                .getProductAmount());

    }

    private ClientImpl createClientWithProducts(Product... products) {
        ClientImpl client = new ClientImpl(0, null, null,
                null, Arrays.asList(products));
        return client;
    }

    private void assertAmountEquals(Amount expectedAmount, Amount actualAmount) {
        assertEquals(expectedAmount.getCurrency(),
                actualAmount.getCurrency());
        assertEquals(expectedAmount.getValue(),
                actualAmount.getValue());
    }

    private Product createProductWithAmount(String amount) {
        return new ProductImpl(100, "Product 15",
                ProductType.BANK_GUARANTEE, new AmountImpl(
                new BigDecimal(amount), Currency.EURO));
    }


    private Product createProduct(long id) {
        return new ProductImpl(id, "Product 15",
                ProductType.BANK_GUARANTEE, new AmountImpl(
                new BigDecimal("5.0"), Currency.EURO));
    }

    private void createDynamicResponse() {
        when(productDO.getAllProducts(anyLong()))
                .thenAnswer((Answer<List<Product>>) invocation -> Arrays.asList(createProduct(id++)));
    }
}