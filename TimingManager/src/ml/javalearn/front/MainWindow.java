package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel gridPanel;
    private JPanel contentPanel;

    int cols;
    int rows;
    int amountCells = cols * rows;

    MainWindow() {
        initFrame();
        setContentPanel();
        setGridPanel();
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);
    }

    private void setGridPanel() {
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(7, 6, 0, 0));
    }

    private void spawnLabels() {
        for (int i = 0; i < amountCells; i++) {
//            new Labels();
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 1024);
        setMinimumSize(new Dimension(800, 600));
        setTitle("Timing Manager");
    }

}
