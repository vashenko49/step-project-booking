package Service.fileSystem;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class FileSystemToList<T> {
    FileSystem fileSystem = new FileSystem();

    public List<T> getListFromFile(String fileName) throws IOException, ClassNotFoundException {
        Object oldData = fileSystem.getHashMapFromFile(fileName);
        if (oldData instanceof List) {
            return (List<T>) fileSystem.getHashMapFromFile(fileName);
        } else {
            return Collections.emptyList();
        }
    }

    public boolean recordListToFile(String fileName, List<T> dataList) throws IOException {
        if (dataList.size() > 0) {
            fileSystem.recordHashMapToFile(fileName, dataList);
            return true;
        } else {
            return false;
        }
    }
}
