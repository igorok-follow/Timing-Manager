package ml.javalearn.front;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.GridLayout;

class SettingsWindow extends JFrame {

    private JPanel contentPanel;
    private JLabel setFontLabel, setFontSizeLabel, setProgramLanguageLabel, changeThemeLabel;
    private JComboBox fontName, fontSize, textFieldColors;
    private JRadioButton changeThemeRadio;
    private JButton applyChangesBtn, declineChangesBtn;

    private int[] fontSizes = new int[10];

    SettingsWindow() {
        initFrame();
        setContentPanel();
        setButtons();
        setComboBoxes();
        setLabels();
        setSecondaryComponents();
        addComponents();
        setActions();
    }

    private void setSecondaryComponents() {
        changeThemeRadio = new JRadioButton();
    }

    private void setLabels() {
        setFontLabel = new JLabel("   Change font: ");
        setFontLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFontLabel.setForeground(Color.BLACK);
        setFontSizeLabel = new JLabel("   Set font size: ");
        setFontSizeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFontSizeLabel.setForeground(Color.BLACK);
        setProgramLanguageLabel = new JLabel("   Set language: ");
        setProgramLanguageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setProgramLanguageLabel.setForeground(Color.BLACK);
        changeThemeLabel = new JLabel("   Black theme: ");
        changeThemeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        changeThemeLabel.setForeground(Color.BLACK);
    }

    private void setComboBoxes() {
        fontName =        new JComboBox();
        fontSize =        new JComboBox();
        textFieldColors = new JComboBox();
    }

    private void setButtons() {
        applyChangesBtn = new JButton("Apply");
        declineChangesBtn = new JButton("Cancel");
    }

    private void setActions() {
        changeThemeRadio.addActionListener(e -> {
            changeTheme();
        });
    }

    private void setContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2, 0, 0));
        getContentPane().add(contentPanel);
    }

    private void addComponents() {
        contentPanel.add(setFontLabel);
        contentPanel.add(fontName);
        contentPanel.add(setFontSizeLabel);
        contentPanel.add(fontSize);
        contentPanel.add(setProgramLanguageLabel);
        contentPanel.add(textFieldColors);
        contentPanel.add(changeThemeLabel);
        contentPanel.add(changeThemeRadio);
        contentPanel.add(applyChangesBtn);
        contentPanel.add(declineChangesBtn);
    }

    private void changeTheme() {
        setFontLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        setFontLabel.setForeground(Color.WHITE);
        setFontSizeLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        setFontSizeLabel.setForeground(Color.WHITE);
        setProgramLanguageLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        setProgramLanguageLabel.setForeground(Color.WHITE);
        changeThemeLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        changeThemeLabel.setForeground(Color.WHITE);
        fontName.setBackground(new Color(0x414141));
        fontSize.setBackground(new Color(0x414141));
        textFieldColors.setBackground(new Color(0x414141));
        changeThemeRadio.setBackground(new Color(0x414141));
        contentPanel.setBackground(new Color(0x414141));
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(400, 400);
        setTitle("Settings");
        setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException,
                                            InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(SettingsWindow::new);
    }
}