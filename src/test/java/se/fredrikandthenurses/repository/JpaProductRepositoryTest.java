package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.fredrikandthenurses.model.Product;

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



}