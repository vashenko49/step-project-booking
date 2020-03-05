package users;


import org.junit.Test;

public class UserTest {
    @Test
    public void testCreatedUser() {
        //given
        String login = "dfg";
        String password = "sfgr";
        //when
        User user = new User(login, password);
        //than
    }
}