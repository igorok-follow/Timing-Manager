package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SetNotificationTimePanel extends JPanel {

    private JFormattedTextField formattedHours;
    private JFormattedTextField formattedMinutes;
    private Font font = new Font("Arial", Font.PLAIN, 16);
    private JLabel invalidNumber = new JLabel("");
    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            try {
                int numbers = Integer.parseInt(formattedHours.getText());
                invalidNumber.setText("");
            } catch (Exception ex) {
                invalidNumber.setText("Invalid number");
            }
        }
    };

    SetNotificationTimePanel(int x, int y, int width, int height) {
        initPanel(x, y, width, height);
        addLabels();
        addFormattedTextFields();
    }

    private void addFormattedTextFields() {
        formattedHours   = new JFormattedTextField();
        formattedHours.setBounds(70, 30, 160, 30);
        formattedHours.addKeyListener(keyAdapter);
        formattedMinutes = new JFormattedTextField();
        formattedMinutes.setBounds(70, 90, 160, 30);

        add(formattedHours);
        add(formattedMinutes);
    }

    private void addLabels() {
        JLabel hours = new JLabel("Hours:");
        hours.setBounds(5, 30, 100, 30);
        hours.setFont(font);

        JLabel minutes = new JLabel("Minutes:");
        minutes.setBounds(5, 90, 100, 30);
        minutes.setFont(font);

        invalidNumber.setFont(font);
        invalidNumber.setForeground(Color.RED);
        invalidNumber.setBounds(10, 10, 100, 30);

        add(hours);
        add(minutes);
        add(invalidNumber);
    }

    private void initPanel(int x, int y, int width, int height) {
        setLayout(null);
        setBackground(Color.gray);
        setBounds(x, y, width, height);
    }

}
