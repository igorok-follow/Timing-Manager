package ml.javalearn.front;

import javax.swing.*;
import java.util.ArrayList;

public class SettingsWindow extends JFrame {

    private JPanel contentPanel;
    private JList <String> timingList;
    private DefaultListModel<String> listModel;
    private ArrayList<String> arrayList = new ArrayList<>();
    private JButton createTiming, removeTiming, changeTiming;

    public SettingsWindow() {
        initFrame();
        setContentPanel();
        fillArrayList();
        setList();
        setButtons();
    }

    private void setButtons() {
        createTiming = new JButton("+");
        createTiming.setBounds(55, 230, 50, 20);

        removeTiming = new JButton("-");
        removeTiming.setBounds(155, 230, 50, 20);

        changeTiming = new JButton(new ImageIcon("set.png"));
        changeTiming.setBounds(105, 230, 50, 20);

        contentPanel.add(changeTiming);
        contentPanel.add(removeTiming);
        contentPanel.add(createTiming);
    }

    private void fillArrayList() {
        for (int i = 0; i < 50; i++) {
            arrayList.add(String.valueOf(i));
        }
        System.out.println("fill of arrayList: " + arrayList);
    }

    private void setList() {
        listModel = new DefaultListModel<>();

        for (String s : arrayList) {
            listModel.addElement(s);
        }
        
        timingList = new JList<>(listModel);
        timingList.setBounds(55, 20, 150, 200);

        contentPanel.add(timingList);
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Settings");
        setVisible(true);
    }

}
