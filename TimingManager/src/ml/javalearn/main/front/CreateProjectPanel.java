package ml.javalearn.main.front;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class CreateProjectPanel extends JPanel {

    private JButton acceptSettings, cancel;
    private JTextField rows, fileNameField;
    private String fileName;
    private JButton showPopup;
    private JTextArea info;
    private boolean checker = false;

    CreateProjectPanel() {
        initPanel();
        setButtons();
        setLabels();
        setTextFields();
        setActions();
        popupArea();
    }

    private void popupArea() {
        ImageIcon rightIterator = new ImageIcon("test0.png");
        ImageIcon downIterator = new ImageIcon("test1.png");

        showPopup = new JButton();
        showPopup.setIcon(rightIterator);
        showPopup.setBounds(30, 140, 20, 20);

        info = new JTextArea();
        info.setBounds(30, 170, 430, 200);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        info.setText("test test test test test test test test test test test test test test test test test test");

        showPopup.addActionListener(e -> {
            if (checker) {
                remove(info);
                showPopup.setIcon(rightIterator);
                checker = false;
                repaint();
            } else {
                add(info);
                showPopup.setIcon(downIterator);
                checker = true;
                repaint();
            }
        });

        add(showPopup);
    }

    private void setButtons() {
        acceptSettings = new JButton("Ok");
        acceptSettings.setBounds(381, 110, 80, 25);
        cancel = new JButton("Cancel");
        cancel.setBounds(296, 110, 80, 25);

        add(acceptSettings);
        add(cancel);
    }

    private void setLabels() {
        Font font = new Font("Italic", Font.PLAIN, 16);

        JLabel label = new JLabel("Tune Settings");
        label.setFont(font);
        label.setBounds(10, 10, 300, 20);

        JLabel rowsLabel = new JLabel("Amount a rows:");
        rowsLabel.setFont(font);
        rowsLabel.setBounds(30, 40, 200, 20);

        JLabel fileNameLabel = new JLabel("Timing-project name:");
        fileNameLabel.setFont(font);
        fileNameLabel.setBounds(30, 80, 200, 20);

        JLabel moreInfo = new JLabel("More info...");
        moreInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        moreInfo.setBounds(60, 140, 80, 20);

        add(label);
        add(rowsLabel);
        add(fileNameLabel);
        add(moreInfo);
    }

    private void setTextFields() {
        rows = new JTextField(50);
        rows.setBounds(190, 40, 270, 20);


        fileNameField = new JTextField(50);
        fileNameField.setBounds(190, 80, 270, 20);

        add(rows);
        add(fileNameField);
    }

    private void setActions() {
        acceptSettings.addActionListener(e -> {

            int rowsInt = Integer.parseInt(rows.getText());

            MainWindow mainWindow = new MainWindow();
            mainWindow.rows = rowsInt;
            System.out.println("Number of entered rows: " + mainWindow.rows);
            System.out.println("Number of entered cols: 7");
            int amount = rowsInt * 7;
            mainWindow.textFieldsSize = amount * 3;
            mainWindow.panelsSize     = amount;
            setVisible(false);
            System.out.println("Start window was closed");

            fileName = fileNameField.getText();

            mainWindow.data = rows.getText() + "/7/" + fileName + "/0";
            mainWindow.fileName = fileName;

            try {
                mainWindow.createProject();
                mainWindow.mainMethod();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Main method was completed");
            mainWindow.setVisible(true);

        });

        cancel.addActionListener(e -> System.exit(0));

    }

    private void initPanel() {
        setLayout(null);
    }

}