package messageencry;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class MessageEncryApp extends JFrame {

    private JTextArea plainTextArea;
    private JTextArea encryptedTextArea;
    private final int shift = 3; // Caesar Cipher key

    public MessageEncryApp() {
        setTitle("Caesar Cipher Message Encryptor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout());

        // ===== Menu =====
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem encrypt = new JMenuItem("Encrypt");
        JMenuItem decrypt = new JMenuItem("Decrypt");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(openFile);
        fileMenu.add(encrypt);
        fileMenu.add(decrypt);
        fileMenu.add(save);
        fileMenu.add(clear);
        fileMenu.add(exit);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // ===== Title =====
        JLabel title = new JLabel("Caesar Cipher Encryptor", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.BLUE);
        add(title, BorderLayout.NORTH);

        // ===== Text Areas =====
        plainTextArea = new JTextArea();
        encryptedTextArea = new JTextArea();
        encryptedTextArea.setEditable(false);

        JScrollPane scroll1 = new JScrollPane(plainTextArea);
        scroll1.setBorder(new TitledBorder("Plain Message"));

        JScrollPane scroll2 = new JScrollPane(encryptedTextArea);
        scroll2.setBorder(new TitledBorder("Encrypted/Decrypted Message"));

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(scroll1);
        centerPanel.add(scroll2);
        add(centerPanel, BorderLayout.CENTER);

        // ===== Actions =====
        openFile.addActionListener(e -> openFile());
        save.addActionListener(e -> saveFile());
        clear.addActionListener(e -> clearText());
        encrypt.addActionListener(e -> encryptText());
        decrypt.addActionListener(e -> decryptText());
        exit.addActionListener(e -> System.exit(0));
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                plainTextArea.read(reader, null);
            } catch (IOException ex) {
                showError(ex.getMessage());
            }
        }
    }

    private void saveFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(chooser.getSelectedFile()))) {
                encryptedTextArea.write(writer);
            } catch (IOException ex) {
                showError(ex.getMessage());
            }
        }
    }

    private void encryptText() {
        String input = plainTextArea.getText();
        StringBuilder output = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char enc = (char) ((ch - base + shift) % 26 + base);
                output.append(enc);
            } else {
                output.append(ch); // keep spaces, punctuation
            }
        }

        encryptedTextArea.setText(output.toString());
    }

    private void decryptText() {
        String input = plainTextArea.getText();
        StringBuilder output = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char dec = (char) ((ch - base - shift + 26) % 26 + base);
                output.append(dec);
            } else {
                output.append(ch);
            }
        }
        encryptedTextArea.setText(output.toString());
    }

    private void clearText() {
        plainTextArea.setText("");
        encryptedTextArea.setText("");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MessageEncryApp().setVisible(true);
        });
    }
}
