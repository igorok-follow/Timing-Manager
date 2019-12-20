package ml.javalearn.main.test;

import javax.swing.*;

public class TransparentTest extends JFrame {

    public static void main(String[] args) {
        new TransparentTest();
    }

    TransparentTest() {
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("test");
        setUndecorated(true);
        setOpacity(0.5f);
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
    }

}
