package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StartWindow extends JFrame {

    private JPanel contentPanel;
    private JButton acceptSettings, cancel;
    private JTextField rows, cols;
    private JLabel rowsLabel, colsLabel;
    private Font fontForLabels = new Font("Arial", Font.BOLD, 16);

    StartWindow() {
        initFrame();
        setContentPanel();
        setButtons();
        setLabels();
        setTextFields();
        setActions();
    }

    private void setButtons() {
        acceptSettings = new JButton("Ok");
        acceptSettings.setBounds(115, 230, 80, 25);
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 230, 80, 25);

        contentPanel.add(acceptSettings);
        contentPanel.add(cancel);
    }

    private void setLabels() {
        contentPanel.add(new Labels(90, 50, "Tune Settings", fontForLabels));

        rowsLabel = new JLabel("Amount a rows:");
        rowsLabel.setFont(new Font("Italic", Font.PLAIN, 16));
        rowsLabel.setBounds(90, 85, 150, 20);

        colsLabel = new JLabel("Amount a cols:");
        colsLabel.setFont(new Font("Italic", Font.PLAIN, 16));
        colsLabel.setBounds(90, 65, 150, 150);


        contentPanel.add(rowsLabel);
        contentPanel.add(colsLabel);
    }

    private void setTextFields() {
        rows = new JTextField(50);
        rows.setBounds(70, 105, 150, 20);

        cols = new JTextField(50);
        cols.setBounds(70, 150, 150, 20);

        contentPanel.add(rows);
        contentPanel.add(cols);
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);
    }

    private void setActions() {
        acceptSettings.addActionListener(e -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.rows = Integer.parseInt(rows.getText());
            System.out.println("Number of entered rows: " + mainWindow.rows);
            mainWindow.cols = Integer.parseInt(cols.getText());
            System.out.println("Number of entered cols: " + mainWindow.cols);
            setVisible(false);
            System.out.println("Start window was closed");
            try {
                mainWindow.mainMethod();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Main method was completed");
            mainWindow.setVisible(true);


        });

        cancel.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 300));
        setResizable(true);
        setTitle("Create Timing List");
        setVisible(true);
    }

}