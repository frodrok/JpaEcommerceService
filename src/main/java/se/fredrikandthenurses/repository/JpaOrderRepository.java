package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.PersistableOrder;
import se.fredrikandthenurses.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.Order;
import java.util.Collection;

/**
 * Created by joanne on 22/12/15.
 */
public class JpaOrderRepository extends AbstractJpaRepository<PersistableOrder> implements OrderRepository {

    public JpaOrderRepository(EntityManagerFactory emf) {
        super(emf, PersistableOrder.class);
    }

    @Override
    public PersistableOrder findByOrderNumber(String number) {
        return query("PersistableOrder.FindByOrderNumber", number);
    }

    @Override
    public Collection<PersistableOrder> findByUser(User user) {
        return queryForList("PersistableOrder.FindByUser", user.getId());
    }

    @Override
    public Collection<PersistableOrder> findOrdersByStatus(String status) {
        return null;
    }

    @Override
    public Collection<PersistableOrder> findByMinimumPrice(double price) {
        return null;
    }


}
