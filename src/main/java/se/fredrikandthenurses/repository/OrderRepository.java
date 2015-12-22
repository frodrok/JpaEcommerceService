package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.PersistableOrder;
import se.fredrikandthenurses.model.User;

import java.util.Collection;

/**
 * Created by joanne on 21/12/15.
 */
public interface OrderRepository extends CrudRepository<PersistableOrder> {

    PersistableOrder findByOrderNumber(String number);

    Collection<PersistableOrder> findByUser(User user);

    Collection<PersistableOrder> findOrdersByStatus(String status);

    Collection<PersistableOrder> findByMinimumPrice(double price);
}
