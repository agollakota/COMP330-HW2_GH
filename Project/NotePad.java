import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;



//adapted from: http://www.dreamincode.net/forums/topic/66176-creating-a-basic-notepad-application/
public class NotePad extends JFrame implements ActionListener {
    private TextArea textArea = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    private MenuBar menuBar = new MenuBar(); // first, create a MenuBar item
    private Menu file = new Menu(); // our File menu
    private MenuItem openFile = new MenuItem();
    private MenuItem saveFile = new MenuItem();
    private MenuItem close = new MenuItem();
    private Menu invert=new Menu();
    private MenuItem normal = new MenuItem();
    private MenuItem inverted = new MenuItem();
    public NotePad() {
        this.setSize(1000, 1000); 
        this.setTitle("CAZA NotePad Pro"); 


        this.textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12)); 
      
        this.getContentPane().setLayout(new BorderLayout()); 
        this.getContentPane().add(textArea);
        
        this.setMenuBar(this.menuBar);
        this.menuBar.add(this.file); 

        this.file.setLabel("File");
        this.invert.setLabel("Invert Color");
       
        this.openFile.setLabel("Open"); 
        this.openFile.addActionListener(this); 
        this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false)); 
        this.file.add(this.openFile); 
        
        this.saveFile.setLabel("Save");
        this.saveFile.addActionListener(this);
        this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
        this.file.add(this.saveFile);


        this.invert.setLabel("Invert");

        this.inverted.setLabel("Black/White");
        this.inverted.addActionListener(this);
        this.file.add(this.inverted);


        this.normal.setLabel("Normal");
        this.normal.addActionListener(this);
        this.file.add(this.normal);

        
        this.close.setLabel("Close");
       
        this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
        this.close.addActionListener(this);
        this.file.add(this.close);
    }

    public void actionPerformed (ActionEvent e) {

        if(e.getSource()==this.inverted){
            textArea.setBackground(Color.black);
            textArea.setForeground(Color.white);

        }
        else if(e.getSource()==this.normal){
            textArea.setBackground(Color.white);
            textArea.setForeground(Color.black);
        }

        
        if (e.getSource() == this.close)
            this.dispose(); 

            
        else if (e.getSource() == this.openFile) {
            JFileChooser open = new JFileChooser(); 
            int option = open.showOpenDialog(this); 
           
            if (option == JFileChooser.APPROVE_OPTION) {
                this.textArea.setText(""); 
                try {
                    
                    Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                    while (scan.hasNext()) 
                        this.textArea.append(scan.nextLine() + "\n"); 
                } catch (Exception ex) { 
                   
                    System.out.println(ex.getMessage());
                }
            }
        }

        
        else if (e.getSource() == this.saveFile) {
            JFileChooser save = new JFileChooser(); 
            int option = save.showSaveDialog(this); 
            
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    
                    BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
                    out.write(this.textArea.getText()); 
                    out.close(); 
                } catch (Exception ex) { 
                   
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}