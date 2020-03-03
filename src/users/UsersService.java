package users;

import booking.BookingOverflowException;
import logger.Logger;

import java.io.IOException;

public class UsersService {
    private final CollectionUsersDAO COLLECTION_USERS_DAO;

    public UsersService(CollectionUsersDAO COLLECTION_USERS_DAO) {
        this.COLLECTION_USERS_DAO = COLLECTION_USERS_DAO;
    }

    public boolean saveDataToFile() {
        try {
            return COLLECTION_USERS_DAO.saveDataToFile();
        } catch (UsersOverflowException e) {
            Logger.error(e.toString() + " UsersOverflowException");
            return false;
        }
    }

    public void loadData() {
        try {
            COLLECTION_USERS_DAO.loadData();
        } catch (UsersOverflowException ex) {
            Logger.error(ex.toString() + " UsersOverflowException");
        }
    }

    public boolean createUser(User user) {
        return COLLECTION_USERS_DAO.createUser(user);
    }

    public boolean deleteUser(User user) {
        return COLLECTION_USERS_DAO.deleteUser(user);
    }

    public User getUserByLogin(String login) {
        return COLLECTION_USERS_DAO.getUserByLogin(login);
    }

    public boolean logIn(String login, String password) {
        return COLLECTION_USERS_DAO.logIn(login, password);
    }

    public boolean registration(String login, String password) {
        return COLLECTION_USERS_DAO.registration(login, password);
    }
}
