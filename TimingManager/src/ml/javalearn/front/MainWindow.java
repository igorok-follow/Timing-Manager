package ml.javalearn.front;

import ml.javalearn.components.Field;
import ml.javalearn.components.Panel;
import ml.javalearn.components.Area;
import ml.javalearn.notifications.ShowNotification;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private JPanel gridPanel, contentPanel;
    private JToolBar toolBar;
    private JToolBar toolBar1;
    private JButton settings, save, clearBtn;
    private String saveDataForFields = "";
    private String saveDataForAreas = "";

    private int rows, panelsSize, textFieldsSize;
    private String fileName, data;
    private JTextField[] textFields;
    private JPanel[] panels;
    private Area[] areas;
    private JTextField focusableField;
    private ProjectManager projectManager;
    private FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            getFocusableField();
            Point p = MouseInfo.getPointerInfo().getLocation();
            textFields[0].requestFocus();
            new TimingWindow(p.x, p.y, getThisClass());
        }
        @Override
        public void focusLost(FocusEvent e) {}
    };


    MainWindow(String fileName, String data, int rows, ProjectManager projectManager) {
        this.projectManager = projectManager;
        this.fileName = fileName;
        this.rows = rows;
        this.data = data;
        textFieldsSize = rows * 14;
        panelsSize = rows * 7;

        mainMethod();
    }

    private void mainMethod() {
        initFrame();
        setToolBar();
        setContentPanel();
        setGridPanel();
        setSecondaryComponents();
        setActions();
    }

    private MainWindow getThisClass() {
        return this;
    }

    void createProject() throws IOException {
        FileWriter writer = new FileWriter("src/ml/javalearn/tables/" + fileName);
        writer.write(data);
        writer.flush();
        writer.close();
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
    }

    private void setGridPanel() {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, 7, 0, 0));
        gridPanel.setBackground(new Color(189, 189, 189));
        spawnPanels();
        spawnFields();

        JPanel test = new JPanel();
        test.add(toolBar1);
        contentPanel.add(test, BorderLayout.WEST);

        contentPanel.add(gridPanel, BorderLayout.CENTER);

        JPanel test1 = new JPanel();
        test1.add(toolBar);
        contentPanel.add(test1, BorderLayout.EAST);

        getContentPane().add(contentPanel);
    }

    private void setToolBar() {
        toolBar = new JToolBar();
        toolBar.setOrientation(SwingConstants.VERTICAL);
        toolBar1 = new JToolBar();
        toolBar1.setOrientation(SwingConstants.VERTICAL);
    }

    private void spawnPanels() {
        panels = new JPanel[panelsSize];

        for (int i = 0; i < panels.length; i++) {
            Panel panelss = new Panel();
            panels[i] = panelss;
            gridPanel.add(panelss);
        }
    }

    private void spawnFields() {
        textFields = new JTextField[textFieldsSize];
        areas = new Area[panelsSize];
        int counter = 0;
        for (int i = 0; i <  rows * 14; i++) {
            Field field = new Field();
            textFields[i] = field;
        }
        for (int i = 0; i < rows * 7; i++) {
            Area area = new Area();
            areas[i] = area;
        }
        for (int i = 0; i < rows * 7; i++) {
            panels[i].add(textFields[counter]);
            counter++;

            textFields[counter].addFocusListener(focusListener);
            panels[i].add(textFields[counter]);
            counter++;

            panels[i].add(areas[i].addScroll());
        }
    }


    private void saveProject() throws IOException {
        for (int i = 0; i < textFields.length; i++) {
            if (i == textFields.length - 1) {
                saveDataForFields += textFields[i].getText();
            } else {
                saveDataForFields += textFields[i].getText() + "/";
            }
        }

        for (int i = 0; i < areas.length; i++) {
            if (i == areas.length - 1) {
                saveDataForAreas += areas[i].getText();
            } else {
                saveDataForAreas += areas[i].getText() + "/";
            }
        }

        String wayToFieldsFile = "src/ml/javalearn/filesSaver/fields/";
        File fieldsFile = new File(wayToFieldsFile + fileName);
        String wayToAreasFile = "src/ml/javalearn/filesSaver/areas/";
        File areasFile = new File(wayToAreasFile + fileName);
        FileWriter fieldFileWriter = new FileWriter(fieldsFile);
        fieldFileWriter.write(saveDataForFields);
        fieldFileWriter.close();
        FileWriter areasFileWriter = new FileWriter(areasFile);
        areasFileWriter.write(saveDataForAreas);
        areasFileWriter.close();
        if (fieldsFile.exists() && !fieldsFile.isDirectory()
                && areasFile.exists() && !areasFile.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Project saved");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }

    void fillFilesOnStart() throws IOException {
        File fieldsFile = new File("src/ml/javalearn/filesSaver/fields/" + fileName);
        File areasFile = new File("src/ml/javalearn/filesSaver/areas/" + fileName);
        FileWriter writer = new FileWriter(fieldsFile);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < rows * 14; i++) {
            if (i != rows * 14 -1) {
                str.append("/");
            }
        }
        writer.write(str.toString());
        writer.close();

        writer = new FileWriter(areasFile);
        str = new StringBuilder();
        for (int i = 0; i < rows * 7; i++) {
            if (i != rows * 7 - 1) {
                str.append("/");
            }
        }
        writer.write(str.toString());
        writer.close();

        writer = new FileWriter("src/ml/javalearn/tables/" + fileName);
        writer.write(rows + "/7/" + fileName + "/1");
        writer.close();
    }

    private void setSecondaryComponents() {
        settings = new JButton(new ImageIcon("set.png"));
        save = new JButton("Save");
        clearBtn = new JButton("Clear");

        toolBar.add(settings);
        toolBar1.add(save);

        toolBar1.add(clearBtn);
    }

    void splitData(ArrayList<String> fields, ArrayList<String> areas) {
        int amount = rows * 7;

        for (int i = 0; i < amount * 2; i++) {
            if (fields.size() != 0) {
                String fieldText = fields.get(i);
                textFields[i].setText(fieldText);
            }
        }
        for (int i = 0; i < amount; i++) {
            if (areas.size() != 0) {
                String areaText = areas.get(i);
                this.areas[i].setText(areaText);
            }
        }
    }

    private void setActions()  {
        settings.addActionListener(e -> new SettingsWindow());

        save.addActionListener(e -> {
            try {
                saveProject();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        clearBtn.addActionListener(e -> {
            int change = JOptionPane.showConfirmDialog(
                this, "You are sure? This option will completely clear your timing list.\n",
                "Select an option", JOptionPane.YES_NO_OPTION);
            if (change == 0) {
                for (JTextField field : textFields) {
                    field.setText(null);
                }
                for (JTextArea area : areas) {
                    area.setText(null);
                }

                try {
                    saveProject();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private String getTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");

        return myDateObj.format(myFormatObj);
    }

    private String getDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return myDateObj.format(myFormatObj);
    }

    void startWaitNotificationTime(String timing) {
        String[] notificationTime = timing.split("_", 2);
        String date = notificationTime[0];
        String time = notificationTime[1];
        Thread thread = new Thread(() -> {
            while (true) {
                if (date.equals(getDate())) {
                    break;
                } else {
                    try {
                        Thread.sleep(35_000_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            while (true) {
                if (time.equals(getTime())) {
                    ShowNotification.showInfoNotification(
                            "Notification", "You asked me to notify you.", getThisClass());
                    break;
                } else {
                    try {
                        Thread.sleep(59000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    void refreshTimingField(String date) {
        focusableField.setText(date);
    }

    private void getFocusableField() {
        focusableField = (JTextField) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    }

    private int confirmClose() {
        return JOptionPane.showConfirmDialog(this, "You want close window?");
    }

    private void initFrame() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (confirmClose() == 0) {
                    setVisible(false);
                    projectManager.setVisible(true);
                } 
            }
        });
        setSize(1150, 600);
        setMinimumSize(new Dimension(800, 600));
        setTitle("Timing Manager [" + fileName + "]");
        setLocation(100, 100);
        setVisible(true);
    }

}