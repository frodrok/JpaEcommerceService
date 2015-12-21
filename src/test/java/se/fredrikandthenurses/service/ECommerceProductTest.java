package se.fredrikandthenurses.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by joanne on 21/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ECommerceProductTest {

    @Rule
    public ExpectedException exception= ExpectedException.none();

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private OrderRepository orderRepositoryMock;

    @InjectMocks
    private ECommerceService service;

    private Product product;

    @Before
    public void setup(){
        product = new Product("100", "julmust", 5.00);
    }

    @Test
    public void productShouldBeAddedOrUpdated(){
        service.addProduct(product);
        verify(productRepositoryMock, times(1)).saveOrUpdate(product);
    }

    @Test
    public void productShouldBeRetrievableByProductNumber() {
        service.addProduct(product);

        when(productRepositoryMock.findByProductNumber(product.getProductNumber())).thenReturn(product);

        assertThat(service.findByProductNumber(product.getProductNumber()), equalTo(product));

        verify(productRepositoryMock, times(1)).saveOrUpdate(product);
        verify(productRepositoryMock, times(1)).findByProductNumber(product.getProductNumber());
    }

    @Test
    public void productShouldBeRetrievableByProductName(){

        service.addProduct(product);

        when(productRepositoryMock.findByProductName(product.getProductName())).thenReturn(product);

        assertThat(service.findByProductName(product.getProductName()), equalTo(product));

        verify(productRepositoryMock, times(1)).saveOrUpdate(product);
        verify(productRepositoryMock, times(1)).findByProductName(product.getProductName());

    }



}