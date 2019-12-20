package ml.javalearn.main.test;

import javax.swing.*;
import java.awt.*;

public class Tray {
    public static void main(String[] args) throws AWTException {
        ImageIcon imageIcon = new ImageIcon("C:/Users/IGOROK/Desktop/JavaLearn/3.png");
        Image image = imageIcon.getImage();

        TrayIcon trayIcon = new TrayIcon(image, "test");
        SystemTray systemTray = SystemTray.getSystemTray();
        systemTray.add(trayIcon);

        trayIcon.displayMessage("test", "WORKING!", TrayIcon.MessageType.INFO);
    }
}
