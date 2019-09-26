package ml.javalearn.front;

import ml.javalearn.back.Filter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class ProjectManager extends JFrame {

    private JPanel panel;
    private final String way = "src/ml/javalearn/tables/";
    private ArrayList<File> arrayList;
    private DefaultListModel<File> defaultListModel;
    private JList<File> list;

    ProjectManager() {
        setPanel();
        setList();
        setDescriptionArea();
        setSecondaryComponents();
    }

    private void setPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
    }

    private void setList() {
        fillList();
        list = new JList<>(defaultListModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(11, 75, 233, 255);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(scrollPane);
    }

    private void fillList() {
        Filter filter = new Filter();
        arrayList = new ArrayList<>(Arrays.asList(filter.finder(way)));
        defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(arrayList);
    }

    private void updateList() {
        System.out.println("Start updating list");
        fillList();
        list.setModel(defaultListModel);
        System.out.println("List was updated");
    }

    private void setDescriptionArea() {
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionArea.setFont(new Font("Times new Roman", Font.PLAIN, 14));
        descriptionArea.setBounds(243, 75, 235, 280);
        panel.add(descriptionArea);
    }

    private void openProject() throws IOException {
        File file = arrayList.get(list.getSelectedIndex());
        Scanner scanner = new Scanner(file);
        String fileFill = scanner.nextLine();
        ArrayList<String> splitFile = new ArrayList<>(Arrays.asList(fileFill.split("/", 3)));
        System.out.println(splitFile);

        MainWindow mainWindow = new MainWindow();
        int rowsInt = Integer.parseInt(splitFile.get(0));
        int colsInt = Integer.parseInt(splitFile.get(1));
        System.out.println("selected index " + list.getSelectedValue());
        mainWindow.fileName = file.getName();
        mainWindow.rows = rowsInt;
        mainWindow.cols = colsInt;
        int amount = colsInt * rowsInt;
        mainWindow.textFieldsSize = amount * 3;
        mainWindow.panelsSize     = amount;
        System.out.println("data transferred");

        try {
            File file1 = new File("src/ml/javalearn/filesSaver/" + file.getName());
            Scanner sc = new Scanner(file1);
            ArrayList<String> arrayList = new ArrayList<>(
                    Arrays.asList(sc.nextLine().split("/", amount * 3 + 1)));
            System.out.println("array list with saved files: " + arrayList);
            mainWindow.mainMethod();
            mainWindow.splitData(arrayList);
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }


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
        desc.setBounds(243, 50, 235, 30);

        JLabel label  = new JLabel("Your projects:");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(175, 10, 300, 40);

        panel.add(label);
        panel.add(tables);
        panel.add(desc);

        JButton accept = new JButton("Open Project");
        JButton cancel = new JButton("Cancel");
        JButton delete = new JButton("Delete project");
        JButton create = new JButton("Create project");
        JButton update = new JButton("Update");
        update.setBounds(10, 330, 233, 25);
        accept.setBounds(10, 395, 235, 30);
        delete.setBounds(240, 395, 235, 30);
        cancel.setBounds(10, 425, 465, 30);
        create.setBounds(10, 365, 465, 30);

        cancel.addActionListener(e -> System.exit(0));

        delete.addActionListener(e -> {
            Filter filter = new Filter();
            ArrayList<File> arrayList = new ArrayList<>(Arrays.asList(filter.finder(way)));
            File file = arrayList.get(list.getSelectedIndex());

            if (file.delete()) {
                updateList();
                System.out.println("File deleted");
            }
        });

        create.addActionListener(e -> new StartWindow());

        accept.addActionListener(e -> {
            try {
                openProject();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        update.addActionListener(e -> updateList());

        panel.add(accept);
        panel.add(cancel);
        panel.add(delete);
        panel.add(create);
        panel.add(update);
    }

    void mainMethod() {
        initFrame();
        setVisible(true);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Open Timing List");
        setSize(new Dimension(500, 503));
        setResizable(true);
    }

}