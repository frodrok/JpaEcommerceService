package se.fredrikandthenurses.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by TheYellowBelliedMarmot on 2015-12-21.
 */

@RunWith(MockitoJUnitRunner.class)
public class ECommerceUserTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private OrderRepository orderRepositoryMock;

    @InjectMocks
    private ECommerceService service;

    private User user;

    @Before
    public void setup(){
        user = new User("fredrik", "123");
    }

    @Test
    public void userShouldBeAddedOrUpdated(){
        service.addUser(user);

        verify(userRepositoryMock, times(1)).saveOrUpdate(user);
    }

}
