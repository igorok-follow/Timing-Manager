package ml.javalearn.front;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class TimingWindow {

    public static void main(String[] args) throws ClassNotFoundException,
            UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            new TimingWindow(100, 100, "testName");
        });
    }

    private JButton setTimingBtn, cancelBtn;
    private JPanel contentPanel;
    private int x, y, monthCounter;
    private String[] dateParts;
    private JFrame frame = new JFrame("Set time of notification");
    private String fileName;
    private MainWindow mainWindow;
    private SetNotificationTimePanel setNotificationTimePanel;
    private SetNotificationDatePanel setNotificationDatePanel;

    private final Font FONT_FOR_BUTTONS = new Font("Arial", Font.BOLD, 14);


    TimingWindow(int x, int y, String fileName) {
        this.x = x;
        this.y = y;
        this.fileName = fileName;
//        this.mainWindow = mainWindow;
        init();
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        frame.getContentPane().add(contentPanel);
    }

    private void addComponents() {
        setTimingBtn = new JButton("Next");
        setTimingBtn.setBounds(13, 202, 122, 20);
        setTimingBtn.setFont(FONT_FOR_BUTTONS);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(135, 202, 122, 20);
        cancelBtn.setFont(FONT_FOR_BUTTONS);

        setNotificationTimePanel = new SetNotificationTimePanel(278, 0, 241, 170);
        setNotificationDatePanel = new SetNotificationDatePanel(0, 0, 241, 200);

        contentPanel.add(setTimingBtn);
        contentPanel.add(cancelBtn);
        contentPanel.add(setNotificationTimePanel);
        contentPanel.add(setNotificationDatePanel);
    }

    private void replaceAnimation1() {
        Thread out = new Thread(() -> {
            for (int i = -10; i < 252; i+=2 ) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setNotificationDatePanel.setBounds(-i, 0, 241, 200);
            }
        });
        out.start();
    }

    private void replaceAnimation2() {
        Thread in = new Thread(() -> {
            for (int i = 300; i > 12; i-=2) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setNotificationTimePanel.setBounds(i, 1, 241, 200);
            }
        });
        in.start();
    }

    private void changeListenerOnAcceptButton() {
        setTimingBtn.addActionListener(e -> {
            writeData();
            frame.dispose();
            try {
                mainWindow.refreshTimingField();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void writeData() {
        try {
            System.out.println("write started");
            String fileWay = "src/ml/javalearn/notifications/dates/" + fileName;
            FileWriter fileWriter = new FileWriter(fileWay);
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
        System.out.println(monthCounter);
    }

    private void setListeners() {
        setTimingBtn.addActionListener(e -> {
            replaceAnimation1();
            splitAndSelectCalendarData(setNotificationDatePanel.getCalendar());
            replaceAnimation2();
            setTimingBtn.setText("Set time");
            for (ActionListener l : setTimingBtn.getActionListeners()) {
                setTimingBtn.removeActionListener(l);
            }
            changeListenerOnAcceptButton();
        });
        cancelBtn.addActionListener(e -> frame.dispose());
    }

    private void createFrame() {
        frame.setBounds(x, y, 269, 235);
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
