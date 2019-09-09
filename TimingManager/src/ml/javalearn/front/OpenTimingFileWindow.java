package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;

public class OpenTimingFileWindow extends JFrame {

    JList list = new JList();

    OpenTimingFileWindow() {
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Open Timing List");
        setSize(new Dimension(500, 500));
        setVisible(true);
    }

}
