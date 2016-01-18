package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.User;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by TheYellowBelliedMarmot on 2015-12-22.
 */
public class JpaUserRepository extends AbstractJpaRepository <User> implements UserRepository {

    public JpaUserRepository(EntityManagerFactory emf ){
        super(emf, User.class);
    }


    @Override
    public User findByUsername(String username) {
        return query("User.FindByUsername", username);
    }

    @Override
    public List<User> getAll() {
        return queryForList("User.GetAll");
    }

    @Override
    public List<User> getAllWithOrders(){return queryForList("User.GetAllWithOrders");}
}
