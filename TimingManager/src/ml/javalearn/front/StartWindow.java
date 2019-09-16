package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartWindow extends JFrame {

    private JPanel contentPanel;
    private JButton acceptSettings, cancel, saveProject;
    private JTextField rows, cols, fileNameField;
    private Font fontForLabels = new Font("Arial", Font.BOLD, 16);
    private String fileName, data;

    StartWindow() throws IOException {
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
        contentPanel.add(new Labels(90, 30, "Tune Settings", fontForLabels));

        JLabel rowsLabel = new JLabel("Amount a rows:");
        rowsLabel.setFont(new Font("Italic", Font.PLAIN, 16));
        rowsLabel.setBounds(90, 65, 150, 20);

        JLabel colsLabel = new JLabel("Amount a cols:");
        colsLabel.setFont(new Font("Italic", Font.PLAIN, 16));
        colsLabel.setBounds(90, 45, 150, 150);

        JLabel fileNameLabel = new JLabel("    File name:");
        fileNameLabel.setFont(new Font("Italic", Font.PLAIN, 16));
        fileNameLabel.setBounds(90, 85, 150, 150);

        contentPanel.add(rowsLabel);
        contentPanel.add(colsLabel);
        contentPanel.add(fileNameLabel);
    }

    private void setTextFields() {
        rows = new JTextField(50);
        rows.setBounds(70, 85, 150, 20);

        cols = new JTextField(50);
        cols.setBounds(70, 130, 150, 20);

        fileNameField = new JTextField(50);
        fileNameField.setBounds(70, 175, 150, 20);

        contentPanel.add(rows);
        contentPanel.add(cols);
        contentPanel.add(fileNameField);
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);
    }

    private void setActions() {
        acceptSettings.addActionListener(e -> {

            int rowsInt = Integer.parseInt(rows.getText());
            int colsInt = Integer.parseInt(cols.getText());

            MainWindow mainWindow = new MainWindow();
            mainWindow.rows = rowsInt;
            System.out.println("Number of entered rows: " + mainWindow.rows);
            mainWindow.cols = colsInt;
            System.out.println("Number of entered cols: " + mainWindow.cols);
            int amount = rowsInt * colsInt;
            mainWindow.textFieldsSize = amount * 3;
            mainWindow.panelsSize     = amount;
            setVisible(false);
            System.out.println("Start window was closed");

            fileName = fileNameField.getText();

            mainWindow.data = rows.getText() + "/" + cols.getText() + "/" + fileName;
            mainWindow.fileName = fileName;

            try {
                mainWindow.mainMethod();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Main method was completed");
            mainWindow.setVisible(true);

        });

        cancel.addActionListener(e -> System.exit(0));
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 300));
        setResizable(true);
        setTitle("Create Timing List");
        setVisible(true);
    }

}