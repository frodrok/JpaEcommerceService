package se.fredrikandthenurses.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    @Test
    public void twoOrdersWithSameParametersShouldBeEqual() {

        User user = new User("Fredrik", "alalala");
        Product product = new Product("1001", "Jackie D", 19.99);
        OrderRow row = new OrderRow(product, 10);
        List<OrderRow> orderRowList = new ArrayList<>();
        orderRowList.add(row);
        Order order = new Order("101", user, orderRowList);

        User user2 = new User("Fredrik", "alalala");
        Product product2 = new Product("1001", "Jackie D", 19.99);
        OrderRow row2 = new OrderRow(product, 10);
        List<OrderRow> orderRowList2 = new ArrayList<>();
        orderRowList2.add(row2);
        Order order2 = new Order("101", user2, orderRowList);

        assertEquals(order, order2);
    }



}