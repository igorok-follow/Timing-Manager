package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;

public class SetNotificationTimePanel extends JPanel {

    private JLabel hours;
    private JLabel minutes;

    SetNotificationTimePanel(int x, int y, int width, int height) {
        initPanel(x, y, width, height);
        addLabels();
    }

    private void addLabels() {
        hours = new JLabel("Hours:");
        hours.setBounds(30, 30, 200, 30);

        minutes = new JLabel("Minutes:");
        minutes.setBounds(30, 70, 200, 30);

        add(hours);
    }

    private void initPanel(int x, int y, int width, int height) {
        setLayout(null);
        setBackground(Color.gray);
        setBounds(x, y, width, height);
    }

}
