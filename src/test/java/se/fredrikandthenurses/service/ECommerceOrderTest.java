package se.fredrikandthenurses.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import se.fredrikandthenurses.model.Order;
import se.fredrikandthenurses.model.OrderRow;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ECommerceOrderTest {

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

    private User user;
    private OrderRow orderRow;
    private List<OrderRow> orderRowList;
    private Product product;
    private Order order;

    @Before
    public void setup() {
        user = new User("Fredrik", "alalalal");
        product = new Product("100", "Jackie D", 19.99);
        orderRow = new OrderRow(product, 100);
        orderRowList = new ArrayList<>();
        orderRowList.add(orderRow);
        order = new Order("1001", user, orderRowList);
    }

    @Test
    public void orderShouldBeAddedOrUpdated() {
        service.addOrder(order);
        verify(orderRepositoryMock).saveOrUpdate(order);
    }

    @Test
    public void orderShouldBeRetrievableByOrderNumber() {
        service.addOrder(order);
        verify(orderRepositoryMock).saveOrUpdate(order);

        when(orderRepositoryMock.findByOrderNumber("1001")).thenReturn(order);

        assertThat(service.getAllOrders(), contains(order));
        verify(orderRepositoryMock,times(1)).getAll();

    }

    @Test
    public void orderShouldBeRetrievableByUser() {
        service.addOrder(order);
        verify(orderRepositoryMock).saveOrUpdate(order);

        user.addOrder(order);

        when(orderRepositoryMock.findByUser(user)).thenReturn(user.getOrderList());

        assertThat(service.findOrdersByUser(user), contains(order));
        verify(orderRepositoryMock, times(1)).findByUser(user);
    }

    @Test
    public void orderShouldBeRetrievableByStatus() {
        service.addOrder(order);
        verify(orderRepositoryMock).saveOrUpdate(order);

        order.setStatusShipped();

        List<Order> shippedOrders = new ArrayList<>();
        shippedOrders.add(order);

        when(orderRepositoryMock.findOrdersByStatus(order.getStatus())).thenReturn(shippedOrders);

        assertThat(service.findOrdersByStatus(order.getStatus()), contains(order));
    }

    @Test
    public void ordersShouldBeRetrievableByMinimumPrice(){
        double price = 100;
        service.addOrder(order);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        verify(orderRepositoryMock).saveOrUpdate(order);

        when(orderRepositoryMock.findByMinimumPrice(price)).thenReturn(orders);

        assertThat(service.findOrdersByMinimumPrice(price), contains(order));

        verify(orderRepositoryMock, times(1)).findByMinimumPrice(price);


    }
}
