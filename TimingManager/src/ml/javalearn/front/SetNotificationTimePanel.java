package ml.javalearn.front;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class SetNotificationTimePanel extends JPanel {

    private JTextField hoursField;
    private JTextField minutesField;
    private Font btnFont = new Font("Arial", Font.PLAIN, 16);
    private Font lblFont = new Font("Arial", Font.PLAIN, 12);
    private JLabel invalidNumber = new JLabel("");
    private JLabel invalidNumber1 = new JLabel("");
    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                int num  = Integer.parseInt(hoursField.getText());
                invalidNumber.setText("format: from 0 to 23 hours");
            } catch (Exception ex) {
                invalidNumber.setText("Invalid number");
            }
        }
    };
    private KeyAdapter keyAdapter1 = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            try {
                int num = Integer.parseInt(minutesField.getText());
                invalidNumber1.setText("format: from 0 to 59 minutes");
            } catch (Exception ex) {
                invalidNumber1.setText("Invalid number");
            }
        }
    };

    SetNotificationTimePanel(int x, int y, int width, int height) {
        initPanel(x, y, width, height);
        addLabels();
        addFormattedTextFields();
    }

    private void addFormattedTextFields() {
        hoursField = new JTextField();
        hoursField.setBounds(70, 30, 160, 30);
        hoursField.addKeyListener(keyAdapter);
        minutesField = new JTextField();
        minutesField.setBounds(70, 90, 160, 30);
        minutesField.addKeyListener(keyAdapter1);

        add(hoursField);
        add(minutesField);
    }

    private void addLabels() {
        JLabel hours = new JLabel("Hours:");
        hours.setBounds(5, 30, 100, 30);
        hours.setFont(btnFont);

        JLabel minutes = new JLabel("Minutes:");
        minutes.setBounds(5, 90, 100, 30);
        minutes.setFont(btnFont);

        invalidNumber.setFont(lblFont);
        invalidNumber.setForeground(Color.RED);
        invalidNumber.setBounds(70, 60, 100, 30);

        invalidNumber1.setFont(lblFont);
        invalidNumber1.setForeground(Color.RED);
        invalidNumber1.setBounds(70, 120, 100, 30);

        add(hours);
        add(minutes);
        add(invalidNumber);
        add(invalidNumber1);
    }

    private void initPanel(int x, int y, int width, int height) {
        setLayout(null);
        setBackground(Color.gray);
        setBounds(x, y, width, height);
    }

    String getHours() {
        return hoursField.getText();
    }

    String getMinutes() {
        return minutesField.getText();
    }

}