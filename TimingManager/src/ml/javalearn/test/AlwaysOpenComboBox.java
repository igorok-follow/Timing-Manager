package ml.javalearn.test;

import javax.swing.*;

public class AlwaysOpenComboBox extends JFrame {

    //разобраться в том, как сделать фиксированное выпадающее окно, или сделать самому

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlwaysOpenComboBox::new);
    }

    AlwaysOpenComboBox() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setComponents();
    }

    private void setComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JComboBox<Integer> comboBox = new JComboBox<>();
        comboBox.setBounds(0, 0, 200, 100);
        for (int i = 0; i < 10; i++) {
            comboBox.addItem(i);
        }
        comboBox.setSelectedItem(0);
        comboBox.showPopup();
        panel.add(comboBox);
        getContentPane().add(panel);
    }

}
