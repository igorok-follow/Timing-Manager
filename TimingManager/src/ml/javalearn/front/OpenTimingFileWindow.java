package ml.javalearn.front;

import ml.javalearn.back.Filter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class OpenTimingFileWindow extends JFrame {

    ArrayList<File> arrayList = new ArrayList<>();
    JPanel panel = new JPanel();

    OpenTimingFileWindow() {
        initFrame();
        setList();
    }

    private void setList() {
        arrayList.addAll(Arrays.asList(new Filter().finder(System.getProperty("user.dir"))));

        JList list = new JList<>(arrayList.toArray());
        list.setBounds(10, 10, 100, 150);
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(null);
        panel.add(list);
        getContentPane().add(panel);
    }

    void mainMethodOpenTimingFile() {
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Open Timing List");
        setSize(new Dimension(500, 500));
        setVisible(true);
    }

    public static void main(String[] args) {
        new OpenTimingFileWindow();
    }

}