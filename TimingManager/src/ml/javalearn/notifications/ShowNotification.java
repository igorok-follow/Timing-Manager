package ml.javalearn.notifications;

import ml.javalearn.front.MainWindow;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

public class ShowNotification {

    private static void showNotification(String caption, String text, MainWindow mainWindow) {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("notification.png");
            TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
            trayIcon.addActionListener(e -> {
                mainWindow.setVisible(true);
            });
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            trayIcon.displayMessage(caption, text, TrayIcon.MessageType.INFO);
        }
    }

    public static void showInfoNotification(String caption, String text, MainWindow mainWindow) {
        showNotification(caption, text, mainWindow);
    }
}
