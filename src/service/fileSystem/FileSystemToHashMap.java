package service.fileSystem;

import java.io.*;
import java.util.HashMap;


public class FileSystemToHashMap<K, D> {
    FileSystem fileSystem = new FileSystem();

    public HashMap<K, D> getHashMapFromFile(String fileName) throws IOException, ClassNotFoundException {
        Object oldData = fileSystem.getHashMapFromFile(fileName);
        if (oldData instanceof HashMap) {
            return (HashMap<K, D>) fileSystem.getHashMapFromFile(fileName);
        } else {
            return new HashMap<>();
        }
    }

    public boolean recordHashMapToFile(String fileName, HashMap<K, D> dataHashMap) throws IOException {
        fileSystem.recordHashMapToFile(fileName, dataHashMap);
        return true;
    }
}
