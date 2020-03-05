package service.fileSystem;

import java.io.*;

public class FileSystem {
    public Object getHashMapFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (file.isFile()) {
            InputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return objectInputStream.readObject();
        }
        return Object.class;
    }

    public void recordHashMapToFile(String fileName, Object data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();

        byte[] bytes = byteArrayOutputStream.toByteArray();
        OutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(bytes);
        outputStream.close();
    }
}
