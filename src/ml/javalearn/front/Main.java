package ml.javalearn.front;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        System.out.println("Start logs: \n");
        SwingUtilities.invokeLater(() -> {
            ProjectManager projectManager = new ProjectManager();
            projectManager.mainMethod();
        });
    }

}