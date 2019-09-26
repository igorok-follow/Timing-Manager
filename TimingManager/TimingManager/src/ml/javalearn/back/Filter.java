package ml.javalearn.back;

import java.io.File;

public class Filter {

    public File[] finder(String dirName){
        File dir = new File(dirName);

        return dir.listFiles((dir1, filename) -> filename.endsWith(".txt"));
    }

}