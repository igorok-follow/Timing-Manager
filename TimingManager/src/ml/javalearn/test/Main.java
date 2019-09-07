package ml.javalearn.test;

import ml.javalearn.front.SettingsWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException,
            UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(SettingsWindow::new);
    }

}
