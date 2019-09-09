package ml.javalearn.front;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        System.out.println("Start logs: \n");

        CheckEarlierCreations checkEarlierCreations = new CheckEarlierCreations();

        SwingUtilities.invokeLater(() -> {

            try {
                checkEarlierCreations.reader();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (checkEarlierCreations.checkEarlierCreations == 1) {
                new OpenTimingFileWindow();
            } else if (checkEarlierCreations.checkEarlierCreations == 0){
                new StartWindow();
            }

        });
    }

}
