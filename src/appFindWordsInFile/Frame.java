package appFindWordsInFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Frame extends JFrame implements ActionListener {

    JLabel labelFindWords = new JLabel();
    JLabel labelWelcome = new JLabel();
    JFileChooser fileChooser = new JFileChooser();
    JButton buttonOpenFile = new JButton("Open file...");
    JButton buttonClean = new JButton("Clean table");
    JButton buttonSaveFile = new JButton("Save text in file...");
    JTextArea textArea = new JTextArea("",20,20);
    JScrollPane scrollPane = new JScrollPane(textArea);
    JPanel panelWelcome = new JPanel();
    JPanel panelWest = new JPanel();
    JPanel panelEast = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panelCenter = new JPanel();
    File fileOpen;
    List<Map.Entry<String, Integer>> listOfEntries;


    public Frame() {
        setDefaultFrame();

        // ------------------- *** JLabel *** -------------------

        labelWelcome.setText("Open file to find all unique worlds and count them:");
        labelWelcome.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        labelWelcome.setHorizontalAlignment(JLabel.LEFT);
        //labelWelcome.setLocation(0, 0);
        //labelWelcome.setSize(600, 50);
        labelWelcome.setFont(new Font("", Font.BOLD, 15 ));

        // ------------------- *** JButton *** -------------------

        buttonOpenFile.addActionListener(this);
        buttonClean.addActionListener(this);
        buttonSaveFile.addActionListener(this);

        // ------------------- *** JPanelWelcome *** -------------------

        panelWelcome.setLayout(new FlowLayout());
        panelWelcome.setPreferredSize(new Dimension(100, 100));
        panelWelcome.setBackground(Color.LIGHT_GRAY);
        panelWelcome.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panelWelcome.setLayout(new BorderLayout());

        JPanel panelWelcomeNorth = new JPanel();
        JPanel panelWelcomeCenter = new JPanel();
        JPanel panelWelcomeEast = new JPanel();
        JPanel panelWelcomeSouth = new JPanel();
        JPanel panelWelcomeWest = new JPanel();

        panelWelcomeNorth.setBackground(Color.LIGHT_GRAY);
        panelWelcomeCenter.setBackground(Color.LIGHT_GRAY);
        panelWelcomeEast.setBackground(Color.LIGHT_GRAY);
        panelWelcomeWest.setBackground(Color.LIGHT_GRAY);
        panelWelcomeSouth.setBackground(Color.LIGHT_GRAY);

        panelWelcomeNorth.add(labelWelcome);
        panelWelcomeCenter.add(buttonOpenFile);
        panelWelcomeCenter.add(buttonClean);
        panelWelcomeCenter.add(buttonSaveFile);

        panelWelcome.add(panelWelcomeNorth, BorderLayout.NORTH);
        panelWelcome.add(panelWelcomeCenter, BorderLayout.CENTER);
        panelWelcome.add(panelWelcomeEast, BorderLayout.EAST);
        panelWelcome.add(panelWelcomeWest, BorderLayout.WEST);
        panelWelcome.add(panelWelcomeSouth, BorderLayout.SOUTH);

        // ------------------- *** JPanelWest *** -------------------

        panelWest.setLayout(new BorderLayout());
        panelWest.setPreferredSize(new Dimension(100, 100));
        panelWest.setBackground(Color.LIGHT_GRAY);
        panelWest.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        //panelWest.setLayout(new FlowLayout());
        //panelWest.add(buttonSaveFile);

        // ------------------- *** JPanelEast *** -------------------

        panelEast.setLayout(new BorderLayout());
        panelEast.setPreferredSize(new Dimension(100, 100));
        panelEast.setBackground(Color.LIGHT_GRAY);
        panelEast.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // ------------------- *** JPanelSouth *** -------------------

        panelSouth.setLayout(new BorderLayout());
        panelSouth.setPreferredSize(new Dimension(100, 100));
        panelSouth.setBackground(Color.LIGHT_GRAY);
        panelSouth.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // ------------------- *** JPanelCenter *** -------------------

        panelCenter.setLayout(new BorderLayout());
        panelCenter.setPreferredSize(new Dimension(100, 100));
        panelCenter.setBackground(Color.ORANGE);
        panelCenter.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // ------------------- *** JTextArea & JScrollPane &JFileChooser *** -------------------

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        panelCenter.add(scrollPane, BorderLayout.CENTER);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files .txt", "txt"));

        // ------------------- *** add to JFrame *** -------------------

        //this.add(labelWelcome);
        //this.add(buttonOpenFile);
        this.add(panelWelcome, BorderLayout.NORTH);
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelEast, BorderLayout.EAST);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.add(panelCenter, BorderLayout.CENTER);
        //this.pack();
        this.setVisible(true);
    }

    public void setDefaultFrame() {
        ImageIcon imageIcon = new ImageIcon("logoApp.png");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("App Find Words in File");
        this.setLayout(new BorderLayout());
        //this.setLocationByPlatform(true);
        this.setIconImage(imageIcon.getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonOpenFile) {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                fileOpen = new File(fileChooser.getSelectedFile().getAbsolutePath());
                textArea.setText("");
                searchWords(fileOpen);
            }
        }

        if (e.getSource() == buttonClean) {
            textArea.setText("");
        }

        if (e.getSource() == buttonSaveFile) {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File fileSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
                saveWordsInFile(fileSave);

            }
        }
    }

    private void saveWordsInFile(File fileSave) {
        String str = "";
        if (! (listOfEntries == null)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSave))) {
                for (var entry : listOfEntries) {
                    str = entry.getValue() + " - " + entry.getKey() + "\n";
                    writer.write(str);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchWords (File file) {

        // ------------------- *** Preparing variables *** -------------------

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        String str;
        String strPattern = "[a-zA-Z'’]{2,}+"; //String starts with letter and ends with letter
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher;
        Scanner sc = null;

        // ------------------- *** Searching words *** -------------------

        try {
            sc = new Scanner(file);
            while (sc.hasNext()) {
                str = sc.next();
                str = str.trim();
                //str = str.toLowerCase();
                if (str.matches(strPattern)) {
                    treeMap.put(str, treeMap.getOrDefault(str, 0) + 1);
                } else {
                    matcher = pattern.matcher(str);
                    if (matcher.find()) {
                        str = str.substring(matcher.start(), matcher.end());
                        treeMap.put(str, treeMap.getOrDefault(str, 0) + 1);
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }

        // ------------------- *** Wrapping words and place them to the Frame *** -------------------

        listOfEntries = new ArrayList<>(treeMap.entrySet());
        listOfEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder())); //Sorting words
        for (var entry : listOfEntries) {
            textArea.append(entry.getValue() + " - " + entry.getKey() + "\n"); //placing words to Frame
        }
    }
}

