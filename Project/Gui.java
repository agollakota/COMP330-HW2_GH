import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import com.inet.jortho.SpellChecker;

import java.awt.event.*;
import java.awt.*;
/**
 *
 */

public class Gui extends JFrame {
    
    /**
     * This function creates the gif image taken from the file folder
     * and b1 is a button created that says PLAY, but has an empty side label
     * handlerclass is what happens when the button is pressed
     * If the user exits the gui then the override method will show up and print out message instead of just closing
     */

    public Gui() {
        super("CAZA");
        
        createView();
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(400,800);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setResizable(false);

    }

	public void createView() {
        
        JLabel background = new JLabel(new ImageIcon("giphy.gif"));
        background.setLayout(new BorderLayout());
        
        JButton sortButton = new JButton ("OPEN SORTER");
        
        JButton noteButton = new JButton("OPEN NOTE PAD");
        noteButton.addActionListener(ev -> {
        	NotePad app = new NotePad();
            app.setVisible(true);
		});
        
        JButton TTSButton = new JButton("OPEN TTS CONVERTER");
        TTSButton.addActionListener(ev -> {
        	SwingUtilities.invokeLater(new Runnable() {
    	        public void run() {
    	        	new TTSGui().setVisible(true);
    	        }
    		});
		});
        
        JButton spellingButton = new JButton("OPEN SPELL CHECKER");
        spellingButton.addActionListener(ev -> {
        	SwingUtilities.invokeLater(new Runnable() {
    	        public void run() {
    	        	new SpellCheck().setVisible(true);
    	        }
    		});
		});
        
        JButton translatorButton = new JButton("OPEN TRANSLATOR");
        
        
        JButton calendarButton = new JButton("OPEN CALENDAR");
        
        JPanel buttonPanel = new JPanel();

        getContentPane().add(background);
       
        Box box = Box.createVerticalBox();
        
        box.add(sortButton);
        box.add(noteButton);
        box.add(TTSButton);
        box.add(spellingButton);
        box.add(translatorButton);
        box.add(calendarButton);
       
        background.add(buttonPanel);
        buttonPanel.add(box);
        buttonPanel.setOpaque(false);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Have a great day!");
                dispose();
            }
        });

		}
    }
