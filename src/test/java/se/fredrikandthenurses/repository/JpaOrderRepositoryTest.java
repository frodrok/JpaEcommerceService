package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Test;
import se.fredrikandthenurses.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.Order;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
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
        userRepository.saveOrUpdate(user);
        persistableOrder = new PersistableOrder("123", user);
        persistableOrder.addOrderRow(orderRow);
        orderRow.addOrder(persistableOrder);
        orderRepository.saveOrUpdate(persistableOrder);

    }

    @Test
    public void testFindOrderById(){
        assertThat(orderRepository.find(persistableOrder.getId()), equalTo(persistableOrder));
    }

    @Test
    public void testFindByOrderNumber() throws Exception {
        assertThat(orderRepository.findByOrderNumber("123"), equalTo(persistableOrder));
    }

    @Test
    public void testFindByUser() throws Exception {
        PersistableOrder persistableOrderTwo = new PersistableOrder("124", user);
        orderRepository.saveOrUpdate(persistableOrderTwo);
        assertThat(orderRepository.findByUser(user), contains(persistableOrder, persistableOrderTwo));
    }

    @Test
    public void testFindOrdersByStatus() throws Exception {
        assertThat(orderRepository.findOrdersByStatus(persistableOrder.getStatus()), contains(persistableOrder));
}

    @Test
    public void testFindByMinimumPrice() throws Exception {
        assertThat(orderRepository.findByMinimumPrice(99.00), contains(persistableOrder));
    }

    @Test
    public void testGetAll(){
        assertThat(orderRepository.getAll(), contains(persistableOrder));
    }
}