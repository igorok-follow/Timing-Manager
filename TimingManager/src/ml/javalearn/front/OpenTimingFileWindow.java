package ml.javalearn.front;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OpenTimingFileWindow extends JFrame {

    JList list = new JList();
    ArrayList<String> arrayList = new ArrayList<>();


    OpenTimingFileWindow() {

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

}
