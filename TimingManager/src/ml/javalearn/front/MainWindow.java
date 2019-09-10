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

    int cols;
    int rows;
    String fileName;
    private int amountCells;
    private int counter = 0;

    MainWindow() {

    }

    void mainMethod() throws IOException {
        setSecondPlusController();
        initFrame();
        setToolBar();
        setContentPanel();
        setGridPanel();
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
        spawnLabels();
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

    private void spawnLabels() {
        System.out.println("Number of transferred rows: " + rows);
        System.out.println("Number of transferred cols: " + cols);

        amountCells = rows * cols;
        System.out.println("Amount of cells: " + amountCells);

        for (int i = 0; i < amountCells; i++) {
            counter++;
            gridPanel.add(new Labels("", "",
                    new Font("Arial", Font.PLAIN, 12)
            ));
            System.out.println(counter + " cycle was passed...");
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 1024);
        setMinimumSize(new Dimension(800, 600));
        setTitle("Timing Manager [" + fileName + "]");
    }

}
