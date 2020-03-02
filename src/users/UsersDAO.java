package users;

import java.util.List;

public interface UsersDAO {
    public boolean createUser(User users);

    public boolean deleteUser(User users);

    public List<User> getAllUsers();

    public User getUserById(String id);

    public boolean validUserData(String login, String password);

}
