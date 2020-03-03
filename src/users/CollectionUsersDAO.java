package users;

import service.fileSystem.FileSystemToHashMap;;
import service.fileSystem.WorkWithFileSystem;

import java.io.IOException;
import java.util.HashMap;

public final class CollectionUsersDAO implements UsersDAO, WorkWithFileSystem {
    private static final CollectionUsersDAO COLLECTION_USERS_DAO = new CollectionUsersDAO();
    private final HashMap<String, User> users = new HashMap<>();
    private final String fileName = "users";

    private CollectionUsersDAO() {
    }

    public static CollectionUsersDAO instanceOf() {
        return COLLECTION_USERS_DAO;
    }

    @Override
    public boolean saveDataToFile() throws UsersOverflowException {
        FileSystemToHashMap<String, User> bookingFileSystem = new FileSystemToHashMap<>();
        try {
            return bookingFileSystem.recordHashMapToFile(fileName, users);
        } catch (IOException e) {
            throw new UsersOverflowException("Ошибка сохранения");
        }
    }

    @Override
    public void loadData() throws UsersOverflowException {
        FileSystemToHashMap<String, User> bookingFileSystem = new FileSystemToHashMap<>();
        try {
            users.putAll(bookingFileSystem.getHashMapFromFile(fileName));
        } catch (IOException | ClassNotFoundException e) {
            throw new UsersOverflowException("Ошибка загрузки");
        }
    }

    @Override
    public boolean createUser(User user) {
        users.put(user.getLogin(), user);
        return users.containsKey(user.getLogin());
    }

    @Override
    public boolean deleteUser(User user) {
        return users.remove(user.getLogin(), user);
    }

    @Override
    public User getUserByLogin(String login) {
        return users.get(login);
    }

    @Override
    public boolean logIn(String login, String password) {
        return users.get(login).equalPassword(password);
    }

    @Override
    public boolean registration(String login, String password) {
        users.put(login, new User(login, password));
        return users.containsKey(login);
    }
}
