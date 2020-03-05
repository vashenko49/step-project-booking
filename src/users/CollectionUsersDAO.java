package users;

import service.fileSystem.FileSystemToHashMap;;
import service.fileSystem.WorkWithFileSystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

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
        FileSystemToHashMap<String, User> userFileSystemToHashMap = new FileSystemToHashMap<>();
        try {
            HashMap<String, User> gog = userFileSystemToHashMap.getHashMapFromFile(fileName);
            users.putAll(gog);
        } catch (IOException | ClassNotFoundException e) {
            throw new UsersOverflowException("Ошибка загрузки");
        }
    }

    @Override
    public User getUserByLogin(String login) {
        return users.get(login);
    }

    @Override
    public boolean logIn(String login, String password) {
        User user = users.get(login);
        if (Objects.nonNull(user)) {
            return user.equalPassword(password);
        }
        return false;
    }

    @Override
    public boolean registration(String login, String password) {
        if (users.containsKey(login)) {
            return false;
        } else {
            users.put(login, new User(login, password));
            try {
                saveDataToFile();
            } catch (UsersOverflowException e) {
                return false;
            }
            return users.containsKey(login);
        }
    }
}
