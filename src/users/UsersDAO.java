package users;


public interface UsersDAO {
    public User getUserByLogin(String login);

    public boolean logIn(String login, String password);

    public boolean registration(String login, String password);

}
