package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.Order;
import se.fredrikandthenurses.model.User;

import java.util.List;

/**
 * Created by joanne on 21/12/15.
 */
public interface OrderRepository extends CrudRepository<Order> {

    Order findByOrderNumber(String number);

    List<Order> getByUser(User user);



}
