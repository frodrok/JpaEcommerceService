package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Test;
import se.fredrikandthenurses.model.OrderRow;
import se.fredrikandthenurses.model.PersistableOrder;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.Order;

import static org.junit.Assert.*;

/**
 * Created by joanne on 22/12/15.
 */
public class JpaOrderRepositoryTest {

    private OrderRepository orderRepo;
    private Product product;
    private OrderRow orderRow;
    private User user;
    private PersistableOrder persistableOrder;
    private EntityManagerFactory factory;

    @Before
    public void setup(){
        factory = Persistence.createEntityManagerFactory("lokaldatabas");
        orderRepo = new JpaOrderRepository(factory);
        product = new Product("789", "corona", 10.00);
        orderRow =new OrderRow(product, 10);
        user = new User("joanne", "123");
        persistableOrder = new PersistableOrder("123", user);
        persistableOrder.addOrderRow(orderRow);

    }

    @Test
    public void testFindByOrderNumber() throws Exception {
        orderRepo.saveOrUpdate(persistableOrder);
//        System.out.println(orderRepo.find(persistableOrder.getId()));
        System.out.println(persistableOrder);

    }

    @Test
    public void testFindByUser() throws Exception {

    }

    @Test
    public void testFindOrdersByStatus() throws Exception {

    }

    @Test
    public void testFindByMinimumPrice() throws Exception {

    }

    @Test
    public void testSaveOrUpdate() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }
}