package ml.javalearn.front;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;

class TimingWindow {

    private JButton setTimingBtn, cancelBtn;
    private JPanel contentPanel;
    private int x, y, monthCounter;
    private String[] dateParts;
    private JFrame frame = new JFrame("Set time of notification");
    private MainWindow mainWindow;
    private SetNotificationTimePanel setNotificationTimePanel;
    private SetNotificationDatePanel setNotificationDatePanel;
    private String savedData;

    TimingWindow(int x, int y, MainWindow mainWindow) {
        this.x = x;
        this.y = y;
        this.mainWindow = mainWindow;
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

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(135, 202, 122, 20);

        setNotificationTimePanel = new SetNotificationTimePanel(278, 0, 241, 170);
        setNotificationDatePanel = new SetNotificationDatePanel(0, 0, 271, 200);

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
            mainWindow.refreshTimingField(writeData());
            mainWindow.startWaitNotificationTime(savedData);
        });
    }

    private String writeData() {
        String mc;
        if (String.valueOf(monthCounter).length() < 2) {
            mc = "0" + monthCounter;
        } else {
            mc = String.valueOf(monthCounter);
        }

        String minutes;
        if (setNotificationTimePanel.getMinutes().length() < 2) {
            minutes = "0" + setNotificationTimePanel.getMinutes();
        } else {
            minutes = setNotificationTimePanel.getMinutes();
        }

        String hours;
        if (setNotificationTimePanel.getHours().length() < 2) {
            hours = "0" + setNotificationTimePanel.getHours();
        } else {
            hours = setNotificationTimePanel.getHours();
        }

        return savedData = dateParts[2] + "." + mc + "." + dateParts[5] + "_" + hours + ":" + minutes;
    }

    private void splitAndSelectCalendarData(String date) {
        dateParts = date.split(" ", 6);
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
        }
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
