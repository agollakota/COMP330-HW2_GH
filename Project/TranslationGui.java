import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class TranslationGui extends JFrame {

public TranslationGui() {
		
	//Setting details of JFrame
		super("Translator");
	
		createView();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("octopus.png"));
		
		setSize(400,800);		
		
		pack();		
		
		setLocationRelativeTo(null);		
		
		setResizable(false);		
		
	}

	public void createView() {
		
		//Array of supported languages for the Google Translator
		//Look to documentation for list of ISO codes for supported languages
		String languages[] = {"en","ru","es","fr","hi","de","el","ja"};		
		
		//Creating dropdown menus for language from and language to
		JComboBox<String> startLanguage = new JComboBox<>(languages);
		
		startLanguage.setSelectedIndex(-1);
		
		JComboBox<String> endLanguage = new JComboBox<>(languages);
		
		endLanguage.setSelectedIndex(-1);
		
		//Setting details for JTextArea
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setToolTipText("<html>Enter or open text into the text area and then click <br/> "
				                + "on the 'Translate' button to translate text from initial <br/> "
				                + "language to desired language.</html>");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		
		//Setting JMenuBar Details + actionListeners for MenuItems--> Open/Save files
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		menu.setOpaque(true);
		
		JMenu menuFile = new JMenu("File");
		
		menu.add(menuFile);
		
		JMenuItem open = new JMenuItem("Open File");
		menuFile.add(open);
		
		JFileChooser fc = new JFileChooser();
		
		open.addActionListener(ev -> {
			 int returnVal = fc.showOpenDialog(panel);
		      if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        try {
		          BufferedReader input = new BufferedReader(new InputStreamReader(
		              new FileInputStream(file)));
		          textArea.read(input, "Reading File...");
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      } else {
		        System.out.println("Operation Cancelled...");
		      }
		});
		
		JMenuItem save = new JMenuItem("Save File");
		menuFile.add(save);
		
		save.addActionListener(ev -> {
			int returnVal = fc.showOpenDialog(panel);
		      if (returnVal == JFileChooser.APPROVE_OPTION) {
		          String area = textArea.getText();
		    	  File file = fc.getSelectedFile();
		        try {
		           FileWriter fw = new FileWriter(file.getPath());
		           fw.write(area);
		           fw.flush();
		           fw.close();
		           
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      } else {
		        System.out.println("Operation Cancelled...");
		      }
		});
		
		/**
		 *Adding JButton with ActionListener that calls the Translation
		 *class's method to call and fetch a translation from the Google
		 *API URL.
		 *
		 */
		
		JButton translateButton = new JButton("TRANSLATE");
		translateButton.putClientProperty("Quaqua.Button.style", "bevel");
        translateButton.setFont(translateButton.getFont().deriveFont(Font.BOLD));
		translateButton.setToolTipText("Click to convert the language for the current text to the desired language.");
	
		//Uses input from the JComboBoxs and the text area to send to the translation method.
		translateButton.addActionListener(ev -> {
			Translation translate = new Translation();
			try {
				textArea.setText(translate.parser(startLanguage.getSelectedItem().toString(),
								endLanguage.getSelectedItem().toString(),textArea.getText()));
				startLanguage.setSelectedIndex(-1);
				endLanguage.setSelectedIndex(-1);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Please choose the appropriate 'Start' and 'End' Language Formats.");
			}
		});

		
		JLabel addStart = new JLabel("Select start language:");
		JLabel addEnd = new JLabel("Select end language:");
		
		//Chose GridLayout for panel because it allows uniform size for all buttons
		JPanel box = new JPanel(new GridLayout(6,0));
		box.add(addStart);
		box.add(startLanguage);
		box.add(addEnd);
		box.add(endLanguage);
		box.add(translateButton);
		
	    
		panel.add(scrollPane, BorderLayout.CENTER);
	    panel.add(box);
	    
	  }
}
