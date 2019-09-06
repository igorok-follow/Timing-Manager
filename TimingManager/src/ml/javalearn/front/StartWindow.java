package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {

    private JPanel contentPanel;
    private JButton acceptSettings, cancel;
    private JTextField rows, cols;
    private JLabel rowsLabel, colsLabel;
    private Font fontForLabels = new Font("Arial", 16, Font.PLAIN);

    StartWindow() {
        initFrame();
        setContentPanel();
        setButtons();
        setLabels();
        setTextFields();
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
        contentPanel.add(new Labels(95, 50, "Tune Settings", "Test"));

        rowsLabel = new JLabel("Amount a rows:");
//        rowsLabel.setFont(fontForLabels);

        colsLabel = new JLabel("Amount a cols:");
//        colsLabel.setFont(fontForLabels);
    }

    private void setTextFields() {
        rows = new JTextField(50);
        rows.setBounds(70, 110, 150, 20);
        rowsLabel.setBounds(90, 120, 150, 20);
        contentPanel.add(rowsLabel);

        contentPanel.add(colsLabel);

        cols = new JTextField(50);
        cols.setBounds(70, 80, 150, 20);

        contentPanel.add(rows);
        contentPanel.add(cols);
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 300));
        setResizable(true);
        setTitle("Create Timing List");
        setVisible(true);
    }

}