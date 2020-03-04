package users;

import logger.Logger;

public class UsersService {
    private final CollectionUsersDAO COLLECTION_USERS_DAO;

    public UsersService(CollectionUsersDAO COLLECTION_USERS_DAO) {
        this.COLLECTION_USERS_DAO = COLLECTION_USERS_DAO;
    }

    public boolean saveDataToFile() {
        try {
            Logger.info("Запись users в файл");
            return COLLECTION_USERS_DAO.saveDataToFile();
        } catch (UsersOverflowException e) {
            Logger.error(e.toString() + " UsersOverflowException");
            return false;
        }
    }

    public void loadData() {
        try {
            COLLECTION_USERS_DAO.loadData();
            Logger.info("Восстановление users из файла");
        } catch (UsersOverflowException ex) {
            Logger.error(ex.toString() + " UsersOverflowException");
        }
    }


    public User getUserByLogin(String login) {
        Logger.info("Получение пользователя по логину");
        return COLLECTION_USERS_DAO.getUserByLogin(login);
    }

    public boolean logIn(String login, String password) {
        Logger.info("Вход пользователя в систему");
        return COLLECTION_USERS_DAO.logIn(login, password);
    }

    public boolean registration(String login, String password) {
        Logger.info("Регестрация пользователя в систему");
        return COLLECTION_USERS_DAO.registration(login, password);
    }
}
