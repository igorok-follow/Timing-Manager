package ml.javalearn.front;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class TimingWindow extends MainWindow {

    public static void main(String[] args) throws ClassNotFoundException,
            UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> new TimingWindow(100, 100));
    }

    private JButton setTimingBtn, cancelBtn;
    private JCalendar calendar;
    private JLabel forTest, title;
    private JPanel contentPanel;
    private int x, y, monthCounter;
    private boolean flag = true, closed;
    private String[] dateParts;
    private JFrame frame = new JFrame("Set time of notification");

    private final Font FONT_FOR_BUTTONS = new Font("Arial", Font.BOLD, 14);

    TimingWindow(int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        frame.getContentPane().add(contentPanel);
    }

    private void addComponents() {
        calendar = new JCalendar();
        calendar.setBounds(10, 50, 250, 150);

        title = new JLabel("Set time of notification");
        title.setBounds(50, 10, 250, 40);
        title.setFont(FONT_FOR_BUTTONS);

        setTimingBtn = new JButton("Next");
        setTimingBtn.setBounds(13, 202, 122, 20);
        setTimingBtn.setFont(FONT_FOR_BUTTONS);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(135, 202, 122, 20);
        cancelBtn.setFont(FONT_FOR_BUTTONS);

        forTest = new JLabel("test test test");
        forTest.setBounds(300, 150, 150, 50);
        forTest.setFont(FONT_FOR_BUTTONS);

        contentPanel.add(title);
        contentPanel.add(calendar);
        contentPanel.add(setTimingBtn);
        contentPanel.add(forTest);
        contentPanel.add(cancelBtn);
    }

    private void replaceAnimation1() {
        flag = true;
        Thread out = new Thread(() -> {
            while (flag) {
                for (int i = -10; i < 252; i+=2 ) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    calendar.setBounds(-i, 50, 250, 150);
                    title.setBounds(-i, 10, 250, 40);
                    setTimingBtn.setText("Set timing");
                    if (calendar.getBounds().x == -250) flag = false;
                }
            }
            splitAndSelectCalendarData(calendar.getCalendar().getTime().toString());
        });
        out.start();
    }

    private void replaceAnimation2() {
        flag = true;
        Thread in = new Thread(() -> {
            while (flag) {
                for (int i = 300; i > 50; i-=2) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    forTest.setBounds(i, 150, 150, 50);
                    if (forTest.getBounds().x == 52) flag = false;
                }
            }
        });
        in.start();
    }

    private void changeListenerOnAcceptButton() {
        setTimingBtn.addActionListener(e -> {
            System.out.println("Work second listener");
            writeData();
            try {
                refreshTimingField();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void writeData() {
        try {
            System.out.println("write started");
            String fileName = "src/ml/javalearn/notifications/dates/" + getFileName();
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(dateParts[2] + "." + monthCounter + "." + dateParts[5]);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void splitAndSelectCalendarData(String date) {
        System.out.println(date);
        dateParts = date.split(" ", 6);
        System.out.println(Arrays.asList(dateParts));
        switch (dateParts[1]) {
            case "Jan":
                monthCounter = 1;
                break;
            case "Feb":
                monthCounter = 2;
                break;
            case "Mar":
                monthCounter = 3;
                break;
            case "Apr":
                monthCounter = 4;
                break;
            case "May":
                monthCounter = 5;
                break;
            case "Jun":
                monthCounter = 6;
                break;
            case "Jul":
                monthCounter = 7;
                break;
            case "Aug":
                monthCounter = 8;
                break;
            case "Sep":
                monthCounter = 9;
                break;
            case "Oct":
                monthCounter = 10;
                break;
            case "Nov":
                monthCounter = 11;
                break;
            case "Dec":
                monthCounter = 12;
                break;
            default:
                System.out.println("none");
        }
    }

    private void setListeners() {
        setTimingBtn.addActionListener(e -> {
            replaceAnimation1();
            replaceAnimation2();
            for (ActionListener l:setTimingBtn.getActionListeners()) {
                setTimingBtn.removeActionListener(l);
                System.out.println("removed");
            }
            changeListenerOnAcceptButton();
        });
        cancelBtn.addActionListener(e -> frame.dispose());
    }

    private void createFrame() {
        frame.setBounds(0, 30, 269, 235);
        frame.setLocation(x, y);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    private void init() {
        createFrame();
        setContentPanel();
        addComponents();
        setListeners();
    }

}
