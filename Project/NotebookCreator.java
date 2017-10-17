//Want to make it so that files will be written/sorted based on what the refereces in the file are

//in a way - try and combine the topo sort w this?


//test 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

<<<<<<<<< saved version
public class CreateDirectoryExample {

    public static void main(String[] args) {

        File file = new File("C:\\Directory1");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        File files = new File("C:\\Directory2\\Sub2\\Sub-Sub2");
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }

    }

}



<<<<<<< HEAD
=======
//test 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

>>>>>>> b276d09d85d858fb5c69652187615b034bbaf50f
=========
public class NotebookCreator extends JFrame{
    
    public NotebookCreator(){
        createView();
		
		setSize(400,800);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setResizable(false);
    }
    
    public void createView(){
        JFileChooser fc = new JFileChooser();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		
    }
}
>>>>>>>>> local version