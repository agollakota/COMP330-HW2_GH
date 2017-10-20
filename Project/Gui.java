import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.inet.jortho.SpellChecker;

import java.awt.event.*;
import java.awt.*;
/**
 *
 */

public class Gui extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		setSize(470, 470);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setResizable(false);

    }

	public void createView() {
        Calendar newCalendar = new Calendar();
        l1 = new JLabel(newCalendar.checkEventToday());             //checks if the user has any events that are occurring that day that they set beforehand
        l1.setForeground(Color.white);
        add(l1);
        JLabel background = new JLabel(new ImageIcon("giphy.gif")); //sets gif as foreground icon
        background.setLayout(new BorderLayout());
        
        JButton sortButton = new JButton ("OPEN SORTER");           //button to open sorter *NEEDS FIX*
        
        JButton noteButton = new JButton("OPEN NOTE PAD");          //button to open Note Pad for user
        
        noteButton.addActionListener(ev -> {            
        	NotePad app = new NotePad();
            app.setVisible(true);
		});                                                         //runs NotePad class
        
        JButton TTSButton = new JButton("OPEN TTS CONVERTER");      //button to open Text-to-Speech Converter
        
        TTSButton.addActionListener(ev -> {
        	SwingUtilities.invokeLater(new Runnable() {
    	        public void run() {
    	        	new TTSGui().setVisible(true);
    	        }
    		});
		});                                                         //runs Text-to-Speech class
        
        JButton spellingButton = new JButton("OPEN SPELL CHECKER"); //button to open Spell Checker
        
        spellingButton.addActionListener(ev -> {
        	SwingUtilities.invokeLater(new Runnable() {
    	        public void run() {
    	        	new SpellCheck().setVisible(true);
    	        }
    		});
		});                                                         //runs Spell Checker class
        
        JButton translatorButton = new JButton("OPEN TRANSLATOR");  //button to open Translator
        
        JButton calendarButton = new JButton("OPEN CALENDAR");      //button to open Calendar
        getContentPane().add(background);
        
        JPanel buttonPanel = new JPanel();
        
        JPanel box = new JPanel(new GridLayout(3,2));
        
        box.add(sortButton);                                        //All boxes for displaying buttons within GUI
        box.add(noteButton);                                        //
        box.add(TTSButton);                                         //
        box.add(spellingButton);                                    //
        box.add(translatorButton);                                  //
        box.add(calendarButton);                                    //
       
        background.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(box);
        buttonPanel.setOpaque(false);
        box.setBackground(new Color(213,134,145,123));              
        buttonPanel.setBackground(new Color(213,134,145,123));      //Sets color to Cyprus Blue
   
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Have a great day!");
                dispose();
            }
        });                                                         //When user exits out of window, dialogue panel is shown

		}
    }
