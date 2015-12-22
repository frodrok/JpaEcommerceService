package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.fredrikandthenurses.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

/**
 * Created by TheYellowBelliedMarmot on 2015-12-22.
 */
public class JpaProductRepositoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ProductRepository jpr;
    private EntityManagerFactory emf;
    private Product product;

    @Before
    public void setup()
    {
        emf = Persistence.createEntityManagerFactory("lokaldatabas");
        jpr = new JpaProductRepository(emf);
        product = new Product("123", "Brooklyn Lager", 15.90);
    }

    @Test
    public void productShouldBePersisted(){
        System.out.println(product.toString());
        jpr.saveOrUpdate(product);
        System.out.println(product.toString());

    }

    @Test
    public void  productShouldBeUpdated(){
        jpr.saveOrUpdate(product);
        Product product1 = jpr.findByProductName("Brooklyn Lager");
        System.out.println(product1.isAvailable());
        product1.setAvailable(false);
        jpr.saveOrUpdate(product1);
        System.out.println(product1.isAvailable());
    }

    @Test
    public void productShouldBeRetrievableByNumber(){
        jpr.saveOrUpdate(product);
        System.out.println(jpr.findByProductNumber("123"));

    }

    @Test
    public void getByIdTest(){
        jpr.saveOrUpdate(product);
        System.out.println(jpr.find(product.getId()));
    }

    @Test
    public void allProductsShouldBeRetrievable(){
        jpr.saveOrUpdate(product);
        Product product1 = new Product("234", "sol", 11.99);
        jpr.saveOrUpdate(product1);

        for (Product product2 : jpr.getAll()) {
            System.out.println(product2);

        }
    }



}