package users;

import java.util.List;

public interface UsersDAO {
    public boolean createUser(User users);

    public boolean deleteUser(User users);

    public User getUserByLogin(String login);

    public boolean logIn(String login, String password);

    public boolean registration(String login, String password);

}
