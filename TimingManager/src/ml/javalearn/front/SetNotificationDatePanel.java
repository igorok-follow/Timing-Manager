package ml.javalearn.front;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;

public class SetNotificationDatePanel extends JPanel {

    private int x, y, width, height;
    private JCalendar calendar;
    private JLabel titleLabel;

    SetNotificationDatePanel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        iniPanel();
        setCalendar();
        setTitleLabel();
    }

    private void setTitleLabel() {
        titleLabel = new JLabel("Set date of notification");
        titleLabel.setBounds(50, 10, 250, 40);
        add(titleLabel);
    }

    private void setCalendar() {
        calendar = new JCalendar();
        calendar.setBounds(10, 50, 250, 150);
        add(calendar);
    }

    String getCalendar() {
        return calendar.getCalendar().getTime().toString();
    }

    private void iniPanel() {
        setLayout(null);
        setBackground(Color.gray);
        setBounds(x, y, width, height);
    }

}
