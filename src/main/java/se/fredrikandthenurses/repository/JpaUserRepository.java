package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.User;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;

/**
 * Created by TheYellowBelliedMarmot on 2015-12-22.
 */
public class JpaUserRepository extends AbstractJpaRepository <User> implements UserRepository {

    public JpaUserRepository(EntityManagerFactory emf ){
        super(emf, User.class);
    }


    @Override
    public User findByUsername(String username) {
        return query("User.FindByUsername").setParameter("username", username).getSingleResult();
    }
}
