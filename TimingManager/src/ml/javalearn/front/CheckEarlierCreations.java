package ml.javalearn.front;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class CheckEarlierCreations {

    int checkEarlierCreations;

    void reader() throws IOException {
        FileReader fileReader = new FileReader("checker.txt");
        Scanner scanner = new Scanner(fileReader);
        checkEarlierCreations = scanner.nextInt();
        System.out.println("content of checker.txt: " + checkEarlierCreations);
    }
}
