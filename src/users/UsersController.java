package users;

import logger.Logger;

public class UsersController {
    private final UsersService USERS_SERVICE;

    public UsersController(UsersService USERS_SERVICE) {
        this.USERS_SERVICE = USERS_SERVICE;
    }
    public boolean saveDataToFile() {
        return USERS_SERVICE.saveDataToFile();
    }

    public void loadData() {
        USERS_SERVICE.loadData();
    }

    public boolean createUser(User user) {
        return USERS_SERVICE.createUser(user);
    }

    public boolean deleteUser(User user) {
        return USERS_SERVICE.deleteUser(user);
    }

    public User getUserByLogin(String login) {
        return USERS_SERVICE.getUserByLogin(login);
    }

    public boolean logIn(String login, String password) {
        return USERS_SERVICE.logIn(login, password);
    }

    public boolean registration(String login, String password) {
        return USERS_SERVICE.registration(login, password);
    }
}
