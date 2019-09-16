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

    int cols, rows, panelsSize, textFieldsSize;
    String fileName, data;

    private JPanel[] panels;

    MainWindow() {

    }

    void mainMethod() throws IOException {
        setSecondPlusController();
        System.out.println("checker worked");
        initFrame();
        System.out.println("init frame");
        setToolBar();
        System.out.println("set tool bar");
        setContentPanel();
        System.out.println("set content panel");
        setGridPanel();
        System.out.println("set grid panel");
        createProject();
        System.out.println("project was created");
    }

    private void createProject() throws IOException {
        FileWriter writer = new FileWriter("src/ml/javalearn/tables/" + fileName + ".txt");
        writer.write(data);
        writer.flush();
        writer.close();
    }

    private void setSecondPlusController() throws IOException {
        FileWriter fileWriter = new FileWriter("checker.txt");
        fileWriter.write("1");
        fileWriter.flush();
        fileWriter.close();

        new CheckEarlierCreations().reader();
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
        toolBar1 = new JToolBar();
        toolBar1.add(new JButton("test"));
        JButton button = new JButton(new ImageIcon("set.png"));
        toolBar.add(button);
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
            panels[i].setBackground(Color.CYAN);
            gridPanel.add(panelss);
            System.out.println(counter + "cycle was passed");
        }

        gridPanel.add(panels[1]);

        System.out.println("Completed");
    }

    private void spawnFields() {
        JTextField[] textFields = new JTextField[textFieldsSize];

        int counter = 0;

        System.out.println("Amount of will spawned fields: " + rows * cols * 3);
        System.out.println("Spawning Fields on Panels...");

        for (int i = 0; i < rows * cols * 3; i++) {
            Fields fields = new Fields();
            textFields[i] = fields;
        }

        for (int i = 0; i < rows * cols; i++) {
            panels[i].add(textFields[counter]);
            counter++;
            panels[i].add(textFields[counter]);
            counter++;
            panels[i].add(textFields[counter]);
            counter++;
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 1024);
        setMinimumSize(new Dimension(800, 600));
        setTitle("Timing Manager [" + fileName + "]");
    }

}
