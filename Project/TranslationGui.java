import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;

public class TranslationGui extends JFrame {

	private static final long serialVersionUID = 1L;
	
public TranslationGui() {
		
		createView();
		
		setSize(400,800);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
	}																			//Initializes GUI for user

	public void createView() {
		
		String languages[] = {"en","ru","es","fr","hi","de","el","ja"};			//Array of languages available for translated from and to
		
		JComboBox<String> startLanguage = new JComboBox<>(languages);			//Creates dropdown menu for user to select start language
		
		startLanguage.setSelectedIndex(-1);
		
		JComboBox<String> endLanguage = new JComboBox<>(languages);				//Creates dropdown menu for user to select end language
		
		endLanguage.setSelectedIndex(-1);
		
		JFileChooser fc = new JFileChooser();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textArea.setToolTipText("<html>Enter or open text into the text area and then click <br/> "
				                + "on the 'Translate' button to translate from s<br/> "
				                + "spelling. Possible errors in spelling will be underlined and <br/> "
				                + "when right clicked, will provide suggestions for correction.</html>");
																				//^^ Above text displays a helpful tip for users on how to use the translator
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));					//Initializes size of display for text
		
		JButton readButton = new JButton("OPEN FILE");							//Allows users to open text file to be translated
		readButton.setToolTipText("Click to open a text file into the text area for translation.");
		
		JButton translateButton = new JButton("TRANSLATE");						//Button to translate text
		translateButton.setToolTipText("Click to convert the language for the current text to the desired language.");
		
		JPanel panel = new JPanel();
		
		getContentPane().add(panel);
		
		translateButton.addActionListener(ev -> {
			Translation translate = new Translation();
			try {
				textArea.setText(translate.parser(startLanguage.getSelectedItem().toString(),
								endLanguage.getSelectedItem().toString(),textArea.getText()));
				startLanguage.setSelectedIndex(-1);
				endLanguage.setSelectedIndex(-1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	    
		readButton.addActionListener(ev -> {
	      int returnVal = fc.showOpenDialog(panel);
	      if (returnVal == JFileChooser.APPROVE_OPTION) {
	        File file = fc.getSelectedFile();
	        try {
	          BufferedReader input = new BufferedReader(new InputStreamReader(
	              new FileInputStream(file)));
	          textArea.read(input, "Reading File...");							//Reads file for translation
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      } else {
	        System.out.println("Operation Cancelled...");						//If unsuccessful, prints message
	      }
	    });

		JLabel addStart = new JLabel("Select start language:");
		JLabel addEnd = new JLabel("Select end language:");
		
		JPanel box = new JPanel(new GridLayout(6,0));							//Initalizes 6 boxes in a grid formation for GUI
		box.add(addStart);														//Creates box for "Select start language"
		box.add(startLanguage);													//Creates box for selected start language
		box.add(addEnd);														//Creates box for "Select end language"
		box.add(endLanguage);													//Creates box for selected end language
		box.add(readButton);													//Creates box for READ button
		box.add(translateButton);												//Creates box for TRANSLATE button
		
	    panel.add(scrollPane, BorderLayout.CENTER);
	    panel.add(box);
	  }
}
