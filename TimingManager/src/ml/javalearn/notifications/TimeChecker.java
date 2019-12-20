package ml.javalearn.notifications;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeChecker {
    public void checkTimeMatching(String dateForShow, String title, String fillOfNotification, TrayIcon.MessageType type) {
        SimpleDateFormat pattern = new SimpleDateFormat("MM/dd HH:mm:ss");

        Thread thread = new Thread(() -> {
            Date date = new Date();
            String currentDate;
            while (true) {
                currentDate = pattern.format(date);
                if (currentDate.equals(dateForShow)) {
                    try {
                        new ShowNotification().showNotification(title, fillOfNotification, type);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }
}
