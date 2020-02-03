package ml.javalearn.test;

import java.io.File;

public class FileDeleter {

    public static void main(String[] args) {
        File file = new File("src/ml/javalearn/fileSaver/areas/2.txt");
        try {
            if (file.delete()) {
                System.out.println("deleted");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
