package se.fredrikandthenurses.repository;

import org.junit.Before;
import org.junit.Test;
import se.fredrikandthenurses.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hamcrest.CoreMatchers.equalTo;
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
        userRepo.saveOrUpdate(user);
    }

    @Test
    public void userShouldBeRetrievableByUsername(){
      assertThat(userRepo.findByUsername(user.getUsername()), equalTo(user));
    }

    @Test
    public void userShouldBeRetrieVableById(){
        assertThat(userRepo.find(user.getId()), equalTo(user));
    }

    @Test
    public void updatedUserShouldBeUpdated(){
        assertTrue(userRepo.find(user.getId()).isActive());
        user.setActive(false);
        userRepo.saveOrUpdate(user);
        assertFalse(userRepo.find(user.getId()).isActive());
    }

    @Test
    public void deletedUserShouldBeDeleted(){
        assertTrue(userRepo.getAll().contains(user));
        userRepo.remove(user);
        assertFalse(userRepo.getAll().contains(user));
    }




}