package ml.javalearn.components;

import javax.swing.*;
import java.awt.*;

public class Fields extends JTextField {

    public Fields() {
        setFont(new Font("Arial", Font.PLAIN, 16));
        setHorizontalAlignment(SwingConstants.CENTER);
        setEditable(false);
    }

}