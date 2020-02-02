package ml.javalearn.front;

import ml.javalearn.components.Field;
import ml.javalearn.components.Panel;
import ml.javalearn.components.Area;
import ml.javalearn.notifications.notifications.ShowNotification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class MainWindow extends JFrame {

    private JPanel gridPanel, contentPanel;
    private JToolBar toolBar;
    private JToolBar toolBar1;
    private JButton settings, save, clearBtn;
    private String[] time;
    private String saveDataForFields = "";
    private String saveDataForAreas = "";
    private String wayToFieldsFile = "src/ml/javalearn/filesSaver/fields/";
    private String wayToAreasFile  = "src/ml/javalearn/filesSaver/areas/";
    private String timingTime;
    private String timingDate;
    private String timing;

    private int rows, panelsSize, textFieldsSize;
    private String fileName, data;
    private JTextField[] textFields;
    private JPanel[] panels;
    private Area[] areas;
    private JTextField focusableField;
    private JButton acceptBtn = new JButton("Set timing");
    private JButton cancelBtn = new JButton("Cancel");
    private FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            System.out.println("focus gained\n ................");
            getFocusableField();
            Point p = MouseInfo.getPointerInfo().getLocation();
            textFields[0].requestFocus();
            new TimingWindow(p.x, p.y, fileName, getThisClass());
        }
        @Override
        public void focusLost(FocusEvent e) {}
    };


    MainWindow(String fileName, String data, int rows) {
        this.fileName = fileName;
        this.rows = rows;
        this.data = data;
        textFieldsSize = rows * 14;
        panelsSize = rows * 7;

        mainMethod();
    }

    private void mainMethod() {
        initFrame();
        System.out.println("init frame");
        setToolBar();
        System.out.println("set tool bar");
        setContentPanel();
        System.out.println("set content panel");
        setGridPanel();
        System.out.println("set grid panel");
        setSecondaryComponents();
        System.out.println("set scndr components");
        setActions();
        System.out.println("set editables");
    }

    private MainWindow getThisClass() {
        return this;
    }

    void createProject() throws IOException {
        FileWriter writer = new FileWriter("src/ml/javalearn/tables/" + fileName);
        System.out.println(data);
        writer.write(data);
        writer.flush();
        writer.close();
        System.out.println("project was created");
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
        int counter = 0;
        System.out.println("\nSpawning Panels...");
        System.out.println("Number of transferred rows: " + rows);
        System.out.println("Number of transferred cols: 7");
        System.out.println("Number of will spawned cells: " + panels.length);

        for (int i = 0; i < panels.length; i++) {
            Panel panelss = new Panel();
            panels[i] = panelss;
            gridPanel.add(panelss);
            counter++;
            System.out.println(counter + " cycle was passed");
        }

        System.out.println("\nPanels was spawned");
        System.out.println("Completed");
    }

    private void spawnFields() {
        textFields = new JTextField[textFieldsSize];
        areas = new Area[panelsSize];
        int counter = 0;
        System.out.println("Amount of will spawned fields: " +  rows * 14);

        System.out.println("Fill the textFields array: \n");
        for (int i = 0; i <  rows * 14; i++) {
            Field field = new Field();
            textFields[i] = field;
        }

        System.out.println("Fields was loaded");

        for (int i = 0; i < rows * 7; i++) {
            Area area = new Area();
            areas[i] = area;
        }

        System.out.println("Areas was loaded");

        for (int i = 0; i < rows * 7; i++) {
            panels[i].add(textFields[counter]);
            counter++;

            textFields[counter].addFocusListener(focusListener);
            panels[i].add(textFields[counter]);
            counter++;

            panels[i].add(areas[i].addScroll());
        }

        System.out.println("\nTextFields was spawned");
    }


    private void saveProject() throws IOException {
        System.out.println("SAVING PROJECT. START FILL 'SAVEDATA'");

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

        File fieldsFile = new File(wayToFieldsFile + fileName);
        File areasFile = new File(wayToAreasFile + fileName);
        FileWriter fieldFileWriter = new FileWriter(fieldsFile);
        fieldFileWriter.write(saveDataForFields);
        fieldFileWriter.close();
        FileWriter areasFileWriter = new FileWriter(areasFile);
        areasFileWriter.write(saveDataForAreas);
        areasFileWriter.close();
        System.out.println("SAVED DATA:" + "\nFields: " + saveDataForFields + "\nAreas: " + saveDataForAreas);
        if (fieldsFile.exists() && !fieldsFile.isDirectory()
                && areasFile.exists() && !areasFile.isDirectory()) {
            JOptionPane.showMessageDialog(null, "Project saved");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
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

    void splitData(ArrayList fields, ArrayList areas) {
        int amount = rows * 7;

        System.out.println("array list with saved fields: " + fields);
        System.out.println("array list with saved areas: " + areas);

        System.out.println("START SETTING TEXTS");

        for (int i = 0; i < amount * 2; i++) {
            if (fields.size() != 0) {
                String fieldText = fields.get(i).toString();
                textFields[i].setText(fieldText);
            }
        }

        for (int i = 0; i < amount; i++) {
            if (areas.size() != 0) {
                String areaText = areas.get(i).toString();
                this.areas[i].setText(areaText);
            }
        }

        System.out.println("END OF SETTING TEXTS");
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

        acceptBtn.addActionListener(e -> {

        });

        cancelBtn.addActionListener(e -> {

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

    private void castTiming() {
        ArrayList<String> date = new ArrayList<>(Arrays.asList(timing.split("_", 2)));
        timingTime = date.get(1);
        timingDate = date.get(0);
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

    //stupid not working code
    void startWaitNotificationTime() {
//        Thread thread = new Thread(() -> {
//            while (true) {
//                if (getDate().equals(timingDate)) {
//                    break;
//                } else {
//                    try {
//                        Thread.sleep(36_000_000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            while (true) {
//                if (getTime().equals(timingTime)) {
//                    ShowNotification.showInfoNotification(
//                            "Notification of " + fileName, "You requested me to notify you in this moment.");
//                    break;
//                } else {
//                    try {
//                        Thread.sleep(60_000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread.start();
    }

    void refreshTimingField() throws FileNotFoundException {
        System.out.println("Start refresh timing field");
        File file = new File("src/ml/javalearn/notifications/dates/" + fileName);
        Scanner scanner = new Scanner(file);
        timing = "";
        while (scanner.hasNextLine()) {
            timing += scanner.nextLine() + "\n";
        }
        System.out.println(timing);
        focusableField.setText(timing);
    }

    private void getFocusableField() {
        focusableField = (JTextField) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(1000, 600);
        setMinimumSize(new Dimension(800, 600));
        setTitle("Timing Manager [" + fileName + "]");
        setLocation(100, 100);
        setVisible(true);
    }

}