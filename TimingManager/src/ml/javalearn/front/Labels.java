package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;

public class Labels extends JLabel {
        private Integer x, y;
        private String name;

        public Labels(Integer x, Integer y, String name, String timeStartToTimeEnd) {
            this.setText(name);
            this.setBounds(x, y, name.length() * 100, 15);
            this.setFont(new Font("Arial", Font.PLAIN, 15));
        }
}
