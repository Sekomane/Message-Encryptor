package messageencry;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class MessageEncry {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(500, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Secure Message");
        
        //Menubar 
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("File");
        menu.setBounds(10, 20, 40, 10);
        
        //Menu Items
        JMenuItem openFile=new JMenuItem("Open file");
        JMenuItem  ecrypt=new JMenuItem("Encrypt Message");
        JMenuItem save=new JMenuItem("Save Encrypted Message");
        JMenuItem clear=new JMenuItem("Clear");
        JMenuItem exit=new JMenuItem("Exit");
      
        menu.add(openFile);
        menu.add(ecrypt);
        menu.add(save);
        menu.add(clear);
        menu.add(exit);
        menuBar.add(menu);
        
        
        //button for message encryptor
        JLabel button=new JLabel("Message Encryptor");
        button.setBounds(150, 50, 240, 30);
        button.setForeground(Color.blue);
        button.setFont(new Font("",Font.ITALIC,20));
        
        //Creating fieldset for text area 1
        JPanel fieldset=new JPanel();
        fieldset.setBorder(new TitledBorder("Plain message"));
        fieldset.setBounds(5, 80, 270, 170);
        //plain message text area
        JTextArea textArea1=new JTextArea();
        JScrollPane scrollPane=new JScrollPane(textArea1);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 110, 260, 130);
        
        //fieldset for text area2
        JPanel fieldset2=new JPanel();
        fieldset2.setBorder(new TitledBorder("Encrypted message"));
        fieldset2.setBounds(275, 80, 270, 170);
        
        //encrypted message text area
        JTextArea textArea2=new JTextArea();
        textArea2.setEditable(false);
        JScrollPane scrollPane2=new JScrollPane(textArea2);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(280, 110, 260, 130);

        ArrayList<String>message=new ArrayList();
        //Encrypting the message
        ecrypt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==ecrypt)
                {
                    String text1=textArea1.getText().toLowerCase();
                    String text2=""; 
                    for(int i=0;i<text1.length();i++)
                    {
                        char ch=text1.charAt(i);
                        if(ch=='a')
                        {
                            text2+="1";
                        }
                        else if(ch=='e')
                        {
                            text2+="2";
                        }
                        else if(ch=='i')
                        {
                            text2+="3";
                         }
                        else if(ch=='o')
                        {
                            text2+="4";
                         }
                        else if(ch=='u')
                        {
                            text2+="5";
                        }
                        else
                        {
                            text2+="_";
                        }
                    }
                    textArea2.setText(text2);
                }
            }
        });
        //open file
       openFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             if(e.getSource()==openFile){
                 JFileChooser fileChooser=new JFileChooser();
                 int option=fileChooser.showOpenDialog(frame);
                 if(option==JFileChooser.APPROVE_OPTION){
                    File file=fileChooser.getSelectedFile();
                    try(BufferedReader reader=new BufferedReader(new FileReader(file)))
                    {
                        textArea1.read(reader, e);
                    } catch (FileNotFoundException ex) {
                       System.out.print(ex.getMessage());
                     } catch (IOException ex) {
                       System.out.print(ex.getMessage());  
                     }
                 }      
             }
            }
        });
        
        //save encrypted message
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==save){
                    JFileChooser fileChooser=new JFileChooser();
                    int option=fileChooser.showSaveDialog(frame);
                    if(option==JFileChooser.APPROVE_OPTION){
                      File file=fileChooser.getSelectedFile();
                      try(BufferedWriter writer=new BufferedWriter(new FileWriter(file)))
                      {
                       textArea2.write(writer);
                      } catch (IOException ex) {
                          System.out.println(ex.getMessage());
                      }
                    }
                }
            }
        });
        //clear
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              if(e.getSource()==clear)
              {
                 textArea1.setText("");
                 textArea2.setText("");
              }
            }
        });
         //exiting the program
         exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==exit)
                {
                    System.exit(0);
                }
            }
        });
         
        frame.add(scrollPane);
        frame.add(scrollPane2);
        frame.add(button);
        frame.add(fieldset);
        frame.add(fieldset2);
        frame.setJMenuBar(menuBar);
        
        frame.setVisible(true);
    }
    
}
