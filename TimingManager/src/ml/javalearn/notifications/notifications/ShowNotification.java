package ml.javalearn.notifications.notifications;

import java.awt.*;

public class ShowNotification {

    public void showNotification(String title, String fillOfNotification, TrayIcon.MessageType type) throws AWTException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("notification.png");
            TrayIcon trayIcon = new TrayIcon(image, "Time to " + title);
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("test");
            tray.add(trayIcon);
            trayIcon.displayMessage(title, fillOfNotification, type);
        } else {
            System.out.println("System tray is not supported");
        }
    }
}
