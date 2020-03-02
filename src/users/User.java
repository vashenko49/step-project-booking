package users;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private int userId;

    public User(String login, String password, int userId) {
        this.login = login;
        this.password = password;
        this.userId = userId;
    }
}
