package ml.javalearn.front;

import ml.javalearn.components.Fields;
import ml.javalearn.components.Panels;
import ml.javalearn.components.TextAreas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class MainWindow extends JFrame {

    //primary components
    private JPanel gridPanel, contentPanel;
    private JToolBar toolBar;
    private JToolBar toolBar1;
    private JButton setEditables, settings, save, clear;
    private String saveDataForFields = "";
    private String saveDataForAreas = "";
    private boolean checkEditable = false;
    int rows, panelsSize, textFieldsSize;
    String fileName, data;
    private JTextField[] textFields;
    private JPanel[] panels;
    private TextAreas[] areas;

    //secondary components
    private JFrame frame = new JFrame("target window");
    private JButton acceptBtn = new JButton("Set timing");
    private JButton cancelBtn = new JButton("Cancel");

    MainWindow() {
        System.out.println("DATA: " + data);
    }

    void mainMethod() throws IOException {
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
        fillFilesOnStart();
    }

    void setFileName(String fileName) { this.fileName = fileName; }

    void setRows(int rows) { this.rows = rows; }

    void setTextFieldsSize(int textFieldsSize) { this.textFieldsSize = textFieldsSize; }

    void setPanelsSize(int panelsSize) { this.panelsSize = panelsSize; }

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
            Panels panelss = new Panels();
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
        areas = new TextAreas[panelsSize];
        int counter = 0, amount = rows * 14;
        System.out.println("Amount of will spawned fields: " + amount);

        System.out.println("Fill the textFields array: \n");
        for (int i = 0; i < amount; i++) {
            Fields fields = new Fields();
            textFields[i] = fields;
        }

        System.out.println("Fields was loaded");

        for (int i = 0; i < rows * 7; i++) {
            TextAreas textAreas = new TextAreas();
            areas[i] = textAreas;
        }

        System.out.println("Areas was loaded");

        for (int i = 0; i < rows * 7; i++) {
            panels[i].add(textFields[counter]);
            counter++;

            textFields[counter].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    System.out.println("focus gained\n ................");
                    Point point = MouseInfo.getPointerInfo().getLocation();
                    int mouseX = point.x;
                    int mouseY = point.y;
                    openTargetWindow(mouseX, mouseY);
                }

                @Override
                public void focusLost(FocusEvent e) {}
            });
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

        File fieldsFile = new File("src/ml/javalearn/filesSaver/fields/" + fileName);
        File areasFile = new File("src/ml/javalearn/filesSaver/areas/" + fileName);
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

    private void fillFilesOnStart() throws IOException {
        File fieldsFile = new File("src/ml/javalearn/filesSaver/fields/" + fileName);
        File areasFile = new File("src/ml/javalearn/filesSaver/areas/" + fileName);
        FileWriter fieldFileWriter = new FileWriter(fieldsFile);
        String str = "";
        for (int i = 0; i < rows * 14 + 1; i++) {
            str += "/";
        }
        fieldFileWriter.write(str);
        fieldFileWriter.close();

        FileWriter areasFileWriter = new FileWriter(areasFile);
        str = "";
        for (int i = 0; i < rows * 7 + 1; i++) {
            str += "/";
        }
        areasFileWriter.write(str);
        areasFileWriter.close();
    }

    private void setSecondaryComponents() {
        settings     = new JButton(new ImageIcon("set.png"));
        setEditables = new JButton("Edit");
        save = new JButton("Save");
        toolBar.add(settings);
        toolBar1.add(setEditables);
        toolBar1.add(save);
    }

    void splitData(ArrayList fields, ArrayList areas) {
        int amount = rows * 7;

        System.out.println("array list with saved fields: " + fields);
        System.out.println("array list with saved areas: " + areas);

        System.out.println("START SETTING TEXTS");

        for (int i = 0; i < amount * 2; i++) {
            String fieldText = fields.get(i).toString();
            textFields[i].setText(fieldText);
        }

        for (int i = 0; i < amount; i++) {
            String areaText = areas.get(i).toString();
            this.areas[i].setText(areaText);
        }

        System.out.println("END OF SETTING TEXTS");
    }

    private void editables() {
        if (checkEditable) {
            checkEditable = false;
            for (JTextField textField : textFields) {
                textField.setEditable(false);
            }
        } else {
            checkEditable = true;
            for (JTextField textField : textFields) {
                textField.setEditable(true);
            }
        }
    }

    private void setActions() {
        settings.addActionListener(e -> new SettingsWindow());

        setEditables.addActionListener(e -> editables());

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
    }

    private void openTargetWindow(int x, int y) {
        frame.setSize(300, 300);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocation(x, y);
        frame.setVisible(true);
        addComponentsOnTargetWindow();
        System.out.println("frame opened\n ..............");
    }

    private void addComponentsOnTargetWindow() {
        JPanel targetContentPanel = new JPanel();
        targetContentPanel.setLayout(null);
        frame.getContentPane().add(targetContentPanel);

        JLabel label = new JLabel("Set timing");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(100, 0, 200, 20);

        acceptBtn.setBounds(75, 230, 100, 25);
        cancelBtn.setBounds(177, 230, 100, 25);
        targetContentPanel.add(acceptBtn);
        targetContentPanel.add(cancelBtn);
        targetContentPanel.add(label);
        targetContentPanel.add(new SetOrChangeTimingComponent());
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(800, 600);
        setTitle("Timing Manager [" + fileName + "]");
        setLocation(100, 100);
        setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(MainWindow::new);
    }
}