package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;

class MainWindow extends JFrame {

    private JPanel gridPanel;
    private JPanel contentPanel;

    int cols;
    int rows;
    private int amountCells;
    private int counter = 0;

    MainWindow() {

    }

    void mainMethod() {
        initFrame();
        setContentPanel();
        setGridPanel();
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
    }

    private void setGridPanel() {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols, 0, 0));
        gridPanel.setBackground(new Color(189, 189, 189));
        gridPanel.setPreferredSize(new Dimension(this.getWidth() - 100, this.getHeight() - 100));
        spawnLabels();
        contentPanel.add(gridPanel);
        getContentPane().add(contentPanel);
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
        setTitle("Timing Manager");
    }

}
