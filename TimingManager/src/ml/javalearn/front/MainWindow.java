package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

class MainWindow extends JFrame {

    private JPanel gridPanel;
    private JPanel contentPanel;
    private JToolBar toolBar;
    private JToolBar toolBar1;
    private JButton setEditables, settings, save;
    private String saveData = "";
    int cols, rows, panelsSize, textFieldsSize;
    String fileName, data;
    private JTextField[] textFields;

    private JPanel[] panels;

    MainWindow() {

    }

    void mainMethod() {
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
    }

    void createProject() throws IOException {
        FileWriter writer = new FileWriter("src/ml/javalearn/tables/" + fileName + ".txt");
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
        gridPanel.setLayout(new GridLayout(rows, cols, 0, 0));
        gridPanel.setBackground(new Color(189, 189, 189));
        spawnPanels();
        spawnFields();
        System.out.println("\n");

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
        System.out.println("Number of transferred cols: " + cols);
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

        int counter = 0;

        System.out.println("Amount of will spawned fields: " + rows * cols * 3);

        System.out.println("Fill the textFields array: \n");
        for (int i = 0; i < rows * cols * 3; i++) {
            Fields fields = new Fields();
            textFields[i] = fields;
            System.out.println((i+1) + " cycle was completed");
        }

        for (int i = 0; i < rows * cols; i++) {
            panels[i].add(textFields[counter]);
            counter++;
            panels[i].add(textFields[counter]);
            counter++;
            panels[i].add(textFields[counter]);
            counter++;
        }

        System.out.println("\nTextFields was spawned");

    }

    private void saveProject() throws IOException {
        for (JTextField textField : textFields) {
            saveData += textField.getText() + "/";
        }

        FileWriter fileWriter = new FileWriter("src/ml/javalearn/filesSaver/" + fileName);
        fileWriter.write(saveData);
        fileWriter.close();
        System.out.println(saveData);
    }

    private void setSecondaryComponents() {
        settings     = new JButton(new ImageIcon("set.png"));
        setEditables = new JButton("Edit");
        save = new JButton("Save");
        toolBar.add(settings);
        toolBar1.add(setEditables);
        toolBar1.add(save);
    }

    private void setActions() {
        settings.addActionListener(e -> {

        });

        setEditables.addActionListener(e -> {

        });

        save.addActionListener(e -> {
            try {
                saveProject();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(1280, 1024);
        setMinimumSize(new Dimension(800, 600));
        setTitle("Timing Manager [" + fileName + "]");
        setVisible(true);
    }

}
