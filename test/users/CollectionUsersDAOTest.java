package users;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CollectionUsersDAOTest {
    CollectionUsersDAO collectionUsersDAO = CollectionUsersDAO.instanceOf();

    @Test
    public void testRegistrationAndLogin() {
        //given
        String login = Integer.toString(ThreadLocalRandom.current().nextInt(100, 200));
        String password = Integer.toString(ThreadLocalRandom.current().nextInt(100, 200));
        //when
        collectionUsersDAO.registration(login, password);
        //than
        Assert.assertTrue(collectionUsersDAO.logIn(login, password));
    }
}