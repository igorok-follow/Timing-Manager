package ml.javalearn.main.front;

import javax.swing.*;
import java.awt.*;

public class SetOrChangeTimingComponentPanel extends JPanel {

    private JButton hoursPlus, hoursMinus, minutesPlus, minutesMinus;
    private JTextField hours, minutes, date;
    private Font fontForButtons = new Font("Arial", Font.BOLD, 14);

    SetOrChangeTimingComponentPanel() {
        initPanel();
        addComponents();
    }

    private void addComponents() {
        hoursPlus = new JButton("˄");
        hoursPlus.setFont(fontForButtons);
        hoursPlus.setBounds(20, 40, 50,  20);

        minutesMinus = new JButton("˅");
        minutesMinus.setFont(fontForButtons);
        minutesMinus.setBounds(190, 85, 50, 20);

        hoursMinus = new JButton("˅");
        hoursMinus.setFont(fontForButtons);
        hoursMinus.setBounds(20, 85, 50, 20);

        minutesPlus = new JButton("˄");
        minutesPlus.setFont(fontForButtons);
        minutesPlus.setBounds(190, 40, 50,  20);

        hours = new JTextField();
        hours.setBounds(20, 60, 50, 25);

        date = new JTextField();
        date.setBounds(70, 60, 120, 25);
        date.setHorizontalAlignment(SwingConstants.CENTER);

        minutes = new JTextField();
        minutes.setBounds(190, 60, 50, 25);

        add(hoursPlus);
        add(hoursMinus);
        add(hours);
        add(date);
        add(minutes);
        add(minutesMinus);
        add(minutesPlus);
    }

    private void initPanel() {
        setLayout(null);
        setBounds(0, 30, 300, 150);
        setBackground(Color.BLACK);
    }

}
