package ml.javalearn.front;

import javax.swing.*;

public class Fields extends JTextField {

    int rows, cols;
    private final int amountConst = 3;

    JTextField textField = new JTextField(rows * cols * amountConst);

    Fields() {

    }

}
