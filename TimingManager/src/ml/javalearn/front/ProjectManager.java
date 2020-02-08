package ml.javalearn.front;

import ml.javalearn.back.Filter;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class ProjectManager extends JFrame {

    private JPanel panel, infoPanel, devInfoPanel;
    private DefaultListModel<File> defaultListModel;
    private JList<File> list;
    private JTextArea descriptionArea;
    private int counter = 0;
    private final Filter filter = new Filter();
    private Font fontForInfoPanels = new Font("Arial", Font.PLAIN, 16);

    ProjectManager() {
        setPanel();
        setMenuBar();
        setList();
        setDescriptionArea();
        setSecondaryComponents();
        setInfoPanel();
        setDevInfoPanel();
        mainMethod();
    }

    private void setPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
    }

    void returnPanel() {
        getContentPane().removeAll();
        getContentPane().add(panel);
        repaint();
    }

    private String readerForInfoPanels(String way) throws FileNotFoundException {
        String fill = "";
        File file = new File(way);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            fill += scanner.nextLine() + "\n";
        }
        return fill;
    }

    private void setInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        JButton ok = new JButton("Ok");
        ok.setBounds(4, 433, 476, 25);
        ok.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(panel);
            repaint();
        });
        JTextArea info = new JTextArea();
        info.setFont(fontForInfoPanels);
        try {
            info.setText(readerForInfoPanels("src/ml/javalearn/descriptions/infoInProjectManager/infoAboutApp"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setBounds(5, 0, 474, 433);

        infoPanel.add(scrollPane);
        infoPanel.add(ok);
    }

    private void setDevInfoPanel() {
        devInfoPanel = new JPanel();
        devInfoPanel.setLayout(null);
        JButton ok = new JButton("Ok");
        ok.setBounds(4, 433, 476, 25);
        ok.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(panel);
            repaint();
        });
        JTextArea info = new JTextArea();
        info.setFont(fontForInfoPanels);
        try {
            info.setText(readerForInfoPanels("src/ml/javalearn/descriptions/infoInProjectManager/infoAboutDev"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setBounds(5, 0, 474, 433);

        devInfoPanel.add(scrollPane);
        devInfoPanel.add(ok);
    }

    private void changePanel(JPanel panel) {
        if (counter == 0) {
            getContentPane().removeAll();
            getContentPane().add(panel);
            revalidate();
            setResizable(false);
            counter = 1;
        } else {
            getContentPane().removeAll();
            getContentPane().add(panel);
            revalidate();
            setResizable(true);
            counter = 0;
        }
    }

    private void setMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu main = new JMenu("Main");
        menuBar.add(main);

        JMenuItem close = new JMenuItem("Quit");
        close.addActionListener(e -> System.exit(0));

        JMenuItem info = new JMenuItem("Info");
        info.addActionListener(e -> changePanel(infoPanel));

        JMenuItem infoAboutDev = new JMenuItem("Info about developer");
        infoAboutDev.addActionListener(e -> changePanel(devInfoPanel));

        main.add(info);
        main.add(infoAboutDev);
        main.add(close);

        setJMenuBar(menuBar);
    }

    private void setList() {
        fillList();
        list = new JList<>(defaultListModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(11, 75, 233, 255);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        list.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getDescription();
            }
        });

        panel.add(scrollPane);
    }

    private void fillList() {
        String way = "src/ml/javalearn/tables/";
        ArrayList<File> arrayList = new ArrayList<>(Arrays.asList(filter.finderFiles(way)));
        defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(arrayList);
    }

    private void updateList() {
        fillList();
        list.setModel(defaultListModel);
    }

    private void setDescriptionArea() {
        descriptionArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        scrollPane.setBounds(243, 75, 231, 255);
        panel.add(scrollPane);
    }

    private void writeDescription() throws IOException {
        String fileName = list.getSelectedValue().toString();
        String[] files;
        files = fileName.split("/", 5);
        File file = new File(files[files.length-1]);
        FileWriter fileWriter = new FileWriter("src/ml/javalearn/descriptions/" + file.getName());
        fileWriter.write(descriptionArea.getText());
        fileWriter.close();
        if(file.exists() && !file.isDirectory()) {
            JOptionPane.showMessageDialog(this, "File saved successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Error! File not saved");
        }
    }

    private void getDescription() {
        try {
            descriptionArea.setText("");
            String fileName = list.getSelectedValue().toString();
            String[] files;
            files = fileName.split("/", 5);
            File file = new File(files[files.length-1]);
            File file1 = new File("src/ml/javalearn/descriptions/" + file.getName());
            FileReader fileReader = new FileReader(file1);
            Scanner scanner = new Scanner(fileReader);
            String s = "";
            while (scanner.hasNextLine()) {
                s += scanner.nextLine() + "\n";
            }
            fileReader.close();
            descriptionArea.setText(s);
        } catch (IOException ignored) {}
    }

    private void openProject() throws IOException {
        FileReader fileReader;
        Scanner sc;
        String fileName = list.getSelectedValue().toString();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String fileFill = scanner.nextLine();
        ArrayList<String> splitFile = new ArrayList<>(Arrays.asList(fileFill.split("/", 4)));

        int rowsInt = Integer.parseInt(splitFile.get(0));
        int amount = 7 * rowsInt;
        MainWindow mainWindow = new MainWindow(file.getName(), null, rowsInt, this);

        try {
            File file1 = new File("src/ml/javalearn/filesSaver/fields/" + file.getName());
            fileReader = new FileReader(file1);
            sc = new Scanner(fileReader);
            String fieldsFill = "";
            while (sc.hasNextLine()) {
                fieldsFill += sc.nextLine() + "\n";
            }

            File file2 = new File("src/ml/javalearn/filesSaver/areas/" + file.getName());
            fileReader = new FileReader(file2);
            sc = new Scanner(fileReader);
            String areasFill = "";
            while (sc.hasNextLine()) {
                areasFill += sc.nextLine() + "\n";
            }
            ArrayList<String> fieldsList = new ArrayList<>(
                    Arrays.asList(fieldsFill.split("/", amount * 2)));
            ArrayList<String> areasList = new ArrayList<>(
                    Arrays.asList(areasFill.split("/", amount)));
            if (Integer.parseInt(splitFile.get(3)) == 0) {
                mainWindow.fillFilesOnStart();
            }
            mainWindow.splitData(fieldsList, areasList);
        } catch (FileNotFoundException ignored) {}

        updateList();
        setVisible(false);
    }

    private void setSecondaryComponents() {
        Font font = new Font("Arial", Font.PLAIN, 16);

        JLabel tables = new JLabel("Tables:", SwingConstants.CENTER);
        tables.setFont(font);
        tables.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tables.setBounds(11, 50, 233, 30);

        JLabel desc = new JLabel("Description:", SwingConstants.CENTER);
        desc.setFont(font);
        desc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        desc.setBounds(243, 50, 231, 30);

        JLabel label  = new JLabel("Your projects:");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(175, 10, 300, 40);

        panel.add(label);
        panel.add(tables);
        panel.add(desc);

        JButton accept = new JButton("Open Project");
        JButton cancel = new JButton("Quit");
        JButton delete = new JButton("Delete project");
        JButton create = new JButton("Create project");
        JButton update = new JButton("Update");
        JButton saveD  = new JButton("Save description");
        update.setBounds(10, 330, 235, 25);
        accept.setBounds(10, 395, 235, 30);
        delete.setBounds(240, 395, 235, 30);
        cancel.setBounds(10, 425, 465, 30);
        create.setBounds(10, 365, 465, 30);
        saveD.setBounds(243, 330, 232, 25);

        cancel.addActionListener(e -> System.exit(0));

        String way = "src/ml/javalearn/filesSaver/";
        delete.addActionListener(e -> {
            System.gc();
            File file = new File(String.valueOf(list.getSelectedValue()));
            File areas = new File(way + "areas/" + file.getName());
            File fields = new File(way + "fields/" + file.getName());
            File descFile = new File("src/ml/javalearn/descriptions/" + file.getName());
            if (file.delete()) {
                descFile.delete();
                fields.delete();
                areas.delete();
                updateList();
                descriptionArea.setText("");
                JOptionPane.showMessageDialog(null, "Timing was deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Error! Timing not deleted",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        create.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(new CreateProjectPanel(this));
            revalidate();
        });

        accept.addActionListener(e -> {
            try {
                openProject();
                dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        update.addActionListener(e -> updateList());

        saveD.addActionListener(e -> {
            try {
                writeDescription();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(accept);
        panel.add(cancel);
        panel.add(delete);
        panel.add(create);
        panel.add(update);
        panel.add(saveD);
    }

    private void mainMethod() {
        initFrame();
        setVisible(true);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Open Timing List");
        setSize(new Dimension(500, 523));
        setLocation(100, 100);
        setFocusable(false);
        setResizable(false);
    }
}