package users;

import Service.fileSystem.WorkWithFileSystem;

import java.util.List;

public class CollectionUsersDAO implements UsersDAO, WorkWithFileSystem {
    public static final CollectionUsersDAO COLLECTION_USERS_DAO = new CollectionUsersDAO();

    private CollectionUsersDAO() {
    }

    public static CollectionUsersDAO instanceOf() {
        return COLLECTION_USERS_DAO;
    }

    @Override
    public boolean saveDataToFile() {
        return false;
    }

    @Override
    public boolean loadData() {
        return false;
    }

    @Override
    public boolean createUser(User users) {
        return false;
    }

    @Override
    public boolean deleteUser(User users) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public boolean validUserData(String login, String password) {
        return false;
    }
}
