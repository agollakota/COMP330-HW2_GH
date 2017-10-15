
import com.inet.jortho.PopupListener;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@SuppressWarnings("serial")

public class SpellCheckGui extends JFrame {
	
	public SpellCheckGui() {
		
		//setting JFrame Details
		createView();
		
		setSize(400,800);
		
		pack();
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
	}

	public void createView() {
		
		//Create spellcheck object to perform spellchecker in listeners
		SpellCheck sc = new SpellCheck();
		
		//Adding panel to frame
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		//Setting JTextArea details
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textArea.setToolTipText("<html>Enter or open text into the text area and then click <br/> "
				                + "on the 'Spell Check' button to find any potential errors in <br/> "
				                + "spelling. Potential errors in spelling will be underlined and <br/> "
				                + "when right clicked, suggestions shall be provided for correction.</html>");
		
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		
		
		//Setting JButtons and respective ActionListeners
		JButton readButton = new JButton("OPEN FILE");
		readButton.setToolTipText("Click to open a text file into the text area for spell check.");
		
		JFileChooser fc = new JFileChooser();
		readButton.addActionListener(ev -> {
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
		
		JButton spellButton = new JButton("SPELL CHECK");
		spellButton.setToolTipText("Click to analyze the text for errors in spelling.");
		
		spellButton.addActionListener(ev -> {
			sc.checker(textArea);
		});
		
		//Create JCheckBox to perform real-time spellcheck while selected
		JCheckBox rtBox = new JCheckBox("Real-Time SpellCheck");
		rtBox.addItemListener(ev -> {
			textArea.getDocument().addDocumentListener(new DocumentListener() {

	public void changedUpdate(DocumentEvent arg0) {
		if (rtBox.isSelected()) sc.checker(textArea);
	}
	
	public void insertUpdate(DocumentEvent arg0) {
		if (rtBox.isSelected()) sc.checker(textArea);
	}
	
	public void removeUpdate(DocumentEvent arg0) {
		if (rtBox.isSelected()) sc.checker(textArea);
	}
});
		});
		  
		
		//Setting SpellCheckerOptions details to add to PopupMenu
		SpellCheckerOptions sco = new SpellCheckerOptions();
		sco.setCaseSensitive(false);
		sco.setSuggestionsLimitMenu(10);
		sco.setLanguageDisableVisible(false);
		sco.setIgnoreWordsWithNumbers(true);
		
		JPopupMenu popup = SpellChecker.createCheckerPopup(sco);
		textArea.addMouseListener(new PopupListener(popup));

		//Use of GridLayout for uniform size of buttons
		JPanel box = new JPanel(new GridLayout(3,0));
		box.add(readButton);
		box.add(spellButton);
		box.add(rtBox);
		
	    panel.add(scrollPane, BorderLayout.CENTER);
	    panel.add(box);
	  }
}

