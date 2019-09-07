package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;

public class Labels extends JLabel {
        private Integer x, y;
        private String name;

        public Labels(Integer x, Integer y, String name, Font font) {
            this.setText(name);
            this.setBounds(x, y, name.length() * 100, 15);
            this.setFont(font);
        }

        public Labels(String name, String timeStartToTimeEnd, Font font) {
            this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            this.setText(name + "\n" + timeStartToTimeEnd);
            this.setFont(font);
        }
}
