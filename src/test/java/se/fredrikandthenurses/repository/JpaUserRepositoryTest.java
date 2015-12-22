package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Test;
import se.fredrikandthenurses.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

/**
 * Created by joanne on 22/12/15.
 */
public class JpaUserRepositoryTest {


    private UserRepository userRepo;
    private User user;
    private EntityManagerFactory factory;
    @Before
    public void setup(){
        factory= Persistence.createEntityManagerFactory("lokaldatabas");
        userRepo = new JpaUserRepository(factory);
        user = new User("joanne", "123");
    }

    @Test
    public void userShouldBeRetrievableByUsername(){
        userRepo.saveOrUpdate(user);
        System.out.println(userRepo.findByUsername("joanne"));
    }



}