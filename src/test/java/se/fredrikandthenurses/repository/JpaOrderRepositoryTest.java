package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Test;
import se.fredrikandthenurses.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.Order;

import static org.junit.Assert.*;

/**
 * Created by joanne on 22/12/15.
 */
public class JpaOrderRepositoryTest {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    private Product product;
    private OrderRow orderRow;
    private User user;
    private PersistableOrder persistableOrder;
    private EntityManagerFactory factory;

    @Before
    public void setup(){
        factory = Persistence.createEntityManagerFactory("lokaldatabas");

        orderRepository = new JpaOrderRepository(factory);
        userRepository = new JpaUserRepository(factory);
        productRepository = new JpaProductRepository(factory);

        product = new Product("789", "corona", 10.00);
        orderRow =new OrderRow(product, 10);
        user = new User("joanne", "123");
        persistableOrder = new PersistableOrder("123", user);
        persistableOrder.addOrderRow(orderRow);
        orderRow.addOrder(persistableOrder);

    }

    @Test
    public void testFindByOrderNumber() throws Exception {
        orderRepository.saveOrUpdate(persistableOrder);
        orderRepository.findByOrderNumber("123").getOrderRowList().forEach(orderRow1 -> System.out.println(orderRow1.getPrice()));
    }

    @Test
    public void saveRegularOrderObject() {
        System.out.println(persistableOrder.getId());
        // orderRow.addOrder(persistableOrder);
        persistableOrder.addOrderRow(orderRow);
        orderRepository.saveOrUpdate(persistableOrder);
        System.out.println(persistableOrder.getId());
    }

    @Test
    public void testFindByUser() throws Exception {

        orderRepository.saveOrUpdate(persistableOrder);
        orderRepository.findByUser(user);
        orderRepository.findByUser(user).forEach(order -> System.out.println(order.getUser().getUsername()));
    }

    @Test
    public void testFindOrdersByStatus() throws Exception {
        orderRepository.saveOrUpdate(persistableOrder);
        orderRepository.findOrdersByStatus(persistableOrder.getStatus()).forEach(order -> System.out.println(order.getStatus().toString()));

}

    @Test
    public void testFindByMinimumPrice() throws Exception {

        orderRepository.saveOrUpdate(persistableOrder);
        orderRepository.findByMinimumPrice(101.00).forEach(order -> System.out.println(order.getPrice()));

    }

    @Test
    public void testGetAllOrderRowsFromOneOrder() {
        orderRepository.saveOrUpdate(persistableOrder);
        for (OrderRow o : orderRepository.find(persistableOrder.getId()).getOrderRowList()) {
            System.out.println(o.getProduct().getProductName());
        }

    }
}