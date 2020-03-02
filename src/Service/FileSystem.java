package Service;

import logger.Logger;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class FileSystem<T> {
    public List<T> getListFromFile(String fileName) {
        File file = new File(fileName);

        if (file.isFile()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Logger.info("recoverData");
                return (List<T>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                Logger.error("Ошибка восстановления");
            }
        }
        Logger.error("Ошибка восстановления");
        return Collections.emptyList();

    }

    public boolean recordListToFile(String fileName, List<T> dataList) {
        if (dataList.size() > 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                objectOutputStream.writeObject(dataList);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            byte[] bytes = byteArrayOutputStream.toByteArray();
            try (OutputStream outputStream = new FileOutputStream(fileName)) {
                Logger.info("loadData");
                outputStream.write(bytes);
            } catch (IOException e) {
                Logger.error("Ошибка сохранения");
                return false;
            }

            return true;
        } else {
            return false;
        }
    }
}
