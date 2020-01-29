package ml.javalearn.components;

import javax.swing.*;
import java.awt.*;

public class Area extends JTextArea {

    JScrollPane scrollPane;

    public Area() {
        scrollPane = new JScrollPane(this);
        setFont(new Font("Arial", Font.PLAIN, 16));
    }

    public JScrollPane addScroll() {
        return scrollPane;
    }

}
