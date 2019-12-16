package ml.javalearn.components;

import javax.swing.*;
import java.awt.*;

public class Panels extends JPanel {

    public Panels() {
        setLayout(new GridLayout(3, 1, 0, 0));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}