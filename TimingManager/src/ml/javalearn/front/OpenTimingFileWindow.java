package ml.javalearn.front;

import ml.javalearn.back.Filter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class OpenTimingFileWindow extends JFrame {

    private ArrayList<File> arrayList = new ArrayList<>();
    private JPanel panel;
    JTextArea descriptionArea;

    OpenTimingFileWindow() {
        initFrame();
        setPanel();
        setList();
        setDescriptionArea();
        setSecondaryComponents();
    }

    private void setPanel() {
        panel = new JPanel();
        panel.setLayout(null);
    }

    private void setList() {
        final String way = "src/ml/javalearn/tables/";
        arrayList.addAll(Arrays.asList(new Filter().finder(way)));

        JList list = new JList<>(arrayList.toArray());
        list.setBounds(11, 75, 233, 280);
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(list);
        getContentPane().add(panel);
    }

    private void setDescriptionArea() {
        descriptionArea = new JTextArea();
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionArea.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        descriptionArea.setBounds(243, 75, 235, 280);
        panel.add(descriptionArea);
    }

    private void setSecondaryComponents() {
        JLabel label = new JLabel("Your projects:");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(175, 10, 300, 40);
        panel.add(label);

        JButton accept = new JButton("Open Project");
        JButton cancel = new JButton("Cancel");
        accept.setBounds(10, 365, 235, 30);
        cancel.setBounds(240, 365, 235, 30);
        cancel.addActionListener(e -> {
            System.exit(0);
        });

        panel.add(accept);
        panel.add(cancel);
    }

    void mainMethodOpenTimingFile() {
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Open Timing List");
        setSize(new Dimension(500, 443));
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OpenTimingFileWindow();
    }

}