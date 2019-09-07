package ml.javalearn.test;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    test() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("test");

        JPanel panel  = new JPanel();
        JPanel panel1 = new JPanel();
        panel1.setSize(new Dimension(20, 20));
        panel1.setBackground(Color.BLACK);
        panel1.add(new JButton("WORK BLEAT'"));

        getContentPane().add(panel1);
        panel.add(panel1);

        setVisible(true);
    }

    public static void main(String[] args) {
        new test();
    }

}
